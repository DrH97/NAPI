package com.ics.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Dr H
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min=2, message="ID Number should have at least 2 characters")
    @Column(name = "id_number", nullable = false, unique = true)
    private String idNumber;

    @NotNull
    @Size(min=2, message="Name should have at least 2 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Movie> suggestedMovies;

    private User() {
    }

    public User(String idNumber) {
        this.idNumber = idNumber;
    }

    public User(String idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idNumber='" + idNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Movie> getSuggestedMovies() {
        return suggestedMovies;
    }

    public void setSuggestedMovies(List<Movie> movies) {
        this.suggestedMovies = movies;
    }
}
