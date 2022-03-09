import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodingChallenge {
    public static void main(String[] args) {
        String inputFileName = args[0];
        FileReader fileIn = null;
        try {
            fileIn = new FileReader(inputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Benefits of BufferReader over Scanner (faster, efficiency not limits to string/primitives)
        BufferedReader fileScan = null;

        Map<String, Integer> phraseList = new HashMap<String, Integer>();

        try {
            fileScan = new BufferedReader(fileIn);
            String line  = "";
            StringBuilder builder = new StringBuilder();
            while((line = fileScan.readLine()) != null){
                builder.append(line).append("\n");
            }
            String content = builder.toString().trim();

            List<String> wordsList = getWordList(content);

            for (int i = 0; i < wordsList.size() - 2; i++) {
                String phrase = createPhrases(wordsList.get(i), wordsList.get(i + 1), wordsList.get(i + 2));
                addToHashMap((HashMap<String, Integer>) phraseList, phrase);
            }

            //Sorting HashMap: https://howtodoinjava.com/java/sort/java-sort-map-by-values/
            phraseList.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(100)
                    .forEach(entry -> {
                        System.out.println("Phrase = " + entry.getKey() + ", Number of Times = " + entry.getValue());
                    });

        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static ArrayList<String> getWordList(String content) {
        //https://www.geeksforgeeks.org/extracting-word-string-java/
        ArrayList<String> wordList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher match = pattern.matcher(content);

        while (match.find()) {
            wordList.add(match.group().toLowerCase());
        }
        return wordList;
    }

    public static String createPhrases(String word1, String word2, String word3) {

        StringBuilder builder2 = new StringBuilder();
        return builder2.append(word1).append(" ").append(word2).append(" ").append(word3).toString();

    }

    public static void addToHashMap(HashMap<String, Integer> map, String phrase) {
        if (map.containsKey(phrase)) {
            map.put(phrase, map.get(phrase) + 1);
        } else {
            map.put(phrase, 1);
        }

    }
}
