module Main where

import Data.List
import System.Random


-- 1. Write a sort that takes a list and a function that compares its two arguments and then returns a sorted list.
-- 2. Test the new sort function with various comparisons, different lists, and lengths of lists.
-- 3. Write a function that takes an argument x and returns a lazy sequence that has every third number, starting with x.  Then, write a function that includes every fifth number, beginning with y.  Combine these functions through composition to return every eighth number, beginning with x+y.
-- 4. Test this function using a variety of x and y values.

optionalSort :: [a] -> (a -> b) -> [b]
optionalSort [] _ =  []
optionalSort (x:xs) fun = fun x : optionalSort xs fun

aCompare :: Int -> Int -> [Int]
aCompare x y = 
    if x > y || y == x
        then [y, x] 
        else [x, y] 

-- reverse my list
reverse_my_list :: [Int] -> [Int]
reverse_my_list [] = []
reverse_my_list (x:xs) = reverse_my_list xs ++ [x]

-- sort a list using merge sort
doMergeSort :: Ord a => [a] -> [a]
doMergeSort [] = []
doMergeSort [x] = [x]
doMergeSort xs = mergeSort (doMergeSort (appendList xs)) (doMergeSort (prependList xs))

-- Slips a list by x and returns the first part.
cutList :: Int -> [a] -> [a]
cutList z xs = take (length xs `div` z) xs

-- Slips a list by 2 and returns the first part.
appendList :: [a] -> [a]
appendList xs = take (length xs `div` 2) xs

-- Slips a list by 2 and returns the last part.
prependList :: [a] -> [a]
prependList xs = drop (length xs `div` 2) xs

-- bulk work for merge sort
mergeSort :: Ord a => [a] -> [a] -> [a]
mergeSort xs [] = xs
mergeSort [] ys = ys
mergeSort (x:xs) (y:ys) 
    | (x <= y)  = x:(mergeSort xs (y:ys)) 
    | otherwise = y:(mergeSort (x:xs) ys)

-- generates a IO Int 1 - 100
randomInt :: IO Int
randomInt = randomRIO (1, 100) :: IO Int


main :: IO ()
main = do
    putStrLn "Creating a [Int]"
    -- not sure if there is a way to generated a random list in pure functional programming other then this
    var <- randomInt
    var1 <- randomInt
    var2 <- randomInt
    var3 <- randomInt
    var4 <- randomInt
    var5 <- randomInt
    var6 <- randomInt
    var7 <- randomInt
    var8 <- randomInt
    var9 <- randomInt
    var10 <- randomInt
    var11 <- randomInt
    var12 <- randomInt
    var13 <- randomInt
    var14 <- randomInt
    var15 <- randomInt
    var16 <- randomInt
    var17 <- randomInt
    var18 <- randomInt
    var19 <- randomInt
    var20 <- randomInt
    var21 <- randomInt
    var22 <- randomInt
    var23 <- randomInt
    let masterList = var:var1:var2:var3:var4:var5:var6:var7:var8:var9:var10:var11:var12:var13:var14:var15:var16:var17:var18:var19:var20:var21:var22:var23:[]
    print masterList

    putStrLn "Write a sort that takes a list and a function that compares its two arguments and then returns a sorted list"

    let sortList = optionalSort masterList aCompare
    -- print sortList

    putStrLn "End"
