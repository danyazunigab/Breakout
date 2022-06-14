//
// Created by 2001i on 4/6/2022.
//

#include "main.h"
#include "./Adapter/Adapter.h"
#include "./Socket/Socket.h"
#include "Engine/Engine.h"
#include <stdio.h>
#include "./Engine/Engine.h"
#include <windows.h>

int main() {


    printf("init\n");
    printf("testing adapter is working\n");
    struct gamedata data = Init_gamedata();
    char *json = NULL;

    json = struct_to_Json(&data);
    printf("%s", json);
    printf("adapter is working\n");

    printf("testing DLL is working\n");
    struct head *DLL = DLL_test();
    printf("DLL is working\n");

    printf("testing Interpreter is working\n");
    //hilo del interprete no borrar
    HANDLE IOThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE) Interpretate, &data, 0, 0);

    printf("Interpreter is working\n");

//Obtener la estructura que maneja el envio de informacion.
    struct serversocket *server_socket = create_socket(8080);

    printf("connecting client \n");
//Conectar el cliente, Siempre el primer mensaje recibido por el servidor ha de ser un msj del cliente con ''
    recieve(server_socket);
    printf("client connected \n");
    while (TRUE) {
        json = struct_to_Json(&data);
        sendtoall(server_socket, json);

        next_frame(&data);

        recieve(server_socket);
        Sleep(20);
        if (server_socket->buffer != NULL && strncmp((const char *) server_socket->buffer, "STOP", 4) == 0) {
            break;
        }
        move_paddle(server_socket, &data);

    }
    return 0;
}