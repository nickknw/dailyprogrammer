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
decToSci number = formatAsSci (num / (10 ^^ exponent)) exponent
	where 
	    num = read number :: Double
	    exponent = findExponent num

sciToDec :: String -> String
sciToDec num = num

formatAsSci :: (Num a, Num b) => a -> b -> String
formatAsSci number exponent = show number ++ " * 10^" ++ show exponent

findExponent :: Double -> Int
findExponent num = findExponentR num 0

findExponentR :: Double -> Int -> Int
findExponentR num counter 
	| num >= 1 && num < 10 = counter
	| num >= 1 = findExponentR (num / 10) (counter + 1)
	| num == 0 = 0
	| num < 1 = findExponentR (num * 10) (counter - 1)
