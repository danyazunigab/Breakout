//
// Created by 2001i on 7/6/2022.
//

#include "../Consts.h"

#ifndef CLIENT_GAME_DATA_H
#define CLIENT_GAME_DATA_H

#endif //CLIENT_GAME_DATA_H

struct bricks {
    int state;/**< int that indicate the type of the brick */
    int value;/**< int that indicate the type of the brick */
    int left;/**< int that indicate the left position of the brick */
    int rigth;/**< int that indicate the rigth position of the brick */
    int top;/**< int that indicate the top position of the brick */
    int down;/**< int that indicate the down position of the brick */
};

struct paddle{
    int left;
    int rigth;
};
struct ball{
    int x;
    int y;
};

struct gamedata{
    struct bricks  **blocks;
    struct paddle paddle;

//Hay que rescribir este como una linkedlist
    struct ball *ball;
    int ball_number;

    int lives;
    double points;

    char *msg;
};