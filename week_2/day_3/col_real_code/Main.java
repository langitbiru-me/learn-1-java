package week_2.day_3.col_real_code;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter("Hello World, Hello Java");
        HashMap<String, Integer> wordCounts = wordCounter.countWords();
        wordCounts.forEach((word, count) -> System.out.println(word + " : " + count));
    }
}
