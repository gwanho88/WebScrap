package com.wemakeprice.assignment.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.httpclient.HttpStatus;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    static final Pattern NOT_NUMBER_PATTERN = Pattern.compile("[^0-9]");
    static final Pattern NOT_ALPHABET_PATTERN = Pattern.compile("[^a-zA-Z]");
    static final Pattern REMOVE_TAG_PATTERN = Pattern.compile("<[^>]*>");


    public static String getAlphabets(String body){
        return NOT_ALPHABET_PATTERN.matcher(body).replaceAll("");
    }

    public static String getNumbers(String body){
        return NOT_NUMBER_PATTERN.matcher(body).replaceAll("");
    }

    /**
     * 영문 오름차순 및 대문자 우선정렬(ex AaAaBcD.. -> AAaaBcD..)
     * @param body
     * @return String
     */
    public static String upperCaseLetterPriorityAndAscendingSort(String body) {
        String alphabet =  NOT_ALPHABET_PATTERN.matcher(body).replaceAll("");
        return alphabet
                .chars()
                .mapToObj(c -> (char)c)
                .sorted(new UpperCaseLetterPrioritySortComparator())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String numberSort(String body){
        String numbers = NOT_NUMBER_PATTERN.matcher(body).replaceAll("");
        return numbers
                .chars()
                .mapToObj(c -> (char)c)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());

    }

    public static String removeTag(String scrapBody){
        return REMOVE_TAG_PATTERN.matcher(scrapBody).replaceAll("");
    }

    public static String combineString(String s1, String s2){

        StringBuilder sb = new StringBuilder(s1.length() + s2.length());
        String smaller = s1.length() < s2.length() ? s1 : s2;
        String larger = s1.length() > s2.length() ? s1 : s2;

        int i = 0;
        for (i = 0; i < smaller.length(); i++) {
            sb.append(s1.charAt(i));
            sb.append(s2.charAt(i));
        }

        sb.append(larger.substring(i));


        return sb.toString();
    }
}
