package com.nitin.springdatajpa.repository;

import com.nitin.springdatajpa.onetomany.entity.Comment;
import com.nitin.springdatajpa.onetomany.entity.Post;
import com.nitin.springdatajpa.onetomany.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Annotate the specific test with @Rollback(false) inorder to persist the changes in DB
 *  import org.springframework.test.annotation.Rollback;
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    //@Rollback(value = false)
    void savePost_withMultipleComments() {
        final Post post = Post.builder()
                .description("Spring Data JPA Post with One to Many example")
                .title("Spring Data 1 to many")
                .build();
        final Comment comment1 = Comment.builder().
                text("Quite useful post").build();
        final Comment comment2 = Comment.builder().
                text("Informative").build();
        final Comment comment3 = Comment.builder().
                text("Would be good to add few details in the read me file").build();

        post.setComments(List.of(comment1, comment2, comment3));

        postRepository.save(post);

        assertThat(post).isNotNull();
        assertThat(post.getId()).isPositive();
    }
}