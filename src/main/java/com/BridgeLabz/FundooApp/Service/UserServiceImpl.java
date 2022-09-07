package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Model.ConfirmationToken;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.TokenRepository;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Utility.MailSenderImpl;
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
import java.util.UUID;


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
    private TokenRepository tokenRepository;

    @Override
    public Response registration(RegisterDTO registerDTO)  {
        User user=modelMapper.map(registerDTO,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        tokenRepository.save(new ConfirmationToken(String.valueOf(UUID.randomUUID()),user));
        User save =userRepository.save(user);
        mailSender.confirmMail(From,registerDTO.getEmail(),tokenRepository.getById(save.getId()).getToken());
        /*
        String token= utiljwt.createToken(save.getEmail());
        mailUtility.sendMail(save.getEmail(),token)
         */

        return Utility.getResponse("User registered sucessfullly",registerDTO);
    }

    @Override
    @SneakyThrows
    public Response login(LoginDTO loginDTO) {
       if(userRepository.findByEmail(loginDTO.getEmail()).isPresent()){
           if(userRepository.findByPassword(loginDTO.getPassword()).isPresent()){
               System.out.println("login Successful");
           }
           else {
               throw new Exception("Invalid password");
           }

       }
        return Utility.getResponse("Login Successful",HttpStatus.OK);

    }

    public Response resetpassword(RestPasswordDTO restPasswordDTO, int id) throws Exception {

       if(userRepository.findById(id).isPresent()){

           userRepository.findById(id).get().getPassword();
           if((userRepository.findById(id)
                   .get()
                   .getPassword()
           ).equals(restPasswordDTO.getOldPassword())){
               userRepository.findById(id).get().setPassword(restPasswordDTO.getNewPasswrod());
               mailSender.sendEmail(From,userRepository.findById(id).get().getEmail());
           }
           else {
               throw new Exception("Incorrect password ");
           }

       }
       else {
           throw new Exception("ID not found");
       }
       return Utility.getResponse("password reset successful", HttpStatus.OK);

    }

    @Override
    @SneakyThrows
    public Response forgotPassword(int id) {
        User user=userRepository.findById(id).get();
        if(userRepository.findById(id).isPresent()){
            mailSender.forgotPasswordMail(From,user.getEmail(),user.getPassword());
        }
        else {
            throw new Exception("user id not found");
        }
        return Utility.getResponse("Password sent to user mail",HttpStatus.OK);

    }

    public List<AllUsers> getAllUser() {
        return userRepository.FindAllUSer();
    }

    public String confirmEmail(String confirmationToken)
    {
        userRepository.getById(tokenRepository.findByToken(confirmationToken).get().getId()).setVerified(true);
        userRepository.save(userRepository.getById(tokenRepository.findByToken(confirmationToken).get().getId()));
        return "Verified Successfully";
    }

}
