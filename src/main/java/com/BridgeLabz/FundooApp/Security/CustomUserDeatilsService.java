package com.BridgeLabz.FundooApp.Security;


import com.BridgeLabz.FundooApp.Exception.ExceptionMessage;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
@Data
public class CustomUserDeatilsService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;

    private String email;
    private String passWord;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {

        if(Email.equals(email))
        {
            return new User(email,passWord,new ArrayList<>());
        }
        else
        {
            throw new ExceptionMessage("Invalid Credentials");
        }
    }
}
