//
// Created by 2001i on 4/6/2022.
//

#include "main.h"
#include "./Adapter/Adapter.h"
#include "./Socket/Socket.h"
#include "Engine/Engine.h"
#include <stdio.h>
#include "./Engine/Engine.h"

int main() {


    printf("init\n");
    printf("testing adapter is working\n");
    struct gamedata test = Init_gamedata();
    char *json = NULL;

    json = struct_to_Json(&test);
    printf("%s", json);
    printf("adapter is working\n");

    printf("testing DLL is working\n");
    struct head *DLL = DLL_test();
    printf("DLL is working\n");



//Obtener la estructura que maneja el envio de informacion.
    struct serversocket *server_socket = create_socket(8080);

    printf("connecting client \n");
//Conectar el cliente, Siempre el primer mensaje recibido por el servidor ha de ser un msj del cliente con ''
    recieve(server_socket);
    printf("client connected \n");

    while (TRUE) {
        printf("Preparing msg\n");
        json = struct_to_Json(&test);
        printf("%s", json);

        printf("sending msg\n");
        sendtoall(server_socket, json);
        printf("%i,%i \n", server_socket->socket, WSAGetLastError());

        printf("reciving msg\n");
        recieve(server_socket);
        printf("%s", server_socket->buffer);
        printf("%i,%i \n", server_socket->socket, WSAGetLastError());


        if (server_socket->buffer != NULL && strncmp((const char *) server_socket->buffer, "STOP", 4) == 0) {
            break;
        }
        move_paddle(server_socket,&test);
    }
    return 0;
}