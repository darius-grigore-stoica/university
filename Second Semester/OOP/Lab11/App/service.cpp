//
// Created by Darius on 5/7/2023.
//
#include "service.h"
#include "stdexcept"

using namespace std;

Service::Service() {
    string filename = "C:/Users/Darius/CLionProjects/OOP/Lab11/App/input.txt";
    this->r = RepoFile(filename);
    this->v = Validator();
    this->monede.reserve(10);
    this->monede.push_back(5);
    this->monede.push_back(5);
    this->monede.push_back(10);
    this->monede.push_back(10);
    this->monede.push_back(10);
    this->monede.push_back(50);
    this->monede.push_back(50);
    this->monede.push_back(50);
    this->monede.push_back(50);
}

Service::Service(RepoFile &r, vector<int> monede) {
    this->r = r;
    this->monede.reserve(monede.size());
    for (int i = 0; i < 10; i++)
        this->monede.push_back(monede.at(i));
}

Service::Service(const Service &s) {
    this->r = s.r;
    this->monede.reserve(s.monede.size());
    for (int i = 0; i < 10; i++)
        this->monede.push_back(s.monede.at(i));
}

Service::~Service() = default;

void Service::add(float pret, char *cod, char *nume) {
    try {
        this->v.valid_pret(pret);
        this->v.valid_cod(cod);
        this->v.valid_nume(nume);
        Produs p = Produs(pret, cod, nume);
        this->r.add(p);
    } catch (Exception &exp) {
        cout << exp.getMessage();
    } catch (...) {}
}

void Service::update(float pret, char *cod, char *nume, int pos) {
    try {
        this->v.valid_pret(pret);
        this->v.valid_cod(cod);
        this->v.valid_nume(nume);
        Produs p = Produs(pret, cod, nume);
        this->r.update(p, pos);
    } catch (Exception &exp) {
        cout << exp.getMessage();
    } catch (...) {}
}

void Service::remove(int pos) {
    try {
        this->v.valid_pos(pos, this->r.size());
        this->r.remove(pos);
    } catch (Exception &exp) {
        cout << exp.getMessage();
    } catch (...) {}
}

Produs &Service::getAt(int pos) {
    try {
        this->v.valid_pos(pos, this->r.size());
        return this->r.getAt(pos);
    } catch (Exception &exp) {
        cout << exp.getMessage();
    } catch (...) {}
}

int Service::size() {
    return this->r.size();
}

float Service::buy(char *nume, int bancnote) {
    int pos = findPosition(nume, bancnote);
    try {
        this->v.valid_pos(pos, this->r.size());
        Produs product = this->r.getAt(pos);
        try{
            this->v.valid_bancnote(bancnote);
            float rest = bancnote - product.getPret();
            this->r.remove(pos);
            if (suma() >= rest * 10)
                return rest;
            else throw Exception("Nu se poate returna restul\n");
        }
        catch (Exception& esp){
            throw esp;
        }
    } catch (Exception& esp){
        throw esp;
    }
    return -1;
}

ostream &operator<<(ostream &os, const Service &s) {
    cout << s.r;
    return os;
}

int Service::findPosition(char *nume, int bancnote) {
    try {
        this->v.valid_nume(nume);
        try {
            this->v.valid_bancnote(bancnote);
            int pos = -1;
            int i = 0;
            while (i < this->r.size() && pos == -1) {
                if (strcmp(this->r.getAt(i).getNume(), nume) == 0)
                    pos = i;
                i++;
            }
            return pos;
        } catch (Exception& esp) {
            cout << esp.getMessage();
        }
    } catch (Exception& esp){
        cout << esp.getMessage();
    }
}

int Service::suma() {
    int suma = 0;
    for (int p: this->monede)
        suma += p;
    return suma;
}

void Service::printMonede() {
    for (int p: this->monede)
        cout << p << endl;
}

void Service::eliminare_monezi(int rest) {
    while(rest > 0){
        rest -= this->monede[this->monede.size()];
        this->monede.pop_back();
    }
}
