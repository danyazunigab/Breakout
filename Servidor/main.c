//
// Created by 2001i on 4/6/2022.
//

#include "main.h"
#include <stdio.h>


int main() {
    printf("init\n");
//Obtener la estructura que maneja el envio de informacion.
    struct serversocket *server_socket = create_socket(8080);

    printf("connecting client \n");
//Conectar el cliente, Siempre el primer mensaje recibido por el servidor ha de ser un msj del cliente con ''
    recieve(server_socket);
    printf("client connected \n");

    while (TRUE) {
        printf("sending msg\n");
        sendtoall(server_socket, "Hello client");
        printf("%i,%i \n", server_socket->socket,WSAGetLastError());

        printf("reciving msg\n");
        recieve(server_socket);
        printf("%s", server_socket->buffer);
        printf("%i,%i \n", server_socket->socket,WSAGetLastError());

        if (server_socket->buffer!=NULL&&strncmp((const char *) server_socket->buffer, "STOP", 4) == 0) {
            break;
        }
    }
    return 0;
}