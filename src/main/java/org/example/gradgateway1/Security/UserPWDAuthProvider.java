package org.example.gradgateway1.Security;

import org.example.gradgateway1.DAO.UserRepository;
import org.example.gradgateway1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class UserPWDAuthProvider implements AuthenticationProvider {

    private  final UserRepository userRepository;

    private    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserPWDAuthProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Optional<User> userOptional = userRepository.findByEmail(email);
    if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userOptional.get().getRole()));

        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }
    else {
        throw new BadCredentialsException("Authentication failed.");
    }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
