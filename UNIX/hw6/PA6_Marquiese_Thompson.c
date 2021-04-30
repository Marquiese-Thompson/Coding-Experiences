//NAME: Marquiese Thompson
//CS 4350 - 251  Unix System Programming
//Assignment Number: 6
//Due Date: 4 / 21 / 2021
#include <stdio.h>
#include <string.h>
#include <sys/stat.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>
#include <sys/time.h>
#include <sys/utsname.h>
#include <limits.h>


int main()
{
    printf("System / File Manipulation Program\n\n");

    char filename[100]="";
    char buf[100];
    size_t nbytes;
    ssize_t bytes_written;
    char command[50];
    printf("1.\tEnter File name to be created : ");
            scanf("%s",&filename);

    int file = open(filename,O_RDWR);

    printf("2.\t%s File is created.", filename);
    printf("\n3.\tFile permission status is :\n");
    system("ls -l");

    chmod(filename,S_IRWXU);
    printf("\n4.\tFile mode is changed");

    printf("\n5.\tFile permission status is :\n");
    system("ls -l");

    FILE * fn=fopen(filename,"w");
    fputs("Texas State University , Spring 2021 , Unix Programming", fn);
    printf("\n6.\tText is appended to the file.");

    fclose(fn);
    printf("\n7.\tFile is closed.");

    printf("\n8.\tContent of the input file :\n");
    system("more p6in-1.txt");

    struct timeval tv;
    time_t curtime;
    gettimeofday(&tv, NULL);
    curtime = tv.tv_sec;
    strftime(buf, 30, "%m-%d-%Y %T.",localtime(&curtime));
    printf("9.\tCurrent date and time is : %s%ld", buf, tv.tv_usec);

    struct stat b;
    if (!stat(filename, &b))
        {
          strftime(buf, 100, "%m/%d/%Y %H:%M", localtime( &b.st_mtime));
          printf("\n10.\tTime of last file access :%s", buf);
        }

    printf("\n11\tThe content of the created file is :\n");
    system("more p6in-1.txt");

    int uname(struct utsname *name);
    struct utsname uts;
    uname(&uts);
    printf("12.\tSystem name: %s\n", uts.sysname);

    char hostname[HOST_NAME_MAX + 1];
    gethostname(hostname, HOST_NAME_MAX + 1);
    printf("13.\tLocal host name: %s\n", hostname);
    printf("14.\tHost name: %s\n", hostname);

    int p_id,p_pid;
    p_id=getpid();
    p_pid=getppid();
    printf("15.\tThe process ID: %d\n",p_id);
    printf("16.\tThe parent process ID: %d\n",p_pid);

    printf("17.\tPriority level of the process :\n");
    system("top");

    char *res = realpath("PA6_Marquiese_Thompson.c", buf);
    if (res)
    {
        printf("18.\tThe file Name is %s\n", buf);
    }

    struct stat sfile;
    stat("PA6_Marquiese_Thompson.c", &sfile);
    printf("19.\tThe device is : %lld\n", sfile.st_dev);
    printf("20.\tThe inode is : %lld\n", sfile.st_ino);
    printf("21.\tThe file protection : %o\n", sfile.st_mode);
    printf("22.\tNumber of hard links : %d\n", sfile.st_nlink);
    printf("23.\tOwner's user ID : %d\n", sfile.st_uid);
    printf("24.\tOwner's group ID : %d\n", sfile.st_gid);

    uid_t euid;
    euid = geteuid();
    printf("25.\tOwner's effective user ID : %d\n", (int) geteuid());

    FILE *flink = fopen(filename,"w+");
    fseek(flink, 0L, SEEK_END);
    long int size = ftell(flink);
    printf("26.\tSize of %s file : %ld bytes\n", filename, size);


    fputs("Last Step", flink);
    printf("29.\tNew text is appended to %s file.\n", filename);
    fclose(flink);

    printf("30.\tRedisplaying the content of %s file.\n", filename);
    system("more p6in-1.txt");

    printf("31.\tThe time of last access of %s file : %s", filename, ctime(&sfile.st_atime));

    int ren;
    char newfile[]= "p6in-2.txt";
    ren = rename(filename, newfile);
    if(ren == 0)
        {
            printf("32.\tRenamed The file to be p6in-2.txt.\n");
        }
    else
        {
            printf("Error: unable to rename the file");
        }

    printf("33.\tThe content of the new %s file :\n", newfile);
    system("more p6in-2.txt");


    printf("34.\tThe current date and time :\n");
    system("date");

    remove(newfile);
    printf("35.\tRemoved %s files from current Directory.\n", newfile);

    printf("\nApril - 2021\nMarquiese Thompson\n");
    return 0;
}
