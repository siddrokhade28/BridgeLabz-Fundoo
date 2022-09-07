package com.BridgeLabz.FundooApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUsers {
    private String firstName;
    private String lastName;
    private String email;
}
