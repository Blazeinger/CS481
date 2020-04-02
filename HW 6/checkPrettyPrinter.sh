#! /bin/bash

for f in "$1"/*.nap; do
    echo Checking: $(basename "$f")
    java compiler.Main "$f" -p > tmp
    java compiler.Main tmp -p > tmp2
    diff tmp tmp2
done;
rm -f tmp tmp2
