package com.nitin.springdatajpa.manytomany.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tags")
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tags")
    private Set<BlogPost> blogPosts = new HashSet<>();
}
