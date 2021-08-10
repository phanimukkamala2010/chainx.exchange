#!/usr/bin/bash

CHAINHOME=$HOME/chainx/chainx.exchange

CLASS_PATH=
for jar in `ls $CHAINHOME/jars/*.jar`
do
    CLASS_PATH=$CLASS_PATH:$jar
done
echo $CLASS_PATH

CLASS=chainpay.TestMain
CLASS=chainx.exchange.TestMain

java -cp $CLASS_PATH $CLASS
