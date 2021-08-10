#!/usr/bin/bash

CHAINHOME=$HOME/chainx
JAVAHOME=/usr/bin

#rm -rf $CHAINHOME/build
#mkdir -p $CHAINHOME/build

CLASS_PATH=$CHAINHOME/build
for jar in `ls $CHAINHOME/jars/*.jar`
do
    CLASS_PATH=$CLASS_PATH:$jar
done
echo $CLASS_PATH

$JAVAHOME/javac -cp $CLASS_PATH -d $CHAINHOME/build $CHAINHOME/src/exchange/*.java

cd $CHAINHOME/build
jar cvf $CHAINHOME/jars/chainx-exchange.jar *
cd -
