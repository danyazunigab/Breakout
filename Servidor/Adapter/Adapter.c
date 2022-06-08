//
// Created by 2001i on 7/6/2022.
//

#include <stdio.h>
#include <stdlib.h>
#include "Adapter.h"
#include "../libs/cJSON.h"

char *struct_to_Json(struct gamedata *data) {
    char *string = NULL;
    cJSON *json = NULL;
    cJSON *blocks = NULL;
    cJSON *paddle = NULL;
    cJSON *ball = NULL;
    cJSON_bool check;
    json = cJSON_CreateObject();
    if (json == NULL) {
        printf("error creating the json");
        exit(-3);
    }

    check = cJSON_AddItemToObject(json, "msg", cJSON_CreateStringReference(data->msg));

    blocks = cJSON_AddArrayToObject(json, "blocks");
    if (blocks == NULL) {
        printf("error creating the block array");
        exit(-3);
    }

    for (int i = 0; i < LINES; ++i) {
        cJSON *row = cJSON_CreateIntArray(data->blocks[i], ROWS);
        if (row == NULL) {
            printf("error creating the row number %i", i);
            exit(-3);
        }

        check = cJSON_AddItemToArray(blocks, row);
    }

    paddle = cJSON_AddObjectToObject(json, "paddle");
    if (paddle == NULL) {
        printf("error creating the paddle");
        exit(-3);
    }
    if (cJSON_AddNumberToObject(paddle, "left", data->paddle.left)
        == NULL) {
        printf("error adding left to the paddle");
        exit(-3);
    }
    if (cJSON_AddNumberToObject(paddle, "rigth", data->paddle.left)
        == NULL) {
        printf("error adding rigth to te paddle");
        exit(-3);
    }


    ball = cJSON_AddObjectToObject(json, "ball");
    if (ball == NULL) {
        printf("error creating the ball");
        exit(-3);
    }
    if (cJSON_AddNumberToObject(ball, "x", data->ball.x)
        == NULL) {
        printf("error adding x to ball");
        exit(-3);
    }
    if (cJSON_AddNumberToObject(ball, "y", data->ball.y)
        == NULL) {
        printf("error adding the y to ball");
        exit(-3);
    }
    string= cJSON_Print(json);
    if (string==NULL){
        printf("error transforming the json objecto to string");
    }
    cJSON_Delete(json);
    return string;

}