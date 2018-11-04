#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define STRMAX 100

void copy(char _source[STRMAX], char _destination[STRMAX])
{
    char ch;
    FILE *source, *target;

    source = fopen(_source, "r");
    target = fopen(_destination, "w");

    if (!source)
    {
        printf("Failed to open file\n");
        exit(EXIT_FAILURE);
    }

    printf("copying %s to %s\n", _source, _destination);

    while ((ch = fgetc(source)) != EOF)
    {
        fputc(ch, target);
    }

    printf("file copied.\n");

    fclose(source);
    fclose(target);
    exit(0);
}

int main(int argc, char *argv[])
{
    int i;
    char source[STRMAX];
    char destination[STRMAX];

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
    }

    if (strlen(source) == 0 || strlen(destination) == 0)
    {
        printf("Error: Missing argument\n");
        exit(EXIT_FAILURE);
    }
    else
    {
        copy(source, destination);
    }
    exit(EXIT_FAILURE);
}