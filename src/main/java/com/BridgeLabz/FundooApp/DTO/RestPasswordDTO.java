package com.BridgeLabz.FundooApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPasswordDTO {
    private String oldPassword;
    private String newPasswrod;

}
