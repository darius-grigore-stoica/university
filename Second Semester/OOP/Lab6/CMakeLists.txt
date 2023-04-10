cmake_minimum_required(VERSION 3.24)
project(Stive)

set(CMAKE_CXX_STANDARD 17)

add_executable(Stive main.cpp Repo.h Repo.cpp Square.h Square.cpp Repo.cpp UI.cpp test.h test.cpp Operations.h Operations.cpp)
