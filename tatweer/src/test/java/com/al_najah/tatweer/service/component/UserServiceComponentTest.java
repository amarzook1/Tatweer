package com.al_najah.tatweer.service.component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.al_najah.tatweer.config.BaseComponentTest;
import com.al_najah.tatweer.dto.user.UserCreateDTO;
import com.al_najah.tatweer.entity.User;
import com.al_najah.tatweer.repository.UserRepository;
import com.al_najah.tatweer.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceComponentTest extends BaseComponentTest {

  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @Test
  void testCreateUser() {
    UserCreateDTO userCreateDTO =
        new UserCreateDTO("testuser", "Ahmed", "Marzook", "test@example.com");

    userService.addNewUser(userCreateDTO);
    Optional<User> user = userRepository.findByEmail("test@example.com");

    assertThat(user)
            .isPresent()
            .hasValueSatisfying(u -> {
              assertThat(u.getUsername()).isEqualTo("testuser");
              assertThat(u.getEmail()).isEqualTo("test@example.com");
            });
  }
}
