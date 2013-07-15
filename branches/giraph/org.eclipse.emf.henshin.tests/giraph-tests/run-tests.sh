#!/bin/bash

if [ ! -f tests.conf ]; then
    echo "tests.conf not found."
    exit 1
fi
source tests.conf

function copy_tests {
	sftp $SSH_ARGS $HADOOP_MASTER << EOF
		mkdir $WORKING_DIR
		rm $WORKING_DIR/*
		cd $WORKING_DIR
		lcd graphs
		put *.json
		lcd ../classes
		cd $GIRAPH_TESTS_DIR
		put *.java
EOF
}

function compile_tests {
	ssh $SSH_ARGS $HADOOP_MASTER << EOF
		cd $GIRAPH_HOME/$GIRAPH_PROJECT
		mvn install -DskipTests
EOF
	if [ $? -ne 0 ]; then
        echo "Build failed."
        exit 1
    else
        echo "Build successful."
    fi
}

function run_test {
    main="$GIRAPH_TESTS_PACKAGE.$1\\\$"
    util="$GIRAPH_TESTS_PACKAGE.HenshinUtil\\\$"
	ssh $SSH_ARGS $HADOOP_MASTER << EOF 2>&1 | tee /tmp/output.log
		cd $WORKING_DIR && \
		hadoop fs -rmr /testInput ; \
		hadoop fs -rmr /testOutput ; \
		hadoop fs -mkdir /testInput ; \
		hadoop fs -put $2.json /testInput/ ; \
		hadoop jar \
			$GIRAPH_TESTS_JAR \
			org.apache.giraph.GiraphRunner \
			$GIRAPH_TESTS_PACKAGE.$1 \
			-vif "$util""InputFormat" \
			-vip /testInput/$2.json \
			-of "$util""OutputFormat" \
			-op /testOutput \
			-w $GIRAPH_WORKERS \
			-mc "$main""MasterCompute"
EOF
	VERTICES=$(cat /tmp/output.log | grep "Aggregate vertices" | sed s/^.*\\=//)
	EDGES=$(cat /tmp/output.log | grep "Aggregate edges" | sed s/^.*\\=//)
	if [ "$VERTICES" != "$3" ]; then
		echo "Error in test $1/$2: expected $3 vertices but got $VERTICES"
		exit;
	fi
	if [ "$EDGES" != "$4" ]; then
		echo "Error in test $1/$2: expected $4 edges but got $EDGES"
		exit;
	fi
	rm /tmp/output.log
	echo "Test $1/$2 successful."
	echo
}

echo

copy_tests
compile_tests

run_test StarMain StarStart 1 0

run_test ForkMain ForkStart 1 0

#run_test Wheel10 WheelStart 3 3

run_test Sierpinski Sierpinski 6 9
run_test SierpinskiMain1 Sierpinski 6 9
run_test SierpinskiMain3 Sierpinski 42 81
run_test SierpinskiMain6 Sierpinski 1095 2187
run_test SierpinskiMain9 Sierpinski 29526 59049


echo All tests successful.
