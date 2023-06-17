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
    void load_from_file();
    void write_to_file();
public:
    RepoFile();
    explicit RepoFile(string filename);
    ~RepoFile() = default;
    void add(const Produs& p);
    void update(const Produs& p, int pos);
    void remove(int pos);
    int size();
    Produs& getAt(int pos);
};
#endif //LAB10_REPOSITORYFILE_H
