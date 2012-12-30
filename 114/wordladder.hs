import System.Environment
import Control.Applicative
import Data.String.Utils
import Data.Ord
import Data.List

dictFile = "selected_four-letter_words.txt"

-- Command line interface

usage = "Usage:\n"
    ++ "./wordladder list puma\n"
    ++ "./wordladder most\n"
    ++ "./wordladder chain 3 best\n"

main = do
    args <- getArgs
    dict <- lines <$> replace "\r" "" <$> readFile dictFile
    case args of
        "list":word:[] -> putStrLn $ fmtList $ wordLadder word dict
        "most":_ -> putStrLn $ fmtList $ flattenPairs $ mostLadderable dict
        "chain":steps:start:[] -> putStrLn $ wordChain steps start dict
        _ -> error usage

fmtList list = foldl1 (\x acc-> x ++ "\n" ++ acc) list

flattenPairs list = map (\p -> show (fst p) ++ " - " ++ show (snd p)) list

-- Word Ladder

wordLadder :: String -> [String] -> [String]
wordLadder word dict = filter (oneLetterOff word) dict

oneLetterOff :: String -> String -> Bool
oneLetterOff word1 word2 = 1 == (sum $ map zeroOutMatches (zip word1 word2))
    where zeroOutMatches (a, b) = if a == b then 0 else 1

-- Most Ladderable

mostLadderable dict =
    let ladderLengths = map (\w -> (w, length (wordLadder w dict))) dict
    in
    take 10 $ reverse $ sortBy (comparing snd) ladderLengths

-- Word Chain

wordChain steps start dict = undefined
