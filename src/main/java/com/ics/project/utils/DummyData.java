package com.ics.project.utils;

import com.ics.project.repositories.CategoryRepository;
import com.ics.project.repositories.MovieRepository;
import com.ics.project.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DummyData implements CommandLineRunner {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final MovieRepository movieRepo;

    private Random rand = new Random();

    public DummyData(UserRepository userRepo, CategoryRepository categoryRepo, MovieRepository movieRepo) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public void run(String... args) throws Exception {
//        List<User> users = new ArrayList<>();
//        users.add(new User("092879", "Jose"));
//        users.add(new User("090002", "Sandra"));
//        users.add(new User("090003", "Brian"));
//
//        List<User> savedUsers = userRepo.saveAll(users);
//
//        List<Category> categories = Arrays.asList(
//                new Category("adventure"),
//                new Category("sci-fi"),
//                new Category("thriller"),
//                new Category("fantasy")
//        );
//
//        List<Category> savedCategories = categoryRepo.saveAll(categories);
//
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie("Jumanji", "A jungle in the wilderness", Utils.getRandomCategories(categories, 1), MovieType.ORIGINAL));
//        movies.add(new Movie("Joker", "Why so serious???", Utils.getRandomCategories(categories, 2), MovieType.ORIGINAL));
//        movies.add(new Movie("Earth 22", "Like what even.", Utils.getRandomCategories(categories, 3), MovieType.SUGGESTED, savedUsers.get(rand.nextInt(savedUsers.size()))));
//
//        List<Movie> savedMovies = movieRepo.saveAll(movies);
//
//        Utils.print(savedUsers.get(0).toString());
//
//        Utils.print(savedCategories.get(0).toString());
//
//        Utils.print(savedMovies.get(0).toString(), savedMovies.get(1).toString());
    }
}
