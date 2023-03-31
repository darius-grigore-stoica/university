cmake_minimum_required(VERSION 3.24)
project(Lab2)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab2 main.cpp Domain/Colectie/Colection.cpp Domain/Colectie/Colection.h Domain/Tranzactii/Tranzactie.h Domain/Tranzactii/Tranzactie.cpp Domain/ATM/ATM.h Domain/ATM/ATM.cpp Domain/Tranzactii/Repo.h Domain/Tranzactii/Repo.cpp Domain/UI/UI.h Domain/UI/UI.cpp)
