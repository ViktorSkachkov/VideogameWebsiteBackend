package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.UpdateUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User UpdateUser(User user) {
        return userRepository.UpdateUser(user);
    }
}
