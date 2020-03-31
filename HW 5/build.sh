#! /bin/bash

antlr4='java -jar /usr/local/lib/antlr-4.8-complete.jar'
$antlr4 -o parser -package parser -visitor W2.g4
javac -Xlint compiler/Main.java
