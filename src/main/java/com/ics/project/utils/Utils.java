package com.ics.project.utils;

import com.ics.project.models.Category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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

    public static String titleCaseConversion(String inputString) {
        if (inputString.isBlank()) {
            return "";
        }

        if (inputString.length() == 1) {
            return inputString.toUpperCase();
        }

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

        Stream.of(inputString.split(" ")).forEach(stringPart ->
        {
            if (stringPart.length() > 1)
                resultPlaceHolder.append(stringPart.substring(0, 1)
                        .toUpperCase())
                        .append(stringPart.substring(1)
                                .toLowerCase());
            else
                resultPlaceHolder.append(stringPart.toUpperCase());

            resultPlaceHolder.append(" ");
        });
        return resultPlaceHolder.toString().trim();
    }
}
