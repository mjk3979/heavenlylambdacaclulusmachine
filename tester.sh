#! /bin/bash

TESTS=LambdaTest
FILESTOREMOVE=butterdick.java

rm -rf test
mkdir test
find src/ -name '*.java' -exec cp {} test/ \;
find test/ -name '*.java' -exec sed -i -e '/edu\.rit\.csc/d' {} \;
cd test
rm $FILESTOREMOVE
javac *.java
for t in $TESTS; do echo $t; echo "-------------"; java $t; echo ""; done
