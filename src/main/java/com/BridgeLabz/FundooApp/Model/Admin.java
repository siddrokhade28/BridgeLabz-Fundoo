//package com.BridgeLabz.FundooApp.Model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Entity(name = "Admin")
//public class Admin {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "Admin_id")
//    private int id;
//    @Column(name = "First_Name",nullable = false,length = 20)
//    private String firstName;
//    @Column(name = "Last_Name",nullable = false,length = 20)
//    private String lastName;
//    @Column(name = "Email",nullable = false,unique = true,length = 45)
//    private String email;
//    @Column(name = "Password",nullable = false,length = 80)
//    private String password;
//    @Column(name = "roles",nullable = false)
//    private String role;
//}
