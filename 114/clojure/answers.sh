#!/bin/bash

lein uberjar
mv target/wordladder-1.0-standalone.jar wordladder.jar

echo
echo How many words from the list can appear next to the word best in a word ladder?

echo java -jar wordladder.jar list best
java -jar wordladder.jar list best

echo
echo Bonus 1: One word in the list has 33 other words that can appear next to it. What is this word?

echo java -jar wordladder.jar top 1
java -jar wordladder.jar top 1

echo
echo Bonus 2: How many different words can be reached, starting from best, in 3 or fewer steps?

echo java -jar wordladder.jar chain 3 best
java -jar wordladder.jar chain 3 best

echo
