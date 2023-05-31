package com.nitin.springdatajpa.onetoone.repository;

import com.nitin.springdatajpa.onetoone.entity.Gender;
import com.nitin.springdatajpa.onetoone.entity.User;
import com.nitin.springdatajpa.onetoone.entity.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Annotate the specific test with @Rollback(false) inorder to persist the changes in DB
 *  import org.springframework.test.annotation.Rollback;
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    //@Rollback(value = false)
    void saveUser_withUserProfile() {
        final User user = User.builder()
                .email("test@gmail.com")
                .name("Test Name")
                .build();
        final UserProfile userProfile = UserProfile.builder()
                .gender(Gender.MALE)
                .phoneNumber("94834233423")
                .dateOfBirth(LocalDate.of(1988, 12, 12))
                .address("15 Asquith Ave, Toronto, ON, CA")
                .build();
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        userRepository.save(user);

        assertThat(user).isNotNull();
        assertThat(user.getId()).isPositive();
        assertThat(user.getUserProfile()).isNotNull();


    }

}