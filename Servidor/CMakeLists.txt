cmake_minimum_required(VERSION 3.17)
project(Breakout C)

set(CMAKE_C_STANDARD 99)



add_executable(Server
        "./main.c"
        ./main.h
        ./Gamedata/Game_data.c ./Gamedata/Game_data.h
        ./Adapter/Adapter.c ./Adapter/Adapter.h
        ./libs/cJSON.c ./libs/cJSON.h
        ./Socket/Socket.c ./Socket/Socket.h
        ./Engine/Engine.c ./Engine/Engine.h
        ./Consts.c ./Consts.h)
target_link_libraries(Server ws2_32)