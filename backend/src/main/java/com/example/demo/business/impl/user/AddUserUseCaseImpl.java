package com.example.demo.business.impl.user;

import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.user.AddUserUseCase;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.RolePersistence;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    /**
     * @param user
     * @return
     */
    @Override
    public User addUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPwd());

        UserPersistence up = UserPersistence.builder()
                .bank_account(user.getBankAccount())
                .email(user.getEmail())
                .pwd(encodedPassword)
                .username(user.getUsername())
                .deleted(false)
                .build();

        UserPersistence up2 = userRepository.save(up);
        for (Role role : user.getUserRoles()) {
            RolePersistence rp = RolePersistence.builder()
                    .role(role.getRole())
                    .user(up2.getId())
                    .build();
            roleRepository.save(rp);
        }
        return user;
    }
}
