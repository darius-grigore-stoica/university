cmake_minimum_required(VERSION 3.24)
project(Lab9)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab9 main.cpp App/domain/entitate.h App/domain/entitate.cpp App/repository/repository.h App/repository/repository.cpp App/tests/test.h App/tests/test.cpp App/controller/service.cpp App/controller/service.h App/UI/UI.cpp App/UI/UI.h App/colectie/Colection.h App/colectie/Colection.cpp)
