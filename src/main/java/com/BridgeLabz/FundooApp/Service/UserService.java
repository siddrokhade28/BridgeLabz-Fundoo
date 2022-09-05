package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.Login;
import com.BridgeLabz.FundooApp.DTO.RestPassword;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import com.BridgeLabz.FundooApp.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void signUp(User user) {
        userRepository.save(user);
    }

    public void getAllNotesBYId(int id){
        userRepository.findById(id);
    }

    public void login(Login login) {


    }

//    public String forgotpassword(String password) {
//        User pwd=userRepository.findByPassword(password).get();
//        return pwd.getPassword();
//    }

    public void resetpassword(RestPassword restPassword, int id) throws Exception {
        User user= userRepository.findById(id).get();
        if(!user.getPassword().equals(restPassword.getOldpassword())){
            throw  new Exception("Incorrect Password");
        }
//
        user.setPassword(restPassword.getNewpasswrod());
        userRepository.save(user);

    }

    public List<User> getAllUser() {
        return  userRepository.FindAllUSer();
    }
}
