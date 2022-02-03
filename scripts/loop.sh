#!/usr/bin/env bash

exec &>> results.txt

now=$(date +"%m-%d-%y %r")
echo "Start time : $now"

oldIFS=${IFS}
IFS=$'\n'

# printf "IFS was: ${oldIFS}"

find ./submissions/ -type f -name '* *' | while read LINE
do
  newName="${LINE// /_}"
  printf "removing space from ${LINE} new name ${newName}\r\n"
  mv "${LINE}" "${newName}"
done

IFS=${oldIFS}

for i in $(find ./submissions/ -type f -iname "*.pdf")
do
  printf "removing ${i}\r\n"
  rm -f "${i}"
done

for i in $(find ./submissions/ -type f -iname "*.pdf")
do
  printf "deleting ${i}\r\n"
  delete "${i}"
done

for i in $(find ./submissions/ -type f -iname "*.jpg")
do
  printf "removing ${i}\r\n"
  rm -f "${i}"
done

for i in $(find ./submissions/ -type f -iname "*.jpg")
do
  printf "deleting ${i}\r\n"
  delete "${i}"
done

for i in $(find ./submissions/ -type f -iname "*.png")
do
  printf "removing ${i}\r\n"
  rm -f "${i}"
done

for i in $(find ./submissions/ -type f -iname "*.png")
do
  printf "deleting ${i}\r\n"
  delete "${i}"
done

# find ./submissions/ -type f -iname "*.jar" -execdir sh -c 'printf "%s\n" "${0%.*}"' {} ';' | xargs rm -r -f

mkdir ./submissions/jars

for i in $(find ./submissions/ -type f -iname "*.jar")
do
  name=${i/.\//}
  name=${name/submissions\//}
  name=${name/.jar/}
  printf "making jar folder ${name}\r\n"
  mkdir "./submissions/jars/${name}"
done

for i in $(find ./submissions/ -type f -iname "*.jar" -execdir sh -c 'printf "%s\n" "${0%.*}"' {} ';')
do
  name=${i/.\//}
  name=${name/submissions\//}
  name=${name/.jar/}
  printf "moving jar ${name}\r\n"
  mv "./submissions/${name}.jar" "./submissions/jars/${name}/"
done

for i in $(find ./submissions/ -type f -iname "*.zip" -execdir sh -c 'printf "%s\n" "${0%.*}"' {} ';')
do
  name=${i/.\//}
  name=${name/submissions\//}
  name=${name/.zip/}
  printf "removing old folder ${name}\r\n"
  rm -r -f ${name}
done

for i in $(find ./submissions/ -type f -iname "*.zip" -execdir sh -c 'printf "%s\n" "${0%.*}"' {} ';')
do
  name=${i/.\//}
  name=${name/submissions\//}
  name=${name/.zip/}
  printf "unzipping ${name}\r\n"
  unzip -d "./submissions/${name}/" "./submissions/${name}.zip"
done

for i in $(find ./submissions/ -type f -iname "*.zip" -execdir sh -c 'printf "%s\n" "${0%.*}"' {} ';')
do
  name=${i/.\//}
  name=${name/submissions\//}
  name=${name/.zip/}
  printf "moving zip ${name}\r\n"
  mv "./submissions/${name}.zip" "./submissions/${name}/"
done

# Remove spaces again
oldIFS=${IFS}
IFS=$'\n'

# printf "IFS was: ${oldIFS}"

# file type removed for spaces in directory structure.
find ./submissions/ -name '* *' | while read LINE
do
  newName="${LINE// /_}"
  printf "removing space from ${LINE} new name ${newName}\r\n"
  mv "${LINE}" "${newName}"
done

IFS=${oldIFS}

# End remove spaces again

j=0

for i in $(find ./submissions/ -iname "dockerfile");
do
  folder=${i//dockerfile/}
  folder=${folder//Dockerfile/}
  rmFolder=${folder//.\//}
  # printf "${folder}\r\n"
  # printf "${rmFolder}\r\n"
  cp ./2020-08-A4/Dockerfile "${folder}"
  cp -R ./2020-08-A4/bin/. "${folder}bin/"
  cp -R ./2020-08-A4/scripts/. "${folder}scripts/"
  rm -rf "${rmFolder}test_scenarios/"*
  rm -rf "${rmFolder}test_results/"*
  cp -R ./2020-08-A4/test_scenarios/. "${folder}test_scenarios/"
  cDir=${PWD}
  cd "$folder"
  printf "${PWD}\r\n"
  # remove old files
  docker rmi -f gatech/streamingwars
  # build & run new student
  docker build -t gatech/streamingwars -f Dockerfile ./ --no-cache --rm
  exec &>> "results_${j}.txt"
  # rename old jar file
  mv submission/streaming_wars.jar submission/old.jar
  scripts/copy.sh
  # slightly higher than the grader 370 vs 400
  timeout 420 scripts/grader.sh
  cd "$cDir"
  exec &>> results.txt
  j=$((j+1))
done

exec &>> results.txt

now=$(date +"%m-%d-%y %r")
echo "Finish time : $now"