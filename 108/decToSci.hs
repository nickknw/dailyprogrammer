import System.Environment
import System.Random

-- Command line interface 

usage = "Usage: decToSci 340000\nor decToSci -g\nor decToSci -r \"3.2 x 10^2\""

main = do
    args <- getArgs
    randomNum <- randomRIO (1, 2000000000) :: IO Double
    case args of
        []         -> putStrLn usage
        "-g":_     -> putStrLn $ decToSci randomNum
        "-r":[]    -> error usage
        "-r":rest  -> putStrLn $ sciToDec $ head rest
        argument:_ -> putStrLn $ decToSci $ read argument 

-- Dec to Sci

decToSci :: Double -> String
decToSci number = 
    let exponent = floor $ logBase 10 number
    in formatAsSci (number / (10 ^^ exponent)) exponent

formatAsSci :: (Num a, Num b) => a -> b -> String
formatAsSci number exponent = show number ++ " x 10^" ++ show exponent

-- Sci to Dec

sciToDec :: String -> String
sciToDec num = num
