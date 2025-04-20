package com.ray.raydemo.security;

import com.ray.raydemo.model.User;
import com.ray.raydemo.repository.UserRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Data
@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new MyUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() // 你也可以回傳角色權限
        );
    }
}
