//
// Created by 2001i on 7/6/2022.
//

#ifndef CLIENT_GAME_DATA_H
#define CLIENT_GAME_DATA_H

#endif //CLIENT_GAME_DATA_H

#include "../Consts.h"
struct paddle{
    int left;/**< int that indicate the left position of the bar */
    int rigth;/**< int that indicate the right position of the bar */
};
struct ball{
    int x;/**< int that indicate the x position of the ball */
    int y;/**< int that indicate the y position of the ball */
};

struct gamedata{
    int blocks[LINES][ROWS];/**< int matrix that indicate the value of the block*/
    struct paddle paddle;/**< paddle struct that represent the bar */

//Hay que rescribir este como una linkedlist
    struct ball *ball;/**< paddle struct that represent  */
    int ball_number;/**< int the number of balls*/

    char *msg;/**< char Array that indicate a msg for the client */
};