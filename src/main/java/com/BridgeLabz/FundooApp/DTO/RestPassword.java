package com.BridgeLabz.FundooApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPassword {
    private String oldpassword;
    private String newpasswrod;

}
