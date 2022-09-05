package com.BridgeLabz.FundooApp.Controller;

import com.BridgeLabz.FundooApp.DTO.Login;
import com.BridgeLabz.FundooApp.DTO.RestPassword;
import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fundoo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/homepage")
    public  String viewHomepage(){
        return "welcome";
    }

    @PostMapping("/register")
    public String newSignUp(@RequestBody User user){
        userService.signUp(user);
        return "sign up sucessfull";
    }

    @PostMapping("/login")
    public String Login(@RequestBody Login login){
        userService.login(login);
        return "login successful";
    }

//    @PostMapping("/forgetPassword")
//    public String forgotPassword(String password){
//        String presentpassword=userService.forgotpassword(password);
//        return presentpassword;
//    }

    @PostMapping("/resetPassword/{id}")
    public String resetPassword(@RequestBody RestPassword request,@PathVariable int id) throws Exception {
        userService.resetpassword(request,id);
        return "the password is been reset";
    }

    @GetMapping("/fetchAllUSer")
    public List<User> getAll(){
        return userService.getAllUser();
    }





}
