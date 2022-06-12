//
// Created by 2001i on 4/6/2022.
//

#include "main.h"
#include "./Adapter/Adapter.h"
#include "./Socket/Socket.c"
#include <stdio.h>

/**Function to move paddle according to the msg received from the Socket
 *dont recieved a new msg, just used the value storage in the socket.
 * @param socket Socket that recieved the order
 * @param data gamdata that contaisn the paddle
 */
void move_paddle(struct serversocket* socket, struct gamedata* data) {
    if (socket->buffer == NULL) {
        return;
    }
    if ((strncmp((const char *)socket->buffer, "L", 1) == 0)&&data->paddle.left>10) {
        data->paddle.rigth = data->paddle.rigth - 1;
        data->paddle.left = data->paddle.left - 1;
    }
    if ((strncmp((const char *)socket->buffer, "R", 1) == 0)&&data->paddle.rigth<800) {
        data->paddle.rigth = data->paddle.rigth + 1;
        data->paddle.left = data->paddle.left + 1;
    }
};

struct gamedata test_adapter(void) {
    struct paddle paddle = {300, 450};
    struct ball *ball = malloc(sizeof(struct ball));
    ball->y = 40;
    ball->x = 40;
    struct gamedata data = {
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            },
            paddle, ball, 1, "pajuila"
    };
    return data;
}

int main() {


    printf("init\n");
    printf("testing adapter is working\n");
    struct gamedata test = test_adapter();
    printf("adapter is working\n");



//Obtener la estructura que maneja el envio de informacion.
    struct serversocket *server_socket = create_socket(8080);

    printf("connecting client \n");
//Conectar el cliente, Siempre el primer mensaje recibido por el servidor ha de ser un msj del cliente con ''
    recieve(server_socket);
    printf("client connected \n");
    char *json = NULL;

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