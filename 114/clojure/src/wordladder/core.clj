(ns wordladder.core
  (:gen-class))

(use '[clojure.core.match :only (match)])
(use '[clojure.string :only (split join)])

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

(defn word-chain [steps start dict]
  (let [next-level (fn [words, dict] (set (flatten (for [w words] (word-ladder w dict)))))]
  (loop [i 0, current (next-level start dict), final start] 
    (if (< i steps)
      (recur (inc i) (next-level current dict) (concat final current))
      (set final)))))

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

(defn fmt-list [list] (join "\n" list))
(defn fmt-pairs [pairs] mapcat #((str (get %1 0) " - " (get %1 1) "\n" )) pairs)

(defn -main
    [& args]
    (let [dict (split (slurp dict-file) #"\r\n")]
    (println (match (vec args)
        ["list" word] (fmt-list (word-ladder word dict))
        ["top" number] (fmt-pairs (most-ladderable (read-string number) dict))
        ["chain" steps start] (count (word-chain (read-string steps) [start] dict))
        :else usage))))
