package com.example.demo.business.impl.user;

import com.example.demo.business.cases.user.DeleteUserUseCase;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.AdditionOrderPersistence;
import com.example.demo.persistence.entity.GameOrderPersistence;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import com.example.demo.persistence.repository.GameOrderRepository;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * @param id
     * @return
     */
    @Override
    public User deleteUser(int id) {
        Optional<UserPersistence> up = userRepository.findById(Long.valueOf(id));
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
            roleRepository.deleteById(role.getId());
            Role newRole = Role.builder()
                    .id(Math.toIntExact(role.getId()))
                    .role(role.getRole())
                    .userId(Math.toIntExact(role.getUser()))
                    .build();
            userRoles.add(newRole);
        });
        user.setUserRoles(userRoles);

        up.get().setDeleted(true);
        userRepository.save(up.get());

        return user;
    }
}
