package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Utility.Response;

import java.util.List;

// interface for User operation

public interface IUSerService {
    public Response registration(RegisterDTO registerDTO);

    public Response resetpassword(RestPasswordDTO restPasswordDTO, String email);

    public Response forgotPassword(String email);

    public List<AllUsers> getAllUser();

    Response login(LoginDTO loginDTO);

    public String confirmEmail(String confirmationToken);

    public Response resetPasswordByToken(RestPasswordDTO restPasswordDTO, String token);
}
