package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class SimilarityModel
{
    /**
     * Returns the most similar string in the array
     * @param source the string we want to compare
     * @param array thea array of strings we want to compare to
     * @return the most similar string
     */
    public static String findMostSimilarString(String source, String[] array)
    {
        double[] similarityRatings = new double[array.length];
        double maximum = 0;
        int indexOfMaximum = 0;
        for (int i = 0; i < array.length; i++)
        {
            similarityRatings[i] = calculateSimilarity(source.toLowerCase(), array[i].toLowerCase());
            if (similarityRatings[i] > maximum)
            {
                maximum = similarityRatings[i];
                indexOfMaximum = i;
            }
        }

        return array[indexOfMaximum];
    }

    /**
     * Calculate the similarity between two strings
     * @param source first string to compare
     * @param target second string to compare
     * @return the similarity rating
     */
    private static double calculateSimilarity(String source, String target)
    {
        if (source == null || target == null)
        {
            return 0.0;
        }

        if (source.length() == 0 || target.length() == 0)
        {
            return 0.0;
        }

        if (source.equals(target))
        {
            return 1.0;
        }

        int stepsToSame = computeLevenshteinDistance(source, target);

        return (1.0 - ((double) stepsToSame / (double) Math.max(source.length(), target.length())));
    }

    /**
     * Calculate Levenshtein distance
     * @param source first string to compare
     * @param target second string to compare
     * @return the number of chars to change before the two strings are the same
     */
    public static int computeLevenshteinDistance(String source, String target) {
        if (source == null || target == null) {
            return 0;
        }

        if (source.length() == 0 || target.length() == 0) {
            return 0;
        }

        if (source.equals(target)) {
            return source.length();
        }


        int sourceWordCount = source.length();
        int targetWordCount = target.length();

        if (sourceWordCount == 0) {
            return targetWordCount;
        }

        if (targetWordCount == 0) {
            return sourceWordCount;
        }

        int[][] distance = new int[sourceWordCount + 1][targetWordCount + 1];

        for (int i = 0; i <= sourceWordCount; distance[i][0] = i++) ;
        for (int j = 0; j <= targetWordCount; distance[0][j] = j++) ;

        for (int i = 1; i <= sourceWordCount; i++) {
            for (int j = 1; j <= targetWordCount; j++) {
                int cost = (target.charAt(j - 1) == source.charAt(i - 1) ? 0 : 1);

                distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1), distance[i - 1][j - 1] + cost);
            }
        }

        return distance[sourceWordCount][targetWordCount];
    }
}
