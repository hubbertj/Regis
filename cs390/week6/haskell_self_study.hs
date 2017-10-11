import Data.List
import System.Random

-- Write a function that takes a list and returns the same list in reverse. 
-- Test the reverse list function using a number of lists with different data types and lengths.

-- Write a sort that takes a list and returns a sorted list.
-- Test the sort function using a variety of lists with different lengths.

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
    putStrLn "Creating a list of Ints"
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
    putStrLn ""
    putStrLn "Reversing the list"
    
    let revList = reverse_my_list (masterList)
    print revList
    putStrLn ""
    putStrLn "Sorts the list"
    let sortedList = doMergeSort (masterList)
    print sortedList
    putStrLn ""

    putStrLn "Test the sort function using a variety of lists with different lengths"
    putStrLn ""
    putStrLn "List A"
    number <- randomRIO (1, 5) :: IO Int
    let newlist = cutList number masterList
    print newlist
    putStrLn "--Sorted List"
    let sorted = doMergeSort (newlist)
    print sorted 
    putStrLn ""

    putStrLn "List B"
    number <- randomRIO (1, 5) :: IO Int
    let newlist = cutList number masterList
    print newlist
    putStrLn "--Sorted List"
    let sorted = doMergeSort (newlist)
    print sorted 
    putStrLn ""

    putStrLn "List C"
    number <- randomRIO (1, 5) :: IO Int
    let newlist = cutList number masterList
    print newlist
    putStrLn "--Sorted List"
    let sorted = doMergeSort (newlist)
    print sorted 
    putStrLn ""

    putStrLn "End"








