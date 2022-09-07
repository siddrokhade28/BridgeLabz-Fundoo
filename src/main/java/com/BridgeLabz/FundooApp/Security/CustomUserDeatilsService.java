package com.BridgeLabz.FundooApp.Security;


import com.BridgeLabz.FundooApp.Model.User;
import com.BridgeLabz.FundooApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDeatilsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> admin = userRepository.findByEmail(email);
        return admin.map(CustomUserDeatils::new).
                orElseThrow(() -> new UsernameNotFoundException(email + " Email not registered"));
    }
}
