package com.wemakeprice.assignment.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest(classes = StringUtil.class)
class StringUtilTest {

    static Stream<Arguments> alphabetsSampleData() {
        return Stream.of(
                Arguments.of("askdfjiwejf1289", "askdfjiwejf"),
                Arguments.of("a11udui23fu89f2j3", "auduifufj"),
                Arguments.of("@(*JEjawA#*E(#JASD", "JEjawAEJASD")
        );
    }

    @DisplayName("영문자 추출 테스트")
    @ParameterizedTest
    @MethodSource("alphabetsSampleData")
    public void getAlphabetsTest(String s1, String result) throws Exception {
       Assertions.assertEquals(result, StringUtil.getAlphabets(s1));
    }

    static Stream<Arguments> numbersSampleData() {
        return Stream.of(
                Arguments.of("askdfjiwejf1289", "1289"),
                Arguments.of("a11udui23fu89f2j3", "11238923"),
                Arguments.of("@(*JEjawA#*E(#JASD", "")
        );
    }

    @DisplayName("숫자 추출 테스트")
    @ParameterizedTest
    @MethodSource("numbersSampleData")
    public void getNumbersTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.getNumbers(s1));
    }

    static Stream<Arguments> priorityLetterSortSampleData() {
        return Stream.of(
                Arguments.of("aAAswsiqSDIF", "AAaDFIiqSssw"),
                Arguments.of("IIKSSiwqlkao", "aIIiKkloqSSw"),
                Arguments.of("qiwloalSQKWl", "aiKllloQqSWw")
        );
    }

    @DisplayName("영문자 중 대문자 우선순위 정렬 테스트")
    @ParameterizedTest
    @MethodSource("priorityLetterSortSampleData")
    public void priorityLetterSortTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.priorityLetterSort(s1));
    }

    static Stream<Arguments> priorityLetterSortBadCaseSampleData() {
        return Stream.of(
                Arguments.of("aA2As1ws3iqSDI21F", "AAaDFIiqSssw"),
                Arguments.of("I1@IKS3#Si4$wql5kao", "aIIiKkloqSSw"),
                Arguments.of("qi@#wloa12l$2SQK21`Wl", "aiKllloQqSWw")
        );
    }

    @DisplayName("영문자 중 대문자 우선순위 정렬 BadCase 테스트")
    @ParameterizedTest
    @MethodSource("priorityLetterSortBadCaseSampleData")
    public void priorityLetterSortBadcaseTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.priorityLetterSort(s1));
    }

    static Stream<Arguments> numberSortSampleData() {
        return Stream.of(
                Arguments.of("2839172602829", "0122223678899"),
                Arguments.of("21902498271", "01122247899"),
                Arguments.of("162081290389401", "000111223468899")
        );
    }

    @DisplayName("숫자 정렬 테스트")
    @ParameterizedTest
    @MethodSource("numberSortSampleData")
    public void numberSortTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.numberSort(s1));
    }

    static Stream<Arguments> numberSortBadCaseSampleData() {
        return Stream.of(
                Arguments.of("2839172602829", "0122223678899"),
                Arguments.of("2@190$2s498d271", "01122247899"),
                Arguments.of("1@6208s129a03$89a401", "000111223468899")
        );
    }

    @DisplayName("숫자 추출 BadCase 테스트")
    @ParameterizedTest
    @MethodSource("numberSortBadCaseSampleData")
    public void numberSortBadCaseTest(String s1, String result) {
        Assertions.assertEquals(result, StringUtil.numberSort(s1));
    }

    static Stream<Arguments> removeTagSampleData() {
        return Stream.of(
                Arguments.of("<html><body></body></html>", ""),
                Arguments.of("<html>12d<body>v23sd</body>12das</html>", "12dv23sd12das"),
                Arguments.of("<html>1@!2d<body>v#D!23sd</body>12dDEas</html>", "1@!2dv#D!23sd12dDEas")
        );
    }

    @DisplayName("html tag 제거 테스트")
    @ParameterizedTest
    @MethodSource("removeTagSampleData")
    public void removeTagTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.removeTag(s1));
    }

    static Stream<Arguments> removeTagBadCaseSampleData() {
        return Stream.of(
                Arguments.of("ml><body>dy></html>", ""),
                Arguments.of("ml>12d<body>dody>12das</html>", "12d12das"),
                Arguments.of("<html21d<body</bod4r2</html>", "21d4r2")
        );
    }

    @DisplayName("html tag 제거 BadCase 테스트")
    @ParameterizedTest
    @MethodSource("removeTagBadCaseSampleData")
    public void removeTagBadCaseTest(String s1, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.removeTag(s1));
    }

    static Stream<Arguments> combineStringSampleData() {
        return Stream.of(
                Arguments.of("askdfjiwejf","0122223678899", "a0s1k2d2f2j2i3w6e7j8f899"),
                Arguments.of("auduifufj", "01122247899","a0u1d1u2i2f2u4f7j899"),
                Arguments.of("JEjawAEJASD","000111223468899", "J0E0j0a1w1A1E2J2A3S4D68899")
        );
    }

    @DisplayName("영문 숫자 순차적 문자열 결합 테스트")
    @ParameterizedTest
    @MethodSource("combineStringSampleData")
    public void combineStringTest(String s1, String s2, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.combineString(s1,s2));
    }

    static Stream<Arguments> combineStringBadCaseSampleData() {
        return Stream.of(
                Arguments.of("askd82fjiw2@ejf", "0122#(@223!@67#8899", "a0s1k2d2f2j2i3w6e7j8f899"),
                Arguments.of("a%udui3@fu4fj", "01#1%22a2d47899", "a0u1d1u2i2f2u4f7j899"),
                Arguments.of("J!Ej#awA%EJAS5D", "000111223468899", "J0E0j0a1w1A1E2J2A3S4D68899")
        );
    }

    @DisplayName("영문 숫자 순차적 문자열 결합 BadCase 테스트")
    @ParameterizedTest
    @MethodSource("combineStringBadCaseSampleData")
    public void combineStringBadCaseTest(String s1, String s2, String result) throws Exception {
        Assertions.assertEquals(result, StringUtil.combineString(s1,s2));
    }
}
