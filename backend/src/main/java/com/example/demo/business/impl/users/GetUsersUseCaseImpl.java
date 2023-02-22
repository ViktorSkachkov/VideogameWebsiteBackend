package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.GetUsersUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    @Override
    public List<User> GetUsers() {
        return userRepository.GetUsers();
    }
}
