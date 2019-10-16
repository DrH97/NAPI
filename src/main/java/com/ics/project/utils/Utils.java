package com.ics.project.utils;

import com.ics.project.models.Category;

import java.util.Collections;
import java.util.List;

public class Utils {

    static List<Category> getRandomCategories(List<Category> categories, int items) {
        Collections.shuffle(categories);

        return categories.subList(0, items);
    }

    public static void print(String message, String ...extras) {
        System.out.println(message);

        for (String extra: extras) {
            System.out.println("\t" + extra);
        }
    }
}
