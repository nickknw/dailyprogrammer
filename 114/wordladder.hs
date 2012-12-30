import System.Environment
import Control.Applicative

dictFile = "selected_four-letter_words.txt"

-- Command line interface

usage = "Usage:\n"
    ++ "./wordladder list puma\n"
    ++ "./wordladder most\n"
    ++ "./wordladder chain 3 best\n"

main = do
    args <- getArgs
    dict <- lines <$> readFile dictFile
    case args of
        "list":word:[] -> wordLadder word dict
        "most":_ -> mostLadderable dict
        "chain":steps:start:[] -> wordChain steps start dict
        _ -> error usage

-- Word Ladder

wordLadder word dict = undefined

-- Most Ladderable

mostLadderable dict = undefined

-- Word Chain

wordChain steps start dict = undefined
