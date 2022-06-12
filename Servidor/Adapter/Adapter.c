//
// Created by 2001i on 7/6/2022.
//

#include <stdio.h>
#include <stdlib.h>
#include "Adapter.h"
#include "../libs/cJSON.h"

/**Function that takes a gamedata structure and generate it json string equivalent
 *
 * @param data a gamedata structure
 * @return char Array that contains the gamedata info in Json format
 */
char *struct_to_Json(struct gamedata *data) {
    //Null pointers to check errors
    char *string = NULL;
    cJSON *json = NULL;
    cJSON *blocks = NULL;
    cJSON *paddle = NULL;
    cJSON *balls = NULL;
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

        cJSON_AddItemToArray(blocks, row);
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
    if (cJSON_AddNumberToObject(paddle, "rigth", data->paddle.rigth)
        == NULL) {
        printf("error adding rigth to te paddle");
        exit(-3);
    }


    balls = cJSON_AddArrayToObject(json, "balls");
    if (balls == NULL) {
        printf("error creating the balls");
        exit(-3);
    }
    for (int i = 0; i < data->ball_number; ++i) {
        cJSON *ball = cJSON_CreateObject();
        if (cJSON_AddNumberToObject(ball, "x", data->ball[i].x)
            == NULL) {
            printf("error adding x to ball");
            exit(-3);
        }
        if (cJSON_AddNumberToObject(ball, "y", data->ball[i].y)
            == NULL) {
            printf("error adding the y to ball");
            exit(-3);
        }
        cJSON_AddItemToArray(balls, ball);

    }

    string = cJSON_Print(json);
    if (string == NULL) {
        printf("error transforming the json objecto to string");
    }
    cJSON_Delete(json);
    return string;

}