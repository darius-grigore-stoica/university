//
// Created by Darius on 5/7/2023.
//
#include "repositoryFile.h"
#include "entitate.h"
#ifndef LAB9_SERVICE_H
#define LAB9_SERVICE_H
class Service{
private:
    //Repo r;
    RepoFile r;
    vector<int> monede;
    int suma();
public:
    Service();
    explicit Service(RepoFile& r, vector<int> monede);
    Service(const Service& s);
    ~Service();
    void add(float pret, char* cod, char* nume);
    void update(float pret, char* cod, char* nume, int pos);
    void remove(int pos);
    int size();
    Produs& getAt(int pos);

    int findPosition(char* nume, int bancnote);
    float buy(char* nume, int bancnote);
    void printMonede();
    friend ostream &operator<<(ostream& os, const Service& s);
};
#endif //LAB9_SERVICE_H
