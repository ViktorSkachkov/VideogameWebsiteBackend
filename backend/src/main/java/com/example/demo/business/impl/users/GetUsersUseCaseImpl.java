package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.GetUsersUseCase;
import com.example.demo.domain.User;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    @Override
    public List<User> GetUsers() {
        List<UserPersistence> list = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(UserPersistence up : list) {
            User user = User.builder()
                    .id(Math.toIntExact(up.getId()))
                    .username(up.getUsername())
                    .pwd(up.getPwd())
                    .bankAccount(up.getBank_account())
                    .role(up.getRole())
                    .email(up.getEmail())
                    .build();
            users.add(user);
        }
        return users;
        //return userRepository.GetUsers();
    }
}
