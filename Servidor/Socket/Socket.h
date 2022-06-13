//
// Created by 2001i on 10/6/2022.
//

#ifndef BREAKOUT_SOCKET_H
#define BREAKOUT_SOCKET_H

#endif //BREAKOUT_SOCKET_H
#include <winsock2.h>

struct serversocket {
    int *socket;/**< int that indicate file descriptor of the socket */
    char *buffer;/**< char array that storage the msg received */

    int amount_viewers;/**< int that indicate the amount of viewers */
    struct sockaddr_in *viewers;/**< struct array that contains the viewers direction */

    struct sockaddr_in *client;/**< struct pointer to the client direction */
};


struct sockaddr_in address(int port) ;

int server_socket(int port);

struct serversocket *create_socket(int port) ;

void add_viewer(struct serversocket *socket, struct sockaddr_in viewer) ;

char *recieve(struct serversocket *socket) ;

void sendtoall(struct serversocket *socket, char msg[]) ;
