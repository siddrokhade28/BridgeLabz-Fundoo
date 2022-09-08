package com.BridgeLabz.FundooApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
DTO Class for Reset the password
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPasswordDTO {
    private String newPasswrod;
    private String confirmPassword;

}
