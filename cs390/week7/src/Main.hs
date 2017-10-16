module Main where

import Data.List
import System.Random


-- 1. Write a sort that takes a list and a function that compares its two arguments and then returns a sorted list.
-- 2. Test the new sort function with various comparisons, different lists, and lengths of lists.
-- 3. Write a function that takes an argument x and returns a lazy sequence that has every third number, starting with x.  Then, write a function that includes every fifth number, beginning with y.  Combine these functions through composition to return every eighth number, beginning with x+y.
-- 4. Test this function using a variety of x and y values.

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
    
    putStrLn "End"
