package com.ics.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ics.project.utils.MovieType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "movies")
    private List<Category> categories;

    @Column(name = "type")
    private MovieType movieType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Movie() {
    }

    public Movie(String title, String description, List<Category> categories, User user) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.user = user;
    }

    public Movie(String title, String description, List<Category> categories, MovieType movieType, User user) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.movieType = movieType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categories=" + categories +
                ", movieType=" + movieType +
                ", user=" + user +
                '}';
    }
}
