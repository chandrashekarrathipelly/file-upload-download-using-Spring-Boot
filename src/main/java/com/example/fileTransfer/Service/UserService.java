package com.example.fileTransfer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.fileTransfer.Dto.AuthRequest;
import com.example.fileTransfer.Dto.AuthResponse;
import com.example.fileTransfer.Repositories.UserRepository;
import com.example.fileTransfer.entity.User;
import com.example.fileTransfer.jwt.JwtService;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username);
    }

    public AuthResponse login(AuthRequest authRequest) {
        String username=authRequest.getUsername();
        UserDetails userDetails=loadUserByUsername(username);
        boolean isPasswordSame=this.encoder.matches(authRequest.getPassword(), userDetails.getPassword());
        if(isPasswordSame){
            return  AuthResponse.builder().token(jwtService.generateToken(username)).build();
        }
        throw new ResponseStatusException(HttpStatusCode.valueOf(404),"User not Found");
    }

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    
    


}
