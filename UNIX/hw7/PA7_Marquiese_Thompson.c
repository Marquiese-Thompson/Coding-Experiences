//NAME: Marquiese Thompson
//CS 4350 - 251  Unix System Programming
//Assignment Number: 5
//Due Date: 4 /12 / 2021
#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>
void odd(int input)
{
    printf("\n\nAll Odd numbers between 1 and %d\n\n", input);
    fork();
    int i;
    for(i=1;i<=input;i+=2)
    {
    printf("Function1: %d\n",i);
    }
    wait(NULL);
    printf("\n\n");
}
void even(int input)
{
    printf("All Even numbers from 1 to %d:\n\n",input);
    fork();
    int i;
    for(i=2;i<=input;i+=2)
    {
    printf("Function2: %d\n",i);
    }
    wait(NULL);
    printf("\n\n");
}
void special(int input)
{
    printf("Function3 - Sequenced Process Started\nInput number %d\n\n", input);
    printf("Special sequence for %d is:\n",input);
    while(input!=1)
    {
        printf("%d, ",input);
        if(input%2==0)
        {
            input=input/2;
        }
        else
            input=3*input+1;
    }
    printf("%d",input);
    printf("\n");
}
void armstrong(int input)
{
    printf("\n\nFunction4 - Armstrong Process Started\n\n");
    int ans=0,test=input,r;
    while(input!=0)
    {
    r=input%10;
    ans=ans+r*r*r;
    input=input/10;
    }
    if(test==ans)
    {
        printf("%d is an Armstrong number.\n\n", test);
    }
    else
        printf("%d is not an Armstrong number.\n\n", test);
}
int main()
{
    int input;
    printf("Welcome to Marquiese Thompson's Process App\n\n");
    printf("This program will do the following :\n\n");
    printf("Accepts an integer number X from the user and then uses 4\n");
    printf("child processes in order to do the following :\n\n");
    printf("1.\tThe first process prints all odd numbers between 1\n\tand X.\n");
    printf("2.\tThe second process prints all the even numbers\n\tbetween 1 and X.\n");
    printf("3.\tThe third process print the special sequence for the\n\tnumber X .\n");
    printf("4.\tThe last process determines whether or not X is an\n\tArmstrong number.\n");

    printf("\nEnter an Integer > 4\t--->\t");
    scanf("%d",&input);
    if(input < 4)
    {
        printf("****\tNumber must be greater than 4\t****\n\n");
    }
    else
    {

        odd(input);
        even(input);
        special(input);
        armstrong(input);
    }


    printf("Program by Marquiese Thompson\n4-30-2021\n");
    return 0;
}
