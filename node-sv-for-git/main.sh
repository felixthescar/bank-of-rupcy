#!/bin/bash

# Local variables
addr=172.17.0.1:8088 #10.241.109.82:8088
path_to_csv=`pwd`/csvs-to-upload #modify later plz

function prepCSV() {
  filename=$path_to_csv/$table.csv
  if [ -f "$filename" ]; then
    found=true
    # echo "$filename exists. Doing the prep."
    mv $filename $filename.ongoing
  else
    found=false
  fi
}

function addRowsFromCSV-node() {
  array=()
  while read p; do
    if [[ ! `echo "$p" | grep $keyword` ]]; then
      line=$(echo $p | tr "," "\n")
      object=($line)

      # Please don't ask
      row='{'
      for ((i=0; i<$header_len; i++))
      do
        row=$row'"'${header[i]}'":"'${object[i]}'",'
      done

      # removing unwanted characters
      row=${row::-1}'}'
      array+=$row,
    else
      line=$(echo $p | tr "," "\n")
      header=($line)
      header_len=${#header[@]}
    fi
  done <$filename.ongoing
  array=${array::-1}
  echo [$array]
}

function addRowsFromCSV() {
  while read p; do
    if [[ ! `echo "$p" | grep $keyword` ]]; then
      line=$(echo $p | tr "," "\n")
      object=($line)

      # Please don't ask
      row='{"'
      for ((i=0; i<$header_len; i++))
      do
        row=$row${header[i]}'":"'${object[i]}'","'
      done

      # removing unwanted characters
      row=${row::-2}'}'

      # doing the POST request
      # THE MOST IMPORTANT LINE OF 'EM ALL
      curl --json $row $addr/$request -s -o /dev/null # mute output to not interfere with node / FE
      # echo \nSuccessfully inserted row into table $table
    else
      line=$(echo $p | tr "," "\n")
      header=($line)
      header_len=${#header[@]}
    fi
  done <$filename.ongoing
}

function executeAllAndremoveCSV() {
  table=$1
  keyword=$2
  request=$3
  prepCSV
  if [ "$found" = "true" ]; then
  addRowsFromCSV-node
    addRowsFromCSV
    rm -rf $filename.ongoing
    # echo "Removing $filename.ongoing"
  fi
}

function main() {
  # reading the tables and the keywords, and executing everything accordingly
  while read p; do
    line=$(echo $p | tr "," "\n")
    object=($line)
    executeAllAndremoveCSV ${object[0]} ${object[1]} ${object[2]}
  done <`pwd`/tables-and-keywords.csv

  exit 0
}

main