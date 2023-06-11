package com.example.demo.business.impl.user;

import com.example.demo.business.cases.user.ValidateUsernameUseCase;
import com.example.demo.domain.ValidationResponse;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateUsernameUseCaseImpl implements ValidateUsernameUseCase {
    private final UserRepository userRepository;

    @Override
    public ValidationResponse validateUsername(String username) {
        List<String> usernames = userRepository.findAllUsernames(false);

        if(usernames.contains(username)) {
            return ValidationResponse.builder()
                    .confirm(true)
                    .build();
        }
        else {
            return ValidationResponse.builder()
                    .confirm(false)
                    .build();
        }
    }
}
