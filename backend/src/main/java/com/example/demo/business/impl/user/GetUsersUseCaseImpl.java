package com.example.demo.business.impl.user;

import com.example.demo.business.cases.user.GetUsersUseCase;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.domain.persistenceClass.RolePersistence;
import com.example.demo.persistence.domain.persistenceClass.UserPersistence;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private final UserRepository userRepository;

    /**
     *
     * @return
     */
    @Override
    public List<User> getUsers() {
        List<UserPersistence> list = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(UserPersistence up : list) {
            Set<Role> roles = new HashSet<>();
            for(RolePersistence rp : up.getUserRoles()) {
                roles.add(Role.builder()
                        .id(Math.toIntExact(rp.getId()))
                        .role(rp.getRole())
                        .user_id(Math.toIntExact(rp.getUser()))
                        .build());
            }

            User user = User.builder()
                    .id(Math.toIntExact(up.getId()))
                    .username(up.getUsername())
                    .pwd(up.getPwd())
                    .bankAccount(up.getBank_account())
                    .userRoles(roles)
                    .email(up.getEmail())
                    .build();
            users.add(user);
        }
        return users;
    }
}
