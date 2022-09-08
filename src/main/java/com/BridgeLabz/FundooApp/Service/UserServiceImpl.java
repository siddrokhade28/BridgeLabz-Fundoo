package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Security.Service.JwtUtilService;
import com.BridgeLabz.FundooApp.Exception.ExceptionMessage;
import com.BridgeLabz.FundooApp.MailConfiguration.MailSenderImpl;
import com.BridgeLabz.FundooApp.Utility.Response;
import com.BridgeLabz.FundooApp.Utility.Utility;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//Implementation of User Interface
@Service
public class UserServiceImpl implements IUSerService {

    @Value("${From}")
    private String From;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderImpl mailSender;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    @SneakyThrows
    public Response registration(RegisterDTO registerDTO) {
        userRepository.findByEmail(registerDTO.getEmail()).ifPresent(action -> {
            throw new ExceptionMessage("Email already registered");
        });
        User user = modelMapper.map(registerDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String JwtToken = jwtUtilService.generateToken(registerDTO.getEmail());
        user.setConfirmationToken(JwtToken);
        userRepository.save(user);
        mailSender.confirmMail(From, registerDTO.getEmail(), JwtToken);
        return Utility.getResponse("User registered sucessfullly", JwtToken);
    }

    @Override
    @SneakyThrows
    public Response login(LoginDTO loginDTO) {
        if (userRepository.findByEmail(loginDTO.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(loginDTO.getEmail()).get();
            if (user.isVerified() == true) {
                System.out.println("Login successful");
            } else {
                throw new ExceptionMessage("User Email not verified");
            }
        } else {
            throw new ExceptionMessage("Invalid mail ID");
        }
        return Utility.getResponse("Login Successful", HttpStatus.OK);
    }


    @Override
    public Response resetpassword(RestPasswordDTO restPasswordDTO, String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            if (restPasswordDTO.getNewPasswrod().equals(restPasswordDTO.getConfirmPassword())) {
                user.setPassword(restPasswordDTO.getConfirmPassword());
                userRepository.save(user);
                mailSender.sendEmail(From, user.getEmail());
            } else {
                throw new ExceptionMessage("Passwords do not Match");
            }
        } else {
            throw new ExceptionMessage("Email Does not Exist");
        }
        return Utility.getResponse("Login Successful", HttpStatus.OK);
    }

    @Override
    public Response resetPasswordByToken(RestPasswordDTO restPasswordDTO, String token) {
        if (userRepository.findByResetPasswordToken(token).isPresent()) {
            User user = userRepository.findByResetPasswordToken(token).get();
            if (restPasswordDTO.getNewPasswrod().equals(restPasswordDTO.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(restPasswordDTO.getConfirmPassword()));
                user.setResetPasswordToken(null);
                userRepository.save(user);
            } else {
                throw new ExceptionMessage("Password do not match");
            }
        } else {
            throw new ExceptionMessage("Invalid Token");
        }
        return Utility.getResponse("Password reset Successful", HttpStatus.OK);
    }

    @Override
    @SneakyThrows
    public Response forgotPassword(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            String token = jwtUtilService.generateToken(email);
            user.setResetPasswordToken(token);
            userRepository.save(user);
            mailSender.forgotPasswordMail(From, email, token);
        } else {
            throw new ExceptionMessage("Invalid Email ");
        }
        return Utility.getResponse("generated password has been sent to your Email", HttpStatus.OK);
    }

    public String confirmEmail(String confirmationToken) {
        User user = userRepository.findByConfirmationToken(confirmationToken).get();
        System.out.println(user);
        if (user.getConfirmationToken().equals(confirmationToken)) {
            user.setVerified(true);
            userRepository.save(user);
        } else {
            throw new ExceptionMessage("Not Verified ");
        }
        return "Verified Successfully";
    }


    public List<AllUsers> getAllUser() {
        return userRepository.FindAllUSer();
    }


}
