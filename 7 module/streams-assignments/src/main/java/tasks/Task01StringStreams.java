package tasks;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task01StringStreams {

    /**
     * Функция должна вернуть число строчных символов в строке.
     *
     * Пример:
     *  "abcDE" -> 3
     *  "ABC" -> 0
     */
    public static long countLowercaseLetters(String str) {
//            throw new PleaseImplementMeException();
        return str.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLowerCase)
                .count();
    }


    /**
     * Функция должна заменить каждое слово в строке его длинной.
     *
     * Слова разделяются одним или более пробелами.
     *
     * Пример:
     *   "a b cd" -> "1 1 2"
     *   "one two   three" -> "3 3 5"
     *
     * Тут подойдут эти методы:
     *    - String::split
     *    - Stream::map
     *    - Stream::collect
     *    - Collectors.joining
     */
    public static String replaceWordsOnLength(String str) {
//            throw new PleaseImplementMeException();
        return Stream.of(str)
                .flatMap(s -> {
                    String[] split = s.split("\\s+");
                    return Arrays.stream(split);
                })
                .map(s -> Integer.toString(s.length()))
                .collect(Collectors.joining(" "));
    }
}