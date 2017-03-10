#!/usr/bin/env bash
#mvn clean package

spark-submit \
	--master local[2] \
	--class com.metok.task.SeperateLog \
	target/demo_spark-1.0-SNAPSHOT.jar "test"

