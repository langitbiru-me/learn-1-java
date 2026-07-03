package week_2.day_3.col_real_code;

import java.util.HashMap;

public class WordCounter {

    private String text;

    public WordCounter(String text) {
        this.text = text;
    }

    public HashMap<String, Integer> countWords() {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.split("\\s+");

        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        return wordCounts;
    }
}
