package com.BridgeLabz.FundooApp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ConfirmationToken")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private int id;

    @Column(name = "Token")
    private String token;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_USer_id")
    private User users;

    public ConfirmationToken(String token, User users) {
        this.token = token;
        this.users = users;
    }
}
