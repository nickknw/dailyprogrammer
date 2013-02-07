#!/bin/bash

javac WordLadderJava.java

echo
echo How many words from the list can appear next to the word best in a word ladder?

echo java WordLadderJava list best
java WordLadderJava list best

echo
echo Bonus 1: One word in the list has 33 other words that can appear next to it. What is this word?

echo java WordLadderJava top 1
java WordLadderJava top 1

echo
echo Bonus 2: How many different words can be reached, starting from best, in 3 or fewer steps?

echo java WordLadderJava chain 3 best
java WordLadderJava chain 3 best

echo
