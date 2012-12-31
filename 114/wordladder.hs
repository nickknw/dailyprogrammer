import System.Environment
import Control.Applicative
import Data.String.Utils
import Data.Ord
import Data.List

dictFile = "selected_four-letter_words.txt"

-- Command line interface

usage = "\nInvalid syntax.\n\nUsage:\n\n"

    ++ "./wordladder list <word>\n"
    ++ "    - List all the words that can be made from <word> by changing one letter.\n\n"

    ++ "./wordladder most\n"
    ++ "    - Show the top 10 biggest word ladders.\n\n"

    ++ "./wordladder chain <n> <word>\n"
    ++ "    - Show the number of words that can be reached, starting from <word>, in <n> or fewer steps.\n\n"

    ++ "Examples:\n\n"

    ++ "./wordladder list best\n"
    ++ "./wordladder most\n"
    ++ "./wordladder chain 3 best\n"

main = do
    args <- getArgs
    dict <- lines <$> replace "\r" "" <$> readFile dictFile
    case args of
        "list":word:[] -> putStrLn $ fmtList $ wordLadder word dict
        "most":_ -> putStrLn $ fmtList $ flattenPairs $ mostLadderable dict
        "chain":steps:start:[] -> putStrLn $ show $ length $ wordChain (read steps) (start:[]) dict
        _ -> putStrLn usage

fmtList list = foldl1 (\x acc-> x ++ "\n" ++ acc) list

flattenPairs list = map (\p -> show (fst p) ++ " - " ++ show (snd p)) list

-- Word Ladder

wordLadder :: String -> [String] -> [String]
wordLadder word dict = filter (oneLetterOff word) dict

oneLetterOff :: String -> String -> Bool
oneLetterOff word1 word2 = 1 == (length $ filter not $ (zipWith (==) word1 word2))

-- Most Ladderable

mostLadderable dict =
    let ladderLengthPairs = map (\w -> (w, length (wordLadder w dict))) dict
    in
    take 10 $ reverse $ sortBy (comparing snd) ladderLengthPairs

-- Word Chain

wordChain :: Int -> [String] -> [String] -> [String]
wordChain 0 current _ = current
wordChain steps current dict =
    let nextLevelWords = nub $ concatMap (\x -> wordLadder x dict) current
    in
    nub (current ++ wordChain (steps-1) nextLevelWords dict)
