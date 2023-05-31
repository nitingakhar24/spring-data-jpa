package com.nitin.springdatajpa.manytomany.repository;

import com.nitin.springdatajpa.manytomany.entity.BlogPost;
import com.nitin.springdatajpa.manytomany.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BlogPostRepositoryTest {
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Test
    @Rollback(value = false)
    void saveBlogPost_withMultipleTags() {
        final BlogPost springBootAndHibernateManyToManyPost = BlogPost.builder()
                .description("Spring Data JPA and Hibernate Post with Many to Many description")
                .title("Spring Data and Hibernate many-many title")
                .content("This is a long content regarding Spring Data and Hibernate JPA Many to Many example")
                .build();

        final BlogPost springBootAndHibernateGeneralPost = BlogPost.builder()
                .description("Spring Data JPA and Hibernate general post description")
                .title("Spring Data and Hibernate general title")
                .content("This is a long content regarding Spring Data and Hibernate JPA general example")
                .build();

        final Tag springDataJPATag = Tag.builder().
                name("#SpringDataJPAManyToMany").
                build();
        final Tag hibernateTag = Tag.builder().
                name("#HibernateManyToMany").
                build();

        springBootAndHibernateManyToManyPost.setTags(Set.of(springDataJPATag, hibernateTag));
        springBootAndHibernateGeneralPost.setTags(Set.of(springDataJPATag));

        blogPostRepository.saveAll(List.of(springBootAndHibernateManyToManyPost, springBootAndHibernateGeneralPost));

        assertThat(springBootAndHibernateManyToManyPost).isNotNull();
        assertThat(springBootAndHibernateManyToManyPost.getId()).isPositive();

        assertThat(springBootAndHibernateGeneralPost).isNotNull();
        assertThat(springBootAndHibernateGeneralPost.getId()).isPositive();
    }

}