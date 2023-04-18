package com.example.demo.business.impl.users;

import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.users.AddUserUseCase;
import com.example.demo.domain.AccessToken;
import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.domain.persistenceClasses.RolePersistence;
import com.example.demo.domain.persistenceClasses.UserPersistence;
import com.example.demo.persistence.repositories.RoleRepository;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public User AddUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPwd());

        UserPersistence up = UserPersistence.builder()
                .bank_account(user.getBankAccount())
                .email(user.getEmail())
                .pwd(encodedPassword)
                .username(user.getUsername())
                .build();

        UserPersistence up2 = userRepository.save(up);
        for(Role role : user.getUserRoles()) {
            RolePersistence rp = RolePersistence.builder()
                    .role(role.getRole())
                    .user(up2.getId())
                    .build();
            roleRepository.save(rp);
        }
        return user;
    }
    /*@Override
    public LoginResponse AddUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPwd());

        UserPersistence up = UserPersistence.builder()
                .bank_account(user.getBankAccount())
                .email(user.getEmail())
                .pwd(encodedPassword)
                .username(user.getUsername())
                .build();

        UserPersistence up2 = userRepository.save(up);
        for(Role role : user.getUserRoles()) {
            RolePersistence rp = RolePersistence.builder()
                    .role(role.getRole())
                    .user(up2.getId())
                    .build();
            roleRepository.save(rp);
        }

        //UserPersistence userPersistence = userRepository.findByUsername(up.getUsername());
        //UserPersistence userPersistence = UserPersistence.builder().build();
        Set<RolePersistence> rolePersistenceList = new HashSet<>();
        for(Role role : user.getUserRoles()) {
            rolePersistenceList.add(RolePersistence.builder()
                            .id((long) role.getId())
                            .role(role.getRole())
                            .user((long) role.getUser_id())
                    .build());
        }
        up2.setUserRoles(rolePersistenceList);
        String accessToken = generateAccessToken(up2);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private String generateAccessToken(UserPersistence user) {
        Long userId = user != null ? user.getId() : null;
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole())
                .toList();

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(userId)
                        .build());
        //return "return";
    }*/
}
