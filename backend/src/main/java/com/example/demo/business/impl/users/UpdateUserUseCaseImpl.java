package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.UpdateUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public User UpdateUser(User user) {
        return userRepository.UpdateUser(user);
    }
}
