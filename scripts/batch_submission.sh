#!/usr/bin/env bash

# SCENARIO=$1
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
regex="commands_([0-9]+).txt"

mkdir -p ./test_results
docker run -d --name streamingwars gatech/streamingwars sh -c "mkdir test_results && sleep 370"
# docker rm streamingwars:/usr/src/a4/streaming_wars.jar
# docker cp ./submission/streaming_wars.jar streamingwars:/usr/src/a4/streaming_wars.jar

for test in $DIR/../test_scenarios/*;
do
  if [[ $test =~ $regex ]]
    then
        SCENARIO="${BASH_REMATCH[1]}"
        docker exec streamingwars sh -c "java -jar streaming_wars.jar < commands_${SCENARIO}.txt > stream_test_${SCENARIO}_results.txt && \
        tr '[:upper:]' '[:lower:]' < stream_test_${SCENARIO}_results.txt > temp_${SCENARIO}.txt && mv temp_${SCENARIO}.txt stream_test_${SCENARIO}_results.txt && \
        tr -d '[:blank:]' < stream_test_${SCENARIO}_results.txt > temp_${SCENARIO}.txt && mv temp_${SCENARIO}.txt stream_test_${SCENARIO}_results.txt && \
        java -jar streaming_wars_initial.jar < commands_${SCENARIO}.txt > stream_test_initial_${SCENARIO}_results.txt && \
        tr '[:upper:]' '[:lower:]' < stream_test_initial_${SCENARIO}_results.txt > temp_initial_${SCENARIO}.txt && mv temp_initial_${SCENARIO}.txt stream_test_initial_${SCENARIO}_results.txt && \
        tr -d '[:blank:]' < stream_test_initial_${SCENARIO}_results.txt > temp_initial_${SCENARIO}.txt && mv temp_initial_${SCENARIO}.txt stream_test_initial_${SCENARIO}_results.txt && \
        diff -a -b -E -s -N -u stream_test_${SCENARIO}_results.txt stream_test_initial_${SCENARIO}_results.txt > test_results/diff_results_${SCENARIO}.txt" &
    fi
done
wait
# /usr/src/cs6310/
docker cp streamingwars:/usr/src/cs6310/test_results/ ./
docker rm -f streamingwars &> /dev/null