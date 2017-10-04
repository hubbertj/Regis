
import System.Random
import Data.List

-- Write a function that takes a list and returns the same list in reverse. 
-- Test the reverse list function using a number of lists with different data types and lengths.

-- Write a sort that takes a list and returns a sorted list.
-- Test the sort function using a variety of lists with different lengths.

-- function call putStrLn is the funcuton and "Hello, World" is the pe


reverseList :: [Int] -> [Int]
reverseList [] = []
reverseList list = reverse list

myPureFunction :: Float -> Float
myPureFunction x = 2 * x

main :: IO ()
main = do
    -- num :: Float
    num <- randomIO :: IO Int
    -- This "extracts" the float from IO Float and binds it to the name num
    print  num

