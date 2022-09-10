package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.*;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Security.CustomUserDeatilsService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailSenderImpl mailSender;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private HttpServletRequest httpServlet;

    @Autowired
    private CustomUserDeatilsService customUserDeatilsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    @SneakyThrows
    public Response registration(RegisterDTO registerDTO) {
        userRepository.findByEmail(registerDTO.getEmail()).ifPresent(action -> {
            throw new ExceptionMessage("Email already registered");
        });

        User user = modelMapper.map(registerDTO, User.class);
        customUserDeatilsService.setEmail(registerDTO.getEmail());
        customUserDeatilsService.setPassWord(registerDTO.getPassword());
        System.out.println(customUserDeatilsService.getEmail());
        System.out.println(customUserDeatilsService.getPassWord());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        customUserDeatilsService.getEmail(),
                        customUserDeatilsService.getPassWord()
                )
        );
        UserDetails userDetails = customUserDeatilsService.loadUserByUsername(registerDTO.getEmail());
        String JwtToken = jwtUtilService.generateToken(userDetails);
        mailSender.confirmMail(From, registerDTO.getEmail(), JwtToken);
        userRepository.save(user);
        return Utility.getResponse("User registered sucessfullly", JwtToken);
    }

    @Override
    @SneakyThrows
    public Response login(LoginDTO loginDTO) {
        if (userRepository.findByEmail(loginDTO.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(loginDTO.getEmail()).get();
            if (user.isVerified() == true) {
                UserDetails userDetails = customUserDeatilsService.loadUserByUsername(loginDTO.getEmail());
                String token = jwtUtilService.generateToken(userDetails);
                return Utility.getResponse("Login Successful", token);
            } else {
                throw new ExceptionMessage("User Email not verified");
            }
        } else {
            throw new ExceptionMessage("Invalid mail ID");
        }

    }


    @Override
    public Response resetpassword(RestPasswordDTO restPasswordDTO, String email) {
        String authorizationHeader = httpServlet.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7);
        String userName = jwtUtilService.extractUsername(jwt);
        if (userRepository.findByEmail(email).get().getEmail().equals(userName)) {
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
        } else {
            throw new ExceptionMessage("Token not matching to the Email ");
        }
        return Utility.getResponse("Password has been reset", HttpStatus.OK);
    }


    @Override
    @SneakyThrows
    public Response forgotPassword(ForgotPasswordDTO email) {
        if (userRepository.findByEmail(email.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(email.getEmail()).get();
            UserDetails userDetails = customUserDeatilsService.loadUserByUsername(email.getEmail());
            String token = jwtUtilService.generateToken(userDetails);
            mailSender.forgotPasswordMail(From, email.getEmail(), token);
        } else {
            throw new ExceptionMessage("Invalid Email ");
        }
        return Utility.getResponse("generated password has been sent to your Email", HttpStatus.OK);
    }

    public String confirmEmail(String confirmationToken) {
        String username = jwtUtilService.extractUsername(confirmationToken);
        userRepository.findByEmail(username).get().setVerified(true);
        userRepository.save(userRepository.findByEmail(username).get());

        return "Verified Successfully";
    }


    public List<FetchAllUsers> getAllUser() {
        FetchAllUsers allUsers = modelMapper.map(userRepository.findAll(),FetchAllUsers.class);
        List<FetchAllUsers> list = new ArrayList<>() ;
        list.add(allUsers);
        return list;
    }


}
