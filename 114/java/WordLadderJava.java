// Java without 3rd party libraries libraries
// I was planning on doing a version with lambdaj and any other libraries that
// might help, but I got partway throught and it was such a small improvement that
// it didn't seem worth it for such a small program.

import java.io.*;
import java.util.*;

public class WordLadderJava {

    public static final String dictFile = "../selected_four-letter_words.txt";

    // Word Ladder

    public static List<String> wordLadder (final String word, final List<String> dict) {

        List<String> wordLadderWords = new ArrayList<String>();

        for (String dictWord : dict) {
            if (oneLetterOff(word, dictWord)) {
                wordLadderWords.add(dictWord);
            }
        }

        return wordLadderWords;
    }

    public static Boolean oneLetterOff (final String word1, final String word2) {

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

    public static SortedSet<Pair<String, Integer>> mostLadderable (final int num, final ArrayList<String> dict) {

        TreeSet<Pair<String, Integer>> wordNumPairs = new TreeSet<Pair<String, Integer>>(
            new Comparator<Pair<String, Integer>>() {
                @Override
                public int compare(Pair<String, Integer> e1, Pair<String, Integer> e2) {
                    return e1.b.compareTo(e2.b);
                }
            });


        for (String word : dict) {
            wordNumPairs.add(new Pair<String, Integer>(word, wordLadder(word, dict).size()));
        }

        int i = 0;
        Pair<String, Integer> nthItem = null;
        NavigableSet<Pair<String, Integer>> wordNumPairsDescending = wordNumPairs.descendingSet();
        for (Pair<String, Integer> pair : wordNumPairsDescending) {
            nthItem = pair;
            if (i == num) {
                break;
            }
            i++;
        }

        return wordNumPairsDescending.headSet(nthItem);

    }

    // Word Chain

    public static Set<String> wordChain (final int steps, final List<String> current, final List<String> dict) {
        Set<String> finalSet = new TreeSet<String>();

        finalSet.addAll(current);
        Set<String> next = nextLevelOfWords(new TreeSet<String>(current), dict);

        for (int i = 0; i < steps; i++) {
            finalSet.addAll(next);
            next = nextLevelOfWords(next, dict);
        }

        return finalSet;
    }

    private static Set<String> nextLevelOfWords(final Set<String> current, final List<String> dict) {
        Set<String> next = new TreeSet<String>();
        for (String word : current) {
            next.addAll(wordLadder(word, dict));
        }

        return next;
    }

    // Command Line Interface

    public static final String usage = "\nInvalid syntax.\n\nUsage:\n\n"

    + "java WordLadder list <word>\n"
    + "    - List all the words that can be made from <word> by changing one letter.\n\n"

    + "java WordLadder top <num>\n"
    + "    - Show the top <num> biggest word ladders.\n\n"

    + "java WordLadder chain <n> <word>\n"
    + "    - Show the number of words that can be reached, starting from <word>, in <n> or fewer steps.\n\n"

    + "Examples:\n\n"

    + "java WordLadder list best\n"
    + "java WordLadder top 10\n"
    + "java WordLadder chain 3 best\n";
 
    public static <T> String fmtList (final List<T> list) {
        String result = "";

        for (T item : list) {
            result += item.toString() + "\n";
        }

        return result;
    }

    public static String fmtPairs (final SortedSet<Pair<String, Integer>> pairs) {
        String result = "";

        for (Pair<String, Integer> pair : pairs) {
            result += pair.a + " - " + pair.b + "\n";
        }

        return result;
    }

    public static void main(final String[] args) throws FileNotFoundException{
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
            List<String> start = new ArrayList<String>();
            start.add(args[2]);
            Set<String> chain = wordChain(steps, start, dict);
            System.out.println(chain.size());
        }
        else {
            System.out.println(usage);
            return;
        }
    }
        
    // Helpers
	
    // http://stackoverflow.com/a/2671052/224354
    public static class Pair<A, B> { 
        public final A a; 
        public final B b; 
        public Pair (A a, B b) { 
            this.a = a; 
            this.b = b; 
        } 
    } 
}
