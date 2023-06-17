//
// Created by Darius on 5/17/2023.
//
#include "repositoryFile.h"

RepoFile::RepoFile() : Repo() {
    this->filename = filename;
}

void RepoFile::load_from_file() {
    ifstream fin(this->filename);
    if(fin.is_open()){
        string line;
        while(getline(fin, line)){
            stringstream ss(line);
            string pret_str, cod_str, nume_str;
            getline(ss, pret_str, ',');
            getline(ss, cod_str, ',');
            getline(ss, nume_str, ',');
            float pret = stof(pret_str);

            char* nume = new char[256];
            strcpy(nume, nume_str.c_str());

            char* cod = new char[256];
            strcpy(cod, cod_str.c_str());

            Produs prod = Produs(pret, cod, nume);
            add(prod);
        }
    } else throw invalid_argument("fisierul nu poate fi deschis");
    fin.close();
}

RepoFile::RepoFile(string filename) {
    this->filename = filename;
    load_from_file();
}

void RepoFile::write_to_file() {
    ofstream fout(this->filename);
    if(fout.is_open()){
        for(Produs p: this->elems)
            fout << p.getPret() << ',' << p.getCod() << ',' << p.getNume() << endl;
    } else throw invalid_argument("fisierul nu poate fi deschis");
    fout.close();
}

void RepoFile::add(const Produs &p) {
    Repo::add(p);
    this->write_to_file();
}

void RepoFile::update(const Produs &p, int pos) {
    Repo::update(p, pos);
    this->write_to_file();

}

void RepoFile::remove(int pos) {
    Repo::remove(pos);
    this->write_to_file();
}

int RepoFile::size() {
    return Repo::size();
}

Produs &RepoFile::getAt(int pos) {
    return Repo::getAt(pos);
}

