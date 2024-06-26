package org.example.gradgateway1.Util;

import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationDetails {


    private final UserRepository userRepository;

    @Autowired
    public AuthenticationDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }

}

