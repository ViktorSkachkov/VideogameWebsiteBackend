package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.GetUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    @Override
    public User GetUser(int index) {
        Optional<UserPersistence> up = userRepository.findById(Long.valueOf(index));
        if(up.isEmpty()) {

        }
        User user = User.builder()
                .id(Math.toIntExact(up.get().getId()))
                .username(up.get().getUsername())
                .pwd(up.get().getPwd())
                .bankAccount(up.get().getBank_account())
                .role(up.get().getRole())
                .email(up.get().getEmail())
                .build();
        return user;
    }
}
