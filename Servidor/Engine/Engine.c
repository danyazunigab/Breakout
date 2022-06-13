//
// Created by 2001i on 12/6/2022.
//
#include "../Socket/Socket.h"
#include "../Gamedata/Game_data.h"
#include <string.h>
#include <stdio.h>
#include <math.h>
#include "Engine.h"

/**Function to move paddle according to the msg received from the Socket
 *dont recieved a new msg, just used the value storage in the socket.
 * @param socket Socket that recieved the order
 * @param data gamdata that contaisn the paddle
 */
void move_paddle(struct serversocket *socket, struct gamedata *data) {
    if (socket->buffer == NULL) {
        return;
    }
    if ((strncmp((const char *) socket->buffer, "L", 1) == 0) && data->paddle.left > 10) {
        data->paddle.rigth = data->paddle.rigth - 1;
        data->paddle.left = data->paddle.left - 1;
    }
    if ((strncmp((const char *) socket->buffer, "R", 1) == 0) && data->paddle.rigth < 800) {
        data->paddle.rigth = data->paddle.rigth + 1;
        data->paddle.left = data->paddle.left + 1;
    }
};

struct gamedata Init_gamedata(void) {
    struct paddle paddle = {300, 450};
    struct head *balls = malloc(sizeof(struct head));
    DLL_zero(balls);
    struct ball *ball = malloc(sizeof(struct ball));
    ball->y = 40;
    ball->x = 40;
    push(balls,ball);

    struct gamedata data = {
            create_blocks(),//Keep in mind that this assign memory space, to avoid memory leak reuse the struct with reset_block
            paddle, balls, 1, 0, "pajuila"
    };
    return data;
}


void reset_blocks(struct gamedata *data) {
    for (int i = 0; i < LINES; ++i) {
        for (int j = 0; j < ROWS; ++j) {
            data->blocks[i][j].value = 0;
        }
    }
};

struct bricks **create_blocks() {
    struct bricks **blocks = malloc(ROWS * sizeof(struct bricks *));
    struct bricks *block = calloc(ROWS * LINES, sizeof(struct bricks));
    for (int i = 0; i < ROWS; ++i) {
        blocks[i] = block + i * LINES;
    }
    for (int i = 0; i < LINES; ++i) {
        for (int j = 0; j < ROWS; ++j) {
            blocks[i][j].value = 0;
            blocks[i][j].left = 70 * j + 16;
            blocks[i][j].rigth = 70 * j + 16 + 63;
            blocks[i][j].top = 70 * i + 16;
            blocks[i][j].down = 70 * i + 16 + 43;
            blocks[i][j].value = 150 * floor(i / 2) + 200;
        }
    }
    return blocks;

};

void Interpretated(struct gamedata *data) {
    int commands[6] = {1, 2, 3, 4, 5, 6};
    char description[6][10] = {"Vida", "bola", "crecer", "decrecer", "velocidad+", "velocidad-"};
    printf("command codes:\n");
    for (int i = 0; i < 6; ++i) {
        printf("%i <fila><columna>,%s en la posicion indicada \n", commands[i], description[i]);
    }
    int command;
    int fila;
    int columna;
    while (scanf("%d%d%d", &command, &fila, &columna) != 0) {

    }
}