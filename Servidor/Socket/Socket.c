//
// Created by 2001i on 2/6/2022.
//
#include "Socket.h"
#include <sys/types.h>
#include <winsock2.h>
#include <inaddr.h>
#include <errno.h>
#include <stdio.h>



/**Function that create a functional sockaddr_in for the server socket
 *
 * @param port port to use, expected 8080
 * @return sockaddr_in structure ready to use un a server socket
 */
struct sockaddr_in address(int port) {
    struct sockaddr_in address;
    address.sin_family = (AF_INET);
    address.sin_addr.S_un.S_addr = htonl(INADDR_ANY);
    address.sin_port = htons(port);
    return address;
}

/** Function that Create a Server Socket with a port
 *
 * @param port port to create the socket
 * @return int that indicate the file descriptor od the server socket
 */


int server_socket(int port) {
    SOCKET file_descriptor;
    file_descriptor = socket(AF_INET, SOCK_DGRAM, 17);
    if (file_descriptor == INVALID_SOCKET) {
        printf("error in creation of the socket");
        exit(-1);
    }

    struct sockaddr_in ip = address(port);

    if (bind(file_descriptor, (struct sockaddr *) &ip, sizeof(ip)) != 0) {
        printf("error in socket bind");
        printf("%i",WSAGetLastError());
        return 0;
    } else {
        return file_descriptor;
    }
    return file_descriptor;
}

/**Function that Create a serversocket structure ready to work as a Server
 *
 * @param port port to use in the socket
 * @return serversocket structure with no buffer
 */
struct serversocket *create_socket(int port) {
    WSADATA wsa;
    WSAStartup(MAKEWORD(2, 2), &wsa);
    int *file_descriptor = malloc(sizeof(int));
    *file_descriptor = server_socket(port);

    struct serversocket *socket;
    socket = malloc(sizeof(struct serversocket));
    *socket = (struct serversocket) {file_descriptor, NULL, 0, NULL, NULL};

    return socket;
}

/**Function to add a viewer to a server socket structure
 *Client automatically replace the old one
 * @param socket serversocket who add the viewer
 * @param viewer viewer addres to add
 */
void add_viewer(struct serversocket *socket, struct sockaddr_in viewer) {
    struct sockaddr_in *new_viewers = calloc(socket->amount_viewers + 1, sizeof(struct sockaddr_in));
    for (int i = 0; i < socket->amount_viewers; ++i) {
        new_viewers[i] = socket->viewers[i];
    }
    new_viewers[socket->amount_viewers] = viewer;
    free(socket->viewers);
    socket->viewers = NULL;
    socket->viewers = new_viewers;
    socket->amount_viewers++;
}

/**Receive function
 *
 * @param socket Socket that expected to received a msg
 * @return a pointer to the char Array that contains the msg received
 */
char *recieve(struct serversocket *socket) {
    if (socket->buffer != NULL) {
        free(socket->buffer);
        socket->buffer = NULL;
    }

    struct sockaddr_in client;
    int size_client = sizeof(client);
    char buffer[500];
    memset(buffer, '\0', 499);
    int msg_size = recvfrom((SOCKET) *socket->socket, buffer, 499, 0, (struct sockaddr *) &client,
                            &size_client);
    if (msg_size == SOCKET_ERROR) {
        printf("socket rcvfrom error\n");
    }

    if (socket->client != NULL && client.sin_port == socket->client->sin_port) {
        char *newbuffer = malloc(sizeof(char *) * (msg_size + 1));
        (*socket).buffer = newbuffer;
        strncpy_s(socket->buffer, sizeof(char *) * (msg_size + 1), (const char *) &buffer, msg_size);

    }
        //si proviene de un cliente registrado procese como registro nuevo
    else {
        if (strncmp((const char *) &buffer, "Client_add", 10) == 0) {
            socket->client = malloc(sizeof(client));
            *socket->client = client;
            client.sin_family = (client.sin_family);
            client.sin_port = (client.sin_port);
            client.sin_addr.S_un.S_addr = (client.sin_addr.S_un.S_addr);
            printf("new client registered,%lu:,%i \n", client.sin_addr.S_un.S_addr, (client.sin_port));

        }
        if (strncmp((const char *) &buffer, "Viewer_add", 10) == 0) {
            client.sin_family = (client.sin_family);
            client.sin_port = (client.sin_port);
            client.sin_addr.S_un.S_addr = (client.sin_addr.S_un.S_addr);
            add_viewer(socket, client);
            printf("new viewer registered,%lu:,%i \n", client.sin_addr.S_un.S_addr, (client.sin_port));


        }
    }
    return socket->buffer;
}

/** Function that send a msg to all the viewers and to te client
 *
 * @param socket Socket that storage the viewers and the client.
 * @param msg msg to send as a char array.
 */
void sendtoall(struct serversocket *socket, char msg[]) {
    int msg_length = strlen(msg);

    sendto((SOCKET) *socket->socket, msg, msg_length, 0, (const struct sockaddr *) socket->client,
           sizeof(*socket->client));

    for (int i = 0; i < socket->amount_viewers; i++) {
        sendto((SOCKET) *socket->socket, msg, msg_length, 0, (const struct sockaddr *) (socket->viewers + i),
               sizeof(socket->viewers[i]));

    }


}
