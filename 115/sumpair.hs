import System.Environment
import Control.Applicative
import Data.List

main = do
    putStrLn "Please enter a list of numbers separated by spaces:"
    numbers <- map read <$> words <$> getLine
    putStrLn "And what number should the sum of each pair be equal to?"
    sumPair <- read <$> getLine
    putStrLn "The answer is:"
    putStrLn $ formatPairs $ calculatePairs numbers sumPair

formatPairs :: Num a => [(a, a)] -> String
formatPairs pairs = concatMap (\(a, b) -> show a ++ ", " ++ show b ++ "\n") pairs

calculatePairs :: (Ord a, Num a) => [a] -> a -> [(a, a)]
calculatePairs nums sum = nub [(a, b) | a <- nums, b <- nums, a + b == sum, a < b]
