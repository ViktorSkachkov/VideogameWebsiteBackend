package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.DeleteUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User DeleteUser(int index) {
        return userRepository.DeleteUser(index);
    }
}
