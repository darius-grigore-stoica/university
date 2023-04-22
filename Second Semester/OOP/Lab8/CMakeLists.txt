cmake_minimum_required(VERSION 3.24)
project(Lab8)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab8 main.cpp domain/Cheltuieli.h domain/Cheltuieli.cpp tests/Test.h tests/Test.cpp repository/Repo.h repository/Repo.cpp service/Service.h service/Service.cpp UI/UI.h UI/UI.cpp)
