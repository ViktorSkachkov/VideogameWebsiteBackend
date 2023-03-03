package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.UpdateUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public User UpdateUser(User user) {
        Optional<UserPersistence> up = userRepository.findById(Long.valueOf(user.getId()));
        if(up.isEmpty()) {

        }
        up.get().setUsername(user.getUsername());
        up.get().setEmail(user.getEmail());
        up.get().setPwd(user.getPwd());
        up.get().setBank_account(user.getBankAccount());
        up.get().setRole(user.getRole());
        userRepository.save(up.get());
        return user;
    }
}
