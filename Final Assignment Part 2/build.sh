#! /bin/bash

antlr4='java -jar /usr/local/lib/antlr-4.8-complete.jar'
$antlr4 -o parser -package parser -visitor nap.g4
export CLASSPATH=.:/usr/local/lib/antlr-4.8-complete.jar
javac -Xlint parser/*.java
javac -Xlint nap/*.java
