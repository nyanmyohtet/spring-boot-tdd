package com.nyanmyohtet.tdd.service;

import com.nyanmyohtet.tdd.model.User;
import com.nyanmyohtet.tdd.persistence.UserRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void register_createsUser_andReturnsSavedUser() {
        // Arrange
        given(userRepository.existsByEmail("a@b.com")).willReturn(false);
        given(userRepository.save(any())).willAnswer(inv -> {
            User u = inv.getArgument(0, User.class);
            u.setId(42L);
            return u;
        });

        // Act
        User result = userService.register("a@b.com", "secret");

        // Assert
        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.getEmail()).isEqualTo("a@b.com");

        // Verify interactions
        then(userRepository).should().existsByEmail("a@b.com");

        // Capture arguments to assert more precisely
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        then(userRepository).should().save(userCaptor.capture());
        User toSave = userCaptor.getValue();
        assertThat(toSave.getEmail()).isEqualTo("a@b.com");
    }
}
