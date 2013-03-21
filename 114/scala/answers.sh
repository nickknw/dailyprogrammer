#!/bin/bash

scalac WordLadder.scala

echo
echo How many words from the list can appear next to the word best in a word ladder?

echo scala WordLadder list best
scala WordLadder list best

echo
echo Bonus 1: One word in the list has 33 other words that can appear next to it. What is this word?

echo scala WordLadder top 1
scala WordLadder top 1

echo
echo Bonus 2: How many different words can be reached, starting from best, in 3 or fewer steps?

echo scala WordLadder chain 3 best
scala WordLadder chain 3 best

echo
