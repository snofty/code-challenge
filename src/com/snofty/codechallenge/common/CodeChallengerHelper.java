package com.snofty.codechallenge.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CodeChallengerHelper {

    private static final SnoftyLogger LOGGER = SnoftyLoggerProvider.getMyLogger(CodeChallengerHelper.class);
    private Map<String, Integer> wordsCount = new HashMap<>();

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void performInstructions(String absolutepath) {
        try {
            long uniqueWordCount = getUniqueWordsCount(absolutepath);
            LOGGER.debug("uniqueWordCount: " + uniqueWordCount);

            String outputFileContent = null;

            String fileContent = new String(Files.readAllBytes(Paths.get(absolutepath)),
                    StandardCharsets.UTF_8);

            outputFileContent = cleanSpecialCharacters(fileContent);

            LOGGER.debug(outputFileContent);


            List<String> stopWords = Files.readAllLines(Paths.get("resources/stopwords.txt"),
                    StandardCharsets.UTF_8);
            LOGGER.debug("------------------------");
            for (String word :
                    outputFileContent.split("\\s+")) {
                if (!stopWords.contains(word)) {
                    int count = 1;
                    if (wordsCount.containsKey(word)) {
                        count = wordsCount.get(word);
                        count++;
                    }
                    wordsCount.put(word, count);
                }
            }
            for (String word : stopWords) {
                outputFileContent = outputFileContent.replaceAll("[\\s+]" + word + "[\\s+]", " ");
            }
            LOGGER.info("output: " + outputFileContent);
            LOGGER.info("counts the usage frequency of unique words: ");
            wordsCount.forEach((key, value) -> {
                LOGGER.info("word:" + key + " count: " + value);
            });
            
            LinkedHashMap<String, Integer> countByWordSorted = wordsCount.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (v1, v2) -> {
                                throw new IllegalStateException();
                            },
                            LinkedHashMap::new
                    ));
            
            int count = 0;
            
           
            
        } catch (IOException e) {
            LOGGER.warn("failed to read file data", e);
        }
    }

    private String cleanSpecialCharacters(String input) {
        LOGGER.debug("after clean " + input.replaceAll("[^a-zA-Z0-9 \n]", ""));
        return input.replaceAll("[^a-zA-Z0-9 \n]", "");
    }

    private long getUniqueWordsCount(String path) {
        long uniqueWords = -1;
        try {
            uniqueWords = Files.lines(Paths.get(path), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
            LOGGER.warn("failed to identify unique words", e);
        }
        return uniqueWords;
    }
}
