package com.ics.project.utils;

import com.ics.project.models.Category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Dr H
 */
public class Utils {

    /**
     * Method to get N number of random categories from a list of categories
     *
     * @param categories
     * @param items
     * @return list of categories
     */
    static List<Category> getRandomCategories(List<Category> categories, int items) {
        Collections.shuffle(categories);

        return categories.subList(0, items);
    }

    /**
     * Custom system println function that takes variable number of messages
     * @param message
     * @param extras
     */
    public static void print(String message, String ...extras) {
        System.out.println(message);

        for (String extra: extras) {
            System.out.println("\t" + extra);
        }
    }

    /**
     * Convert any string to a title cased string
     * @param inputString
     * @return title cased String
     */
    public static String titleCaseConversion(String inputString) {
        if (inputString.isEmpty()) {
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
