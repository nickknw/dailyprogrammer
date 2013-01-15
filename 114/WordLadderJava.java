// Java with only platform libraries

import java.io.*;
import java.util.*;

public class WordLadderJava {

    public static String dictFile = "selected_four-letter_words.txt";

    // Command Line Interface

    public static String usage = "\nInvalid syntax.\n\nUsage:\n\n"

    + "./wordladder list <word>\n"
    + "    - List all the words that can be made from <word> by changing one letter.\n\n"

    + "./wordladder top <num>\n"
    + "    - Show the top <num> biggest word ladders.\n\n"

    + "./wordladder chain <n> <word>\n"
    + "    - Show the number of words that can be reached, starting from <word>, in <n> or fewer steps.\n\n"

    + "Examples:\n\n"

    + "./wordladder list best\n"
    + "./wordladder top 10\n"
    + "./wordladder chain 3 best\n";
 

    public static void main(String[] args) throws FileNotFoundException{
        if(args.length == 0) {
            System.out.println(usage);
            return;
        }

        Scanner sc = new Scanner(new File(dictFile));
        ArrayList<String> dict = new ArrayList<String>();
        while (sc.hasNext()) {
            dict.add(sc.next());
        }
        
        if (args[0].equals("list") && args.length == 2) {
            String word = args[1];
            System.out.println(fmtList(wordLadder(word, dict)));
        } 
        else if (args[0].equals("top") && args.length == 2) {
            int num = Integer.parseInt(args[1]);
            System.out.println(fmtPairs(mostLadderable(num, dict)));
        }
        else if (args[0].equals("chain") && args.length == 3) {
            int steps = Integer.parseInt(args[1]);
            ArrayList<String> start = new ArrayList<String>();
            start.add(args[2]);
            ArrayList<String> chain = wordChain(steps, start, dict);
            System.out.println(chain.size());
        }
        else {
            System.out.println(usage);
            return;
        }
    }

    public static <T> String fmtList (ArrayList<T> list) {
        String result = "";

        for (T item : list) {
            result += item.toString() + "\n";
        }

        return result;
    }

    public static String fmtPairs (ArrayList<Pair<String, Integer>> pairs) {
        String result = "";

        for (Pair<String, Integer> pair : pairs) {
            result += pair.a + " - " + pair.b + "\n";
        }

        return result;
    }

    // Word Ladder

    public static ArrayList<String> wordLadder (String word, ArrayList<String> dict) {

        ArrayList<String> wordLadderWords = new ArrayList<String>();

        for (String dictWord : dict) {
            if (oneLetterOff(word, dictWord)) {
                wordLadderWords.add(dictWord);
            }
        }

        return wordLadderWords;
    }

    public static Boolean oneLetterOff (String word1, String word2) {

        if (word1 == null || word2 == null || word1.length() != word2.length()) {
            return false;
        }

        int lettersOff = 0;

        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                lettersOff++;
            }
        }

        return lettersOff == 1;
    }

    // Most Ladderable

    public static ArrayList<Pair<String, Integer>> mostLadderable (int num, ArrayList<String> dict) {
        return new ArrayList<Pair<String, Integer>>();
    }

    // Word Chain

    public static ArrayList<String> wordChain (int steps, ArrayList<String> current, ArrayList<String> dict) {
        return new ArrayList<String>();
    }

    // Helpers

    public class Pair<A, B> { 
      public final A a; 
      public final B b; 
      public Pair (A a, B b) { 
        this.a = a; 
        this.b = b; 
      } 
    } 
}
