package com.BridgeLabz.FundooApp.Security;

import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDeatilsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email).get();
        if(user==null){
            throw  new UsernameNotFoundException("User Not found");
        }

        return new CustomerUserDeatils(user);
    }
}
