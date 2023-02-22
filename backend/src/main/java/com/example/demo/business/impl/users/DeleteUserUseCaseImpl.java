package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.DeleteUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public User DeleteUser(int index) {
        return userRepository.DeleteUser(index);
    }
}
