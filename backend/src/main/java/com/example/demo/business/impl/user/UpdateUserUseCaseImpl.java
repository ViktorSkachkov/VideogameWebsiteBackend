package com.example.demo.business.impl.user;

import com.example.demo.business.cases.AccessTokenEncoder;
import com.example.demo.business.cases.user.UpdateUserUseCase;
import com.example.demo.domain.AccessToken;
import com.example.demo.domain.LoginResponse;
import com.example.demo.domain.User;
import com.example.demo.persistence.entity.UserPersistence;
import com.example.demo.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final AccessTokenEncoder accessTokenEncoder;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param user
     * @return
     */
    @Override
    public LoginResponse updateUser(User user) {
        Optional<UserPersistence> up = userRepository.findById(Long.valueOf(user.getId()));

        if (up.isEmpty()) {

        }
        up.get().setUsername(user.getUsername());
        up.get().setEmail(user.getEmail());
        up.get().setBankAccount(user.getBankAccount());
        if (!user.getPwd().equals("")) {
            String encodedPassword = passwordEncoder.encode(user.getPwd());
            up.get().setPwd(encodedPassword);
        }

        UserPersistence userPersistence = userRepository.save(up.get());

        String accessToken = generateAccessToken(userPersistence);
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
    }
}
