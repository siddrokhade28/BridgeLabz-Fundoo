package com.BridgeLabz.FundooApp.Service;

import com.BridgeLabz.FundooApp.DTO.AllUsers;
import com.BridgeLabz.FundooApp.DTO.LoginDTO;
import com.BridgeLabz.FundooApp.DTO.RegisterDTO;
import com.BridgeLabz.FundooApp.DTO.RestPasswordDTO;
import com.BridgeLabz.FundooApp.Utility.Response;

import java.util.List;

public interface IUSerService {
    public Response registration(RegisterDTO registerDTO);

    public Response resetpassword(RestPasswordDTO restPasswordDTO, int id);

    public Response forgotPassword(int id);

    public List<AllUsers> getAllUser();

    Response login(LoginDTO loginDTO);
}
