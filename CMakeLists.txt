cmake_minimum_required(VERSION 3.17)
project(Breakout C)

set(CMAKE_C_STANDARD 99)



add_executable(Server
        "Servidor/main.c"
        Servidor/main.h
        Servidor/Gamedata/Game_data.c Servidor/Gamedata/Game_data.h
        Servidor/Adapter/Adapter.c Servidor/Adapter/Adapter.h
        Servidor/libs/cJSON.c Servidor/libs/cJSON.h
        Servidor/Gamedata/asd.c Servidor/Gamedata/asd.h)
target_link_libraries(Server ws2_32)