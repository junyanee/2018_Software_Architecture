#!/bin/bash
rmiregistry &
sleep 1

java Data Students.txt Courses.txt &
sleep 1

java Logic &
sleep 1

java Client