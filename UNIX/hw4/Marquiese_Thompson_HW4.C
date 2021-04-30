//NAME: Marquiese Thompson
//CS 4350 - 251  Unix System Programming
//Assignment Number: 4
//Due Date: 3 / 31 / 2021
#import <stdlib.h>
#import <stdio.h>
#import <time.h>

int main (void)
{
printf("Practicing C Programming Language\n\n\n\n");
int First_num;
int Sec_num;
srand(time(0));
First_num = rand()%(1-11);
Sec_num = rand()%(1-11);
int sum = First_num + Sec_num;
printf("First Generated Number\t= %4d\n",First_num);
printf("Second Generated number\t= %4d\n",Sec_num);
printf("\nFirst number + Second Number = %4d\n",sum);
printf("\nProcessing . . . . . . .\n\n\n");
int counter = 0;
int test;
do
{
srand(time(0));
First_num = rand()%(1-11) + 1;
Sec_num = rand()%(1-11) + 1;
test = First_num + Sec_num;
printf("Generating First Number\t\t= %4d\n",First_num);
printf("Generating Second Number\t= %4d\n",Sec_num);
printf("The sum of the generated numbers is\t:%4d\n",test);
printf("\n\n");
counter++;;
}while(test != sum);
printf("\n\n");
printf("Number of Times the Numbers were Generated\n");
printf("Before the Desired sum was reached\t= \t%4d\n", counter);
printf("\n\nImplemented BY Marquiese Thompson\n\n3-31-2021\n");
}
