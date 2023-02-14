package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.GetUsersUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> GetUsers() {
        return userRepository.GetUsers();
    }
}
