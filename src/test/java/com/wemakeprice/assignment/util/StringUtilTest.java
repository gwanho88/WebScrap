package com.wemakeprice.assignment.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class StringUtilTest {

    static Stream<Arguments> alphabetsSampleData() {
        return Stream.of(
                Arguments.of("영문+숫자","askdfjiwejf1289", "askdfjiwejf"),
                Arguments.of("영문+숫자+특수","a11udui#23fu&89f2j3", "auduifufj"),
                Arguments.of("특수문자+영문","@(*JEjawA#*E(#JASD", "JEjawAEJASD")
        );
    }

    @DisplayName("영문자 추출 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("alphabetsSampleData")
    public void getAlphabetsTest(String name, String s1, String result) throws Exception {
       Assertions.assertEquals(result, StringUtil.getAlphabets(s1));
    }

    static Stream<Arguments> numbersSampleData() {
        return Stream.of(
                Arguments.of("영문+숫자","askdfjiwejf1289", "1289"),
                Arguments.of("영문+숫자+특수","a11udu%i23fu8#9f2$j3", "11238923"),
                Arguments.of("특수문자+영문","@(*JEjawA#*E(#JASD", "")
        );
    }

    @DisplayName("숫자 추출 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("numbersSampleData")
    public void getNumbersTest(String name, String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.getNumbers(s1));
    }

    static Stream<Arguments> upperCaseLetterPrioritySortSampleData() {
        return Stream.of(
                Arguments.of("영문 case1","aAAswsiqSDIF", "AAaDFIiqSssw"),
                Arguments.of("영문 case2","IIKSSiwqlkao", "aIIiKkloqSSw"),
                Arguments.of("영문+숫자", "aA2As1ws3iqSDI21F", "AAaDFIiqSssw"),
                Arguments.of("영문+숫자+특수","I1@IKS3#Si4$wql5kao", "aIIiKkloqSSw"),
                Arguments.of("영문+특수","qi@#wloa$SQK`Wl", "aiKlloQqSWw")
        );
    }

    @DisplayName("영문자 중 대문자 우선순위 정렬 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("upperCaseLetterPrioritySortSampleData")
    public void upperCaseLetterPrioritySortTest(String name, String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.upperCaseLetterPriorityAndAscendingSort(s1));
    }

    static Stream<Arguments> numberSortSampleData() {
        return Stream.of(
                Arguments.of("숫자오름차순1","2839172602829", "0122223678899"),
                Arguments.of("숫자오름차순2","21902498271", "01122247899"),
                Arguments.of("영문+특수","28@3917$260#2829", "0122223678899"),
                Arguments.of("영문+숫자","21902s498d271", "01122247899"),
                Arguments.of("영문+숫자+특수","1@6208s129a03$89a401", "000111223468899")
        );
    }

    @DisplayName("숫자 정렬 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("numberSortSampleData")
    public void numberSortTest(String name, String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.numberSort(s1));
    }

    static Stream<Arguments> removeTagSampleData() {
        return Stream.of(
                Arguments.of("tag only","<htm<body></body></html>", ""),
                Arguments.of("tag+숫자+영문","<html>12d<body>v23sd</body>12das</html>", "12dv23sd12das"),
                Arguments.of("tag+숫자+영문+특수","<html>1@!2d<body>v#D!23sd</body>12dDEas</html>", "1@!2dv#D!23sd12dDEas"),
                Arguments.of("잘못된 tag","ml><body>dy></html>", "ml>dy>"),
                Arguments.of("잘못된 tag+숫자+영문","ml>12d<body>dody>12das</html>", "ml>12ddody>12das"),
                Arguments.of("잘못된 tag+숫자+영문+특수","<html21d<b6^o%dy</bo#d4r2</html>", "")
        );
    }

    @DisplayName("html tag 제거 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("removeTagSampleData")
    public void removeTagTest(String name, String s1, String result) {
        Assertions.assertEquals(result, StringUtil.removeTag(s1));
    }

    static Stream<Arguments> combineStringSampleData() {
        return Stream.of(
                Arguments.of("영문+숫자","askdfjiwejf","0122223678899", "a0s1k2d2f2j2i3w6e7j8f899"),
                Arguments.of("(영+숫+특),(숫+특)","askd82fjiw2@ejf", "0122#(@223!@67#8899", "a0s1k2d28#2(f@j2i2w32!@@e6j7f#8899"),
                Arguments.of("(영+숫+특),(숫+특+)","a%udui3@fu4fj", "01#1%22a2d47899", "a0%1u#d1u%i232@af2ud44f7j899")
        );
    }

    @DisplayName("영문 숫자 순차적 문자열 결합 테스트")
    @ParameterizedTest(name = "{index}. {0}")
    @MethodSource("combineStringSampleData")
    public void combineStringTest(String name, String s1, String s2, String result) {
        Assertions.assertEquals(result, StringUtil.combineString(s1,s2));
    }
}
