package com.wemakeprice.assignment.util;

import com.wemakeprice.assignment.constants.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.httpclient.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.httpclient.HttpStatus;

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

    public static String priorityLetterSort(String body) {
        return body
                .chars()
                .mapToObj(c -> new Character((char)c))
                .sorted(new PriorityLetterSortComparator())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String numberSort(String body){
        return body
                .chars()
                .mapToObj(c -> new Character((char)c))
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());

    }

    public static String removeTag(String scrapBody){
        return REMOVE_TAG_PATTERN.matcher(scrapBody).replaceAll("");
    }

    public static String combineString(String s1, String s2){

        StringBuilder sb = new StringBuilder(s1.length() + s2.length());

        int i = 0;
        if(s1.length() < s2.length()){
            for (i = 0; i < s1.length(); i++) {
                sb.append(s1.charAt(i));
                sb.append(s2.charAt(i));
            }
            sb.append(s2.substring(i));
        }else{
            for (i = 0; i < s2.length(); i++) {
                sb.append(s1.charAt(i));
                sb.append(s2.charAt(i));
            }
            sb.append(s1.substring(i));
        }

        return sb.toString();
    }

    public static String  getHttpErrorMessage(int httpStatusCode){
        return "요청하신 URL주소 접속할 수 없습니다.(" + httpStatusCode + ":" + HttpStatus.getStatusText(httpStatusCode) +")";
    }

}
