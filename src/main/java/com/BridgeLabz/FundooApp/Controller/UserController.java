package com.BridgeLabz.FundooApp.Controller;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Service.IUSerService;
import com.BridgeLabz.FundooApp.Service.UserServiceImpl;
import com.BridgeLabz.FundooApp.Utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder;


    @GetMapping("/homepage")
    public String viewHomepage() {
        return "welcome";
    }

    @PostMapping("/register")
    public Response newSignUp(@RequestBody RegisterDTO registerDTO) {
        return iuSerService.registration(registerDTO);
    }

    @GetMapping("/confirm-email")
    public String confirmUser(@RequestParam("token") String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/resetPassword/{id}")
    public Response resetPassword(@RequestBody RestPasswordDTO request, @PathVariable int id) throws Exception {
        return iuSerService.resetpassword(request, id);
    }

    @PostMapping("/forgot-password/{id}")
    public Response forgotPassword(@PathVariable int id) throws Exception {
        return userService.forgotPassword(id);
    }

    @GetMapping("/fetchAllUSer")
    public List<AllUsers> getAll() {
        return iuSerService.getAllUser();
    }


}
