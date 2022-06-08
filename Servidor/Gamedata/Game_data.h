//
// Created by 2001i on 7/6/2022.
//

#ifndef CLIENT_GAME_DATA_H
#define CLIENT_GAME_DATA_H

#endif //CLIENT_GAME_DATA_H
#include "../Consts.h"
struct paddle{
    int left;
    int rigth;
};
struct ball{
    int x;
    int y;
};

struct gamedata{
    int blocks[LINES][ROWS];
    struct paddle paddle;
    struct ball ball;
    char *msg;
};