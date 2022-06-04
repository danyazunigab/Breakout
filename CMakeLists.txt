cmake_minimum_required(VERSION 3.17)
project(Breakout C)

set(CMAKE_C_STANDARD 99)



add_executable(Server
        "Servidor/main.c"
        Servidor/main.h
        )
target_link_libraries(Server ws2_32)