package com.BridgeLabz.FundooApp.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private int id;
    @Column(name = "First_Name",nullable = false,length = 20)
    private String firstName;
    @Column(name = "Last_Name",nullable = false,length = 20)
    private String lastName;
    @Column(name = "Email",nullable = false,unique = true,length = 45)
    private String email;
    @Column(name = "Password",nullable = false,length = 80)
    private String password;

    @OneToMany(targetEntity = Notes.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userID_FK",referencedColumnName ="User_id")
    List<Notes> notes;
}
