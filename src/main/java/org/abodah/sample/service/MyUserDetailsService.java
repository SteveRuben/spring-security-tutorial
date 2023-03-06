package org.abodah.sample.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abodah.sample.entities.User;
import org.abodah.sample.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username); // 1
                                                            // 2
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
