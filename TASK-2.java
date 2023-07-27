import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCount {
    private static final String[] COMMON_WORDS = {"the", "and", "of", "to", "in", "a", "is", "that", "it"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a text or provide a file path:");
        String input = scanner.nextLine();

        String text;
        if (isFile(input)) {
            try {
                text = readFile(input);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
                return;
            }
        } else {
            text = input;
        }

        String[] words = splitTextIntoWords(text);

        System.out.println("Total word count: " + words.length);
        System.out.println("Unique word count: " + getUniqueWordCount(words));
        System.out.println("Word frequency:");
        displayWordFrequency(words);
    }

    private static boolean isFile(String input) {
        File file = new File(input);
        return file.isFile();
    }

    private static String readFile(String filePath) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        scanner.close();
        return sb.toString();
    }

    private static String[] splitTextIntoWords(String text) {
        // Use regular expression to split words based on space or punctuation
        return text.split("\\W+");
    }

    private static int getUniqueWordCount(String[] words) {
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        return uniqueWords.size();
    }

    private static void displayWordFrequency(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (isCommonWord(word)) {
                continue;
            }
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Sort words alphabetically
        List<String> sortedWords = new ArrayList<>(wordFrequency.keySet());
        Collections.sort(sortedWords);

        for (String word : sortedWords) {
            int frequency = wordFrequency.get(word);
            System.out.println(word + ": " + frequency);
        }
    }

    private static boolean isCommonWord(String word) {
        return Arrays.asList(COMMON_WORDS).contains(word.toLowerCase());
    }
}
