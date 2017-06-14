#!/usr/bin/env bash
#mvn clean package
INPUT="/user/h_scribe/metok/metok_server_business_log"
OUTPUT="/user/output"
MODE="test"
spark-submit \
	--master local[2] \
	--class com.com.yk.ykLog \
	target/demo-1.0-SNAPSHOT.jar ${MODE} ${INPUT} ${OUTPUT}

