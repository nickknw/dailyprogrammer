import System.Environment
import Control.Applicative
import Data.String.Utils
import Data.Ord
import Data.List

dictFile = "../selected_four-letter_words.txt"

-- Word Ladder

wordLadder :: String -> [String] -> [String]
wordLadder word dict = filter (oneLetterOff word) dict

oneLetterOff :: String -> String -> Bool
oneLetterOff word1 word2 = 1 == (length $ filter not $ (zipWith (==) word1 word2))

-- Most Ladderable

mostLadderable :: Int -> [String] -> [(String, Int)]
mostLadderable num dict = take num $ reverse $ sortBy (comparing snd) wordNumberPairs
    where wordNumberPairs = [(w, length (wordLadder w dict)) | w <- dict]

-- Word Chain

wordChain :: Int -> [String] -> [String] -> [String]
wordChain 0 current _ = current
wordChain steps current dict = nub $ current ++ wordChain (steps-1) nextLevelOfWords dict
    where nextLevelOfWords = nub $ concat [wordLadder x dict | x <- current]

-- Command line interface

usage = "\nInvalid syntax.\n\nUsage:\n\n"

    ++ "./wordladder list <word>\n"
    ++ "    - List all the words that can be made from <word> by changing one letter.\n\n"

    ++ "./wordladder top <num>\n"
    ++ "    - Show the top <num> biggest word ladders.\n\n"

    ++ "./wordladder chain <n> <word>\n"
    ++ "    - Show the number of words that can be reached, starting from <word>, in <n> or fewer steps.\n\n"

    ++ "Examples:\n\n"

    ++ "./wordladder list best\n"
    ++ "./wordladder top 10\n"
    ++ "./wordladder chain 3 best\n"

fmtList :: [String] -> String
fmtList list = join "\n" list

fmtPairs :: [(String, Int)] -> String
fmtPairs pairs = concatMap (\p -> fst p ++ " - " ++ show (snd p) ++ "\n") pairs

main = do
    args <- getArgs
    dict <- lines <$> replace "\r" "" <$> readFile dictFile
    case args of
        "list":word:[]         -> putStrLn $ fmtList $ wordLadder word dict
        "top":num:[]           -> putStrLn $ fmtPairs $ mostLadderable (read num) dict
        "chain":steps:start:[] -> putStrLn $ show $ length $ wordChain (read steps) (start:[]) dict
        _                      -> putStrLn usage
