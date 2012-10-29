import System.Environment
import System.Random

usage = "Usage: decToSci 340000\nor decToSci -g\nor decToSci -r \"3.2 * 10^2\""

main = do
    args <- getArgs
    randomNum <- randomRIO (1, 2000000000) :: IO Int
    case args of
        []         -> putStrLn usage
        "-g":_     -> putStrLn $ decToSci (show randomNum)
        "-r":[]    -> error usage
        "-r":rest  -> putStrLn $ sciToDec $ head rest
        argument:_ -> putStrLn $ decToSci argument

decToSci :: String -> String
decToSci num = num

sciToDec :: String -> String
sciToDec num = num
