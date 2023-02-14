package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.GetUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    public GetUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User GetUser(int index) {
        return userRepository.GetUser(index);
    }
}
