(ns wordladder.core
  (:gen-class))

(use '[clojure.core.match :only (match)])

; Word Ladder

(defn one-letter-off [word1 word2]
  (= 1 (count (filter #(not %1) (map #(= %1 %2) word1 word2)))))

(defn word-ladder [word dict]
  (filter (partial one-letter-off word) dict))

; Most Ladderable

(defn most-ladderable [num dict]
  (let [word-number-pairs (for [w dict]  [w (count (word-ladder w dict))])]
    (take num (sort-by #(get % 1) > word-number-pairs))))

; Word Chain

; Command line interface

(def dict-file "../selected_four-letter_words.txt")

(def usage "\nInvalid syntax.\n\nUsage:\n\n

  ./wordladder list <word>\n
      - List all the words that can be made from <word> by changing one letter.\n\n

  ./wordladder top <num>\n
      - Show the top <num> biggest word ladders.\n\n

  ./wordladder chain <n> <word>\n
      - Show the number of words that can be reached, starting from <word>, in <n> or fewer steps.\n\n

  Examples:\n\n

  ./wordladder list best\n
  ./wordladder top 10\n
  ./wordladder chain 3 best\n")

(defn fmt-list [list] (clojure.string/join "\n" list))
(defn fmt-pairs [pairs] mapcat #((str (get %1 0) " - " (get %1 1) "\n" )) pairs)

(defn -main
    [& args]
    (let [dict (clojure.string/split (slurp dict-file) #"\n")]
    (println (match (vec args)
        ["list" word] (println (fmt-list (word-ladder word dict)))
        ["top" num] (println (fmt-pairs (most-ladderable num dict)))
        ["chain" steps start] (println "chained")
        :else (println usage)))))
