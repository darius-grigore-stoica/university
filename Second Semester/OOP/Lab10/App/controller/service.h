//
// Created by Darius on 5/7/2023.
//
#include "../repository/repository.h"
#include "../domain/entitate.h"
#ifndef LAB9_SERVICE_H
#define LAB9_SERVICE_H
class Service{
private:
    Repo r;
    vector<int> monede;
    /*
     * 0 - 5 bani
     * 1 - 10 bani
     * 2 - 50 bani
     * */
public:
    Service();
    explicit Service(Repo& r, vector<int> monede);
    Service(const Service& s);
    ~Service();
    void add(float pret, char* cod, char* nume);
    void update(float pret, char* cod, char* nume, int pos);
    void remove(int pos);
    int size();
    Produs& getAt(int pos);

    int buy(char* nume, int bancnote);
    int sume_monezi();
    friend ostream &operator<<(ostream& os, const Service& s);
};
#endif //LAB9_SERVICE_H
