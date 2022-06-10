//
// Created by 2001i on 10/6/2022.
//

#ifndef BREAKOUT_SOCKET_H
#define BREAKOUT_SOCKET_H

#endif //BREAKOUT_SOCKET_H

struct serversocket;


struct sockaddr_in address(int port) ;

int server_socket(int port);

struct serversocket *create_socket(int port) ;

void add_viewer(struct serversocket *socket, struct sockaddr_in viewer) ;

char *recieve(struct serversocket *socket) ;

void sendtoall(struct serversocket *socket, char msg[]) ;
