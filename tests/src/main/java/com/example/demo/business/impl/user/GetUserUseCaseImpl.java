package com.example.demo.business.impl.user;

import com.example.demo.business.cases.user.GetUserUseCase;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    /**
     * @param index
     * @return
     */
    @Override
    public User getUser(int index) {

        Optional<UserPersistence> up = userRepository.findById(Long.valueOf(index));
        if (up.isEmpty()) {

        }

        User user = User.builder()
                .id(Math.toIntExact(up.get().getId()))
                .username(up.get().getUsername())
                .pwd(up.get().getPwd())
                .bankAccount(up.get().getBankAccount())
                .email(up.get().getEmail())
                .deleted(up.get().getDeleted())
                .build();

        Set<Role> userRoles = new HashSet<>();
        up.get().getUserRoles().forEach(role -> {
            Role newRole = Role.builder()
                    .id(Math.toIntExact(role.getId()))
                    .role(role.getRole())
                    .userId(Math.toIntExact(role.getUser()))
                    .build();
            userRoles.add(newRole);
        });

        user.setUserRoles(userRoles);
        return user;
    }
}
