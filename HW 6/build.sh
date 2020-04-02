#! /bin/bash

antlr4='java -jar /usr/local/lib/antlr-4.8-complete.jar'
$antlr4 -o parser -package parser -visitor nap.g4
javac -Xlint parser/*.java
javac -Xlint type/*.java
javac -Xlint ast/*.java
javac -Xlint util/*.java
javac -Xlint compiler/*.java
