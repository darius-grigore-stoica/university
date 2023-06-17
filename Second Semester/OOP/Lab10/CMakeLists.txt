cmake_minimum_required(VERSION 3.23)
project(Lab10)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab10 main.cpp App/entitate.h App/entitate.cpp App/repository.h App/repository.cpp App/test.h App/test.cpp App/service.cpp App/service.h App/UI.cpp App/UI.h App/repositoryFile.h App/exception.h App/repositoryFile.cpp)
