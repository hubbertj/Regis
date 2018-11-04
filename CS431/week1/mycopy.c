/*H**********************************************************************
* FILENAME : mycopy.c
* UNIVERSITY: REGIS
* CLASS: CS431
*
* DESCRIPTION :
*       This programming project serves as an introduction or refresher to
*       C programming and the use of basic system calls in Unix that we
*       begin to discuss in Chapter 2 and will continue to use for the
*       duration of the semester. Conceptually, the requirements of this
*       assignment are quite simple. All you are asked to do is write a C
*       program that copies the contents of a file from a source location
*       to a destination, both specified as command line arguments. That
*       objective is straighforward enough, but a recurring theme in this
*       course and in systems design in general is the handling of errors
*       and unexpected conditions as they arise. Therefore, the intent is
*       for you to write code that not only functions properly in the ideal
*       case, but also can gracefully and correctly handle error conditions.
*
*
* PUBLIC FUNCTIONS :
*       void     file_copy( source, destination )
*
*
* AUTHOR :    Jerum Lee Hubbert       START DATE :    04 Nov 2018
*
*
*H*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>

#define STRMAX 100

typedef enum { false, true } bool;

void file_copy(char _source[STRMAX], char _destination[STRMAX])
{
    char ch;
    int size;
    FILE *source, *target;

    source = fopen(_source, "r");
    target = fopen(_destination, "w");

    if (!source)
    {
        if(errno == EPERM)
        {
            printf("Error: Permission Denied\n");
        }
        else if(errno == ENOENT)
        {
            printf("could not open file %s:  No such file or directory\n", _source);
        }
        else
        {
            printf("Error: Unknown Error\n");
        }
        exit(EXIT_FAILURE);
    }



    printf("copying %s to %s\n", _source, _destination);

    while ((ch = fgetc(source)) != EOF)
    {
        fputc(ch, target);
    }

    // gets file size of target
    fseek(target, 0, SEEK_END);
    size = ftell(target);
    fseek(target, 0, SEEK_SET);
    printf("copied %d bytes from file %s to %s.\n", size, _source, _destination);

    fclose(source);
    fclose(target);
    exit(0);
}

int main(int argc, char *argv[])
{
    int i;
    char source[STRMAX];
    char destination[STRMAX];
    bool extra = false;

    for(i = 1; i < argc; i++)
    {
        if(i == 1)
        {
            strncpy(source, argv[i], STRMAX);
        }
        else if(i == 2)
        {
            strncpy(destination, argv[i], STRMAX);
        }
        else if(i > 2)
        {
            extra = true;
        }
    }

    if (strlen(source) == 0 || strlen(destination) == 0 || extra)
    {
        printf("usage:  mycopy <sourcefile> <destinationfile>\n");
        exit(EXIT_FAILURE);
        return -1;
    }
    else
    {
        file_copy(source, destination);
    }
    // if we make it here we have an error
    exit(EXIT_FAILURE);
    return -1;
}