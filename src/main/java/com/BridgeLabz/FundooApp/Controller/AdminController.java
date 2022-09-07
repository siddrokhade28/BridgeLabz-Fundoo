//package com.BridgeLabz.FundooApp.Controller;
//
//import com.BridgeLabz.FundooApp.Model.Admin;
//import com.BridgeLabz.FundooApp.Model.User;
//import com.BridgeLabz.FundooApp.Repository.AdminRepository;
//import com.BridgeLabz.FundooApp.Repository.UserRepository;
//import com.BridgeLabz.FundooApp.Service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.PostConstruct;
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    private static final String ADMIN_ROLE  = "ADMIN";
//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostConstruct
//    public void initRolesAndUser(){
//        adminService.initRolesAndUser();
//    }
//
//
//
//    //only admin can register another admin
//
//    /*
//    Princal will always give the name of logged in Admin.
//     */
//    @PostMapping("/access")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public User giveAccess(@RequestParam int user_id,Principal principal){
//        User user=userRepository.findById(user_id).get();
//        String activeRole = getRoleByloggedInUSer(principal);
//        if(activeRole.contains("ADMIN")){
//            user.setRole("ADMIN");
//        }
//       return userRepository.save(user);
////        admin.setRole(ADMIN_ROLE);
////        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
////        return adminService.giveAccess(admin);
//    }
//
//
//    private Admin getLoggedInAdmin(Principal principal){
//        return adminRepository.findByEmail(principal.getName()).get();
//    }
//
//    private String getRoleByloggedInUSer(Principal principal){
//       String role= getLoggedInAdmin(principal).getRole();
//       if(role.equals("ADMIN")){
//
//           return ADMIN_ROLE;
//       }
//       return principal.getName()+"Do not have Permission to Grant Access";
//    }
//
//}
