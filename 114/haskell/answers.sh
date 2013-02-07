#!/bin/bash

ghc --make wordladder.hs

echo
echo How many words from the list can appear next to the word best in a word ladder?

echo ./wordladder list best
./wordladder list best

echo
echo Bonus 1: One word in the list has 33 other words that can appear next to it. What is this word?

echo ./wordladder top 1
./wordladder top 1

echo
echo Bonus 2: How many different words can be reached, starting from best, in 3 or fewer steps?

echo ./wordladder chain 3 best
./wordladder chain 3 best

echo
