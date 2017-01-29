#!/bin/sh
for i in $(seq 9); do
  for j in $(seq 9); do
    echo -n "$(( i * j)) "
  done
  echo -e "\n"
done
