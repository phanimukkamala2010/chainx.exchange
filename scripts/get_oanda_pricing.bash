#!/usr/bin/bash

CHAINHOME=$HOME/chainx/chainx.exchange

CLASS_PATH=$CHAINHOME/jars
for jar in `ls $CHAINHOME/jars/*.jar`
do
    CLASS_PATH=$CLASS_PATH:$jar
done
echo $CLASS_PATH

CLASS=chainpay.TestMain
CLASS=chainx.exchange.TestMain
CLASS=chainx.fxpricing.TestMain

java -cp $CLASS_PATH $CLASS
