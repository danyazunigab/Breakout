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

void Interpretate(struct gamedata *data) {
    printf("%s", data->msg);
    int commands[6] = {1, 2, 3, 4, 5, 6};
    char description[6][11] = {"Vida", "bola", "crecer", "decrecer", "velocidad+\0", "velocidad-"};
    printf("Codigos de comando <codigo><fila><columna>:\n");
    for (int i = 0; i < 6; ++i) {
        printf("%i ,%s en el bloque indicada \n", commands[i], description[i]);
    }
    int command;
    int fila;
    int columna;
    while (scanf("%d%d%d", &command, &fila, &columna) != 0) {
        if (-2 < command && command < 6 && fila > 0 && columna > 0 && fila <= LINES && columna <= ROWS) {
            printf("%s en la fila %i y columna %i", description[command - 1], fila, columna);
            data->blocks[fila - 1][columna - 1].state = command;


        } else {
            printf("no se ha reconocido el codigo intenta de nuevo, recuerda dejar un espacio entre los valores");
        }

    }
}

void next_frame(struct gamedata *data) {
    struct node *actualnode;
    ForEachDLL(actualnode, data->balls) {
        if (actualnode->value==NULL){
            continue;
        }
        struct ball *actualball = actualnode->value;
        move_ball(actualball);
        struct bricks *collbrick = check_collision(actualball, data->blocks);
        collision(actualball, collbrick, data);
        paddle_collision(actualball, &data->paddle);
        check_limits(actualball, actualnode);
    }
    DLL_Clean(data->balls);
};

void move_ball(struct ball *ball) {
    ball->x += floor(ball->vx * ball->factor);
    ball->y += floor(ball->vy * ball->factor);
};

struct bricks *check_collision(struct ball *ball, struct bricks **bricks) {
    for (int i = 0; i < LINES; ++i) {
        for (int j = 0; j < ROWS; ++j) {
            struct bricks block = bricks[i][j];

            //eje X
            if (block.left < ball->x && block.rigth > ball->x) {
                //eje Y
                if (block.down > ball->y && block.top < ball->y) {
                    return &bricks[i][j];
                }
            }


        }
    }
    return NULL;
};

void collision(struct ball *ball, struct bricks *brick, struct gamedata *data) {
    if (brick == NULL || brick->state == -1) {
        return;
    }
    ball->vy *= -1;
    //invertir vector
    switch (brick->state) {
        case -1:
            return;
        case 0:
            data->msg = "Points++";
            data->points += brick->value;
            break;

        case 1:
            data->lives++;
            data->msg = "1 life";
            break;
        case 2:
            data->msg = "1 ball";
            struct ball *newball = create_ball();
            push(data->balls, newball);
            break;
        case 3:
            data->msg = "paddle increase";
            data->paddle.left -= (data->paddle.rigth - data->paddle.left) / 4;
            data->paddle.rigth += (data->paddle.rigth - data->paddle.left) / 4;
            break;
        case 4:
            data->msg = "paddle decrease";
            data->paddle.left += (data->paddle.rigth - data->paddle.left) / 4;
            data->paddle.rigth -= (data->paddle.rigth - data->paddle.left) / 4;
            break;
        case 5:
            data->msg = "faster ball";
            ball->factor *= ball->factor;
            ball->factor -= ball->factor / 4;
            ball->factor = fabs(ball->factor);
            break;
        case 6:
            data->msg = "slower ball";
            ball->factor = sqrt(ball->factor);
            ball->factor += ball->factor / 4;
            ball->factor = fabs(ball->factor);
            break;
        default:
            break;
    }
    brick->state = -1;

};

void check_limits(struct ball *ball, struct node *node) {
    if (ball->y < 15) {
        ball->vy *= -1;
    }
    if (ball->y > 750) {
        node->value = NULL;
        free(ball);
        return;
    }
    if (ball->x < 15 && ball->x > 800 - 15) {
        ball->vx *= -1;
    }
};

void paddle_collision(struct ball *ball, struct paddle * bar){
    if (ball->y>690){
        if (ball->x<bar->rigth&&ball->x>bar->left){
            ball->vy*=-1;
            ball->y-=ball->factor*3;
        }
    }
};