//
// Created by Darius Stoica on 5/14/2023.
//
#include "repository.h"
#include <sstream>
#include <fstream>
#include <string.h>
#include <vector>

#ifndef LAB10_REPOSITORYFILE_H
#define LAB10_REPOSITORYFILE_H

class RepoFile : public Repo {
private:
    string filename;
public:
    RepoFile() : Repo() {
        this->filename = filename;
    };

    explicit RepoFile(string filename) : Repo() {
        this->filename = filename;
        load_from_file();
    };

    ~RepoFile() = default;

    void load_from_file() {
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

    void write_to_file() {
        ofstream fout(this->filename);
        if(fout.is_open()){
            for(Produs p: this->elems)
                fout << p.getPret() << ',' << p.getCod() << ',' << p.getNume() << endl;
        } else throw invalid_argument("fisierul nu poate fi deschis");
        fout.close();
    };
};
#endif //LAB10_REPOSITORYFILE_H
