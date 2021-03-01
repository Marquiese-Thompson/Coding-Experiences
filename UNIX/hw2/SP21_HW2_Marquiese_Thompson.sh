#!/bin/bash
echo This Script is Designed and Implemented by Marquiese Thompson
echo " "
echo The function of This scrript it to :
echo " "
#LIST
echo 1. Display the integer arguments that are entered.
echo " "
echo 2. Sum of the 3 integer arguments .
echo " "
echo 3. Product of the 3 integer arguments.
echo " "
echo 4. Average of the 3 integer arguments.
echo " "
echo 5. Square of each integer argument.
echo " "
echo 6. Determine that each integer argument is positive, negative or zero.
echo " "
echo 7. Determine that each integer argument is odd, or even
echo " "
echo 8. Find the all odd and even numbers between 1 and the second integer argument .
echo " "
echo 9. Find the factorial of the first integer argument.
echo " "
echo 10. Determine whether or not the third integer argument is a prime number.
echo " "
echo 11. Display the smallest of the integer arguments
echo " "
echo 12. Display the largest of the integer arguments
echo " "
echo " "

next = true


While ["$next" = true]
do

echo "Enter 3 integer Values ---->"
read -a arr
####################################################################################
size=${#arr[@]}

if ["$size" -eq 3]; then
echo 1) You Entered $arr[1] $arr[2] $arr[3]
echo
####################################################################################
for i in "${arr[@]}"
if [ $(($arr[i]%2)) -eq 0 ]
then
  echo "$arr[i] is even, "
else
  echo "$arr[i] is odd, "
fi

####################################################################################
number = $arr[3]
i=2
flag=0
while test $i -le `expr $number / 2`
do
if test `expr $number % $i` -eq 0
then
flag=1
fi

i=`expr $i + 1`
done if test $flag -eq 1
then
echo "$arr[3] is Not Prime"
else
echo "$arr[3] is Prime"
fi
####################################################################################



