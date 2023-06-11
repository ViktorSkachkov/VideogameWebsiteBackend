package com.example.demo.business.impl.user;

import com.example.demo.business.cases.user.ValidatePasswordUseCase;
import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidatePasswordUseCaseImpl implements ValidatePasswordUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ValidationResponse validatePassword(String password) {
        List<String> passwords = userRepository.findAllPasswords(false);
        for(String pwd : passwords) {
            if(matchesPassword(password, pwd)) {
                return ValidationResponse.builder()
                        .confirm(true)
                        .build();
            }
        }
        return ValidationResponse.builder()
                .confirm(false)
                .build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
