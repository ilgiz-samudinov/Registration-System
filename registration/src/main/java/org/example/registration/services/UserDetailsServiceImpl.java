package org.example.registration.services;

import lombok.RequiredArgsConstructor;
import org.example.registration.configs.MyUserDetails;
import org.example.registration.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(MyUserDetails::new)
                .orElseThrow(()->{
                    System.out.println("Пользователь не найден: " + username);
                    return new UsernameNotFoundException("Пользователь не найден: " + username);
                });
    }
}