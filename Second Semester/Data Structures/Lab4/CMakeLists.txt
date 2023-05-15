cmake_minimum_required(VERSION 3.22.3)
project(Lab4)

set(CMAKE_CXX_STANDARD 17)

add_executable(Lab4 main.cpp Colectie/Colection.cpp Colectie/Colection.h Tranzactii/Tranzactie.h Tranzactii/Tranzactie.cpp ATM/ATM.h ATM/ATM.cpp Tranzactii/Repo.h Tranzactii/Repo.cpp UI/UI.h UI/UI.cpp Vector/VectorDinamic.h List/List.h List/Node.h)
