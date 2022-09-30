package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.*;
import com.BridgeLabz.FundooApp.Utility.Response;

import java.util.List;

// interface for User operation

public interface IUSerService {
    public Response registration(RegisterDTO registerDTO);

    public Response resetpassword(RestPasswordDTO restPasswordDTO);

    public Response forgotPassword(ForgotPasswordDTO email);

    Response login(LoginDTO loginDTO);

    public String confirmEmail(String confirmationToken);

}
