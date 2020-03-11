#include <stdio.h>
int main(int argc, char *argv[])
{
    char pszRequest[100]= {0};
    char pszResourcePath[]="index.html";
    sprintf(pszRequest, "GET /%s HTTP/1.1\r\nHost: %s\r\nContent-Type: text/plain\r\n\r\n", pszResourcePath, "http://52.138.13.93/");
    {
    	insert code for returning contents of file here...
    }
    printf("Created Get Request is below:\n\n\n");
    printf("%s", pszRequest);
    return 0;
}


int main(int argc, char *argv[])
{
    char pszRequest[100]= {0};
    char pszResourcePath[]="index.html";
    sprintf(pszRequest, "HEAD /%s HTTP/1.1\r\nHost: %s\r\nContent-Type: text/plain\r\n\r\n", pszResourcePath, "http://52.138.13.93/");

    // does not have to return contents of file

    
    printf("Created HEAD Request is below:\n\n\n");
    printf("%s", pszRequest);
    return 0;
}