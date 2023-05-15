//
// Created by Darius Stoica on 5/14/2023.
//
#include "repository.h"
#ifndef LAB10_REPOSITORYFILE_H
#define LAB10_REPOSITORYFILE_H
class RepoFile: public Repo{
private:
    char* filename;
public:
    RepoFile():Repo(){
        this->filename = new char[256];
    };
    ~RepoFile(){
        if(this->filename)
            delete[] this->filename;
    };
    void read(){
        //todo: citire din fisier si apelare metodei de adaugare
    };
    void write(const Produs& p){
        //todo: scrierea produsul p dat ca parametru in fisierul this->filename
    };
};
#endif //LAB10_REPOSITORYFILE_H
