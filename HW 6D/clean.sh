#! /bin/bash

find . -name "*~" -delete
find . -name "*.class" -delete
rm -Rf parser out
rm -f *.tests
