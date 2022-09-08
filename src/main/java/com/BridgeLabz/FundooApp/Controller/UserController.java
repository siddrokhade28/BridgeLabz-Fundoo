package com.BridgeLabz.FundooApp.Controller;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Model.Notes;
import com.BridgeLabz.FundooApp.Service.IUSerService;
import com.BridgeLabz.FundooApp.Service.UserServiceImpl;
import com.BridgeLabz.FundooApp.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUSerService iuSerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    /*
    api for home page
     */
    @GetMapping("/homepage")
    public String viewHomepage() {
        return "welcome";
    }

    /*
    API to register the user
     */
    @PostMapping("/register")
    public Response newSignUp(@RequestBody RegisterDTO registerDTO) {
        return iuSerService.registration(registerDTO);
    }

    /*
    API to confirm the mail id;
     */
    @GetMapping("/confirm-email")
    public String confirmUser(@RequestParam("token") String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }

    /*
    API for Login
     */
    @PostMapping("/login")
    public Response login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    /*
    API to reset password when forgotten
     */
    @PostMapping("/resetPasswordByToken")
    public Response resetPasswordByToken(@RequestBody RestPasswordDTO restPasswordDTO, @RequestParam String token) throws Exception {
        return iuSerService.resetPasswordByToken(restPasswordDTO, token);
    }

    /*
    API to reset password
     */
    @PostMapping("/resetPassword")
    public Response resetPassword(@RequestBody RestPasswordDTO restPasswordDTO, @RequestParam String email) throws Exception {
        return iuSerService.resetpassword(restPasswordDTO, email);
    }

    /*
    API when User forgets password
     */
    @PostMapping("/forgot-password")
    public Response forgotPassword(@RequestParam String email) throws Exception {
        return userService.forgotPassword(email);
    }

    /*
    API to Fetch All Users
     */
    @GetMapping("/fetchAllUSer")
    public List<AllUsers> getAll() {
        return iuSerService.getAllUser();
    }


}
