package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.AddUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserRepository userRepository;

    public AddUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User AddUser(User user) {
        return userRepository.AddUser(user);
    }
}
