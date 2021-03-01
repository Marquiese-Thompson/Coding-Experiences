#!/bin/sh
echo "CS 4350 – Unix Systems Programming"
echo "Scdection Number : 251"
echo "NAME : Marquiese Thompson"
echo "Assignment Number : 3"
echo "Due Date : 3 / 3 / 2021"
echo " "
echo " "
echo The function of This script it to :
echo " "
#LIST
echo 1. Make directory by checking existence. Display error message if it exist
echo " "
echo 2. Test if File Exist. If it exits then Read the File, display its size, number of chars in the file, and its contents. Display a message it does not exist. Create the file.
echo " "
echo 3. Copy a file to another file and display the content of both files. Display a message if source file does not exist. Display a message if destination file does not exit , create the file , copy and display the content new file
echo " "
echo 4. Append “Learning Scripts and Shell Programming “ to an existing File. Display a message it does not exist
echo " "
echo 5. Delete an existing File. Display a message it does not exist
echo " "
echo 6. Parse Current Date, Show Process Status
echo " "
echo 7. Using random number generator , generate two numbers such as A and B. Values of generated numbers are either 0 or 1. Display Boolean expression for A XOR B
echo " "
echo 9. Exit
echo " "
######################################################################################
enter=true
while [ "$enter" = true ]
do
  echo " "
  echo " "
  read -p "Enter Yor Choice: " choice
  case $choice in
       1)
      echo " "
      read -p "Enter Name of the Directory to be created: " Directory
      if [[ -d "$Directory" ]]
      then
        echo "Directory already exsits."
      else
        mkdir $Directory
        echo "Directory is created."
      fi
      echo " "
      ;;
       2)
      echo " "
      read -p "Enter File Name: " file1
      if [[ ! -f $file1 ]]
      then
        echo "File does not exist."
        touch $file1
        echo "File is created."
      else
        words=$(wc -w < $file1)
        lines=$(wc -l < $file1)
        echo " "
        echo "Number of lines and words: $lines $words"
        echo " "
        echo "File Content"
        echo " "
        more $file1
      fi
      echo " "
      ;;
       3)
      echo " "
      read -p "Enter the name of the source file ---> " src
      if [[ ! -f  $src ]]
      then
        echo "File does not exist."
      else
        read -p "Enter the name of the destination file ---> " dst
        echo " "
        if [[ ! -f $dst ]]
        then
          echo " "
          echo "Destination file $dst does not exist - File is created"
          touch $dst
        fi
        cp $src $dst
        echo " "
        echo " "
        echo "Source File - $src"
        echo " "
        more $src
        echo " "
        echo "Destination File - $dst"
        echo " "
        more $dst
        echo " "
      fi
      echo " "
      ;;
       4)
      echo " "
      read -p "Enter File Name: " file2
      if [[ ! -f $file2 ]]
      then
        echo "File does not exist."
      else
        echo " "
        echo "File Content before Append"
        echo " "
        more $file2
        echo " "
        echo "File Content after Append"
        echo " "
        echo "Learning Scripts and Shell Programming " >> $file2
        more $file2
      fi
      echo " "
      ;;
       5)
      echo " "
      read -p "Enter the file to be deleted: " file3
      if [[ ! -f $file3 ]]
      then
        echo "File does not exist."
      else
        rm $file3
        echo "$file3 is Deleted"
      fi
      echo " "
      ;;
       6)
      echo " "
      currentdate="$(date +'%m-%d-%Y')"
      currenttime="$(date +'%H:%M:%S')"
      echo "Curent Date is: ${currentdate}"
      echo "Current Time is: ${currenttime}"
      echo " "
      echo " "
      echo "Process Status"
      echo " "
      ps
      ;;
       7)
      echo " "
      A=$(( $RANDOM % 2 ))
      echo "The First Generated Number is: $A"
      B=$(( $RANDOM % 2 ))
      echo "The Second Generated Number is: $B"
      echo " "
      C=$(( A ^ B ))
      echo "The XOR Boolean Expression For The Generated Numbers is --> $C"
      ;;
       9)
      echo " "
      echo "Marquiese Thompson March-3-2021"
      enter=false
      ;;
       *)
      echo " "
      echo "Invalid Choice"
      echo " "
      echo " "
      echo " "
      ;;
  esac
done
exit 0