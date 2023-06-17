//
// Created by Darius on 5/21/2023.
//
#include <string.h>
#include "exception.h"
#include "validator.h"

void Validator::valid_pret(float pret) {
    if(pret < 0)
        throw Exception("Pret invalida\n");
}

void Validator::valid_cod(char *cod) {
    if(cod[0] < 48 || cod[0] > 57)
        throw Exception("Primul termen al codului trebuie sa fie o cifra\n");
}

void Validator::valid_nume(char *nume) {
    for(int i = 0; i < strlen(nume); i++)
        if(nume[i] < 'a' || nume[i] > 'z')
            throw Exception("Numele nu trebuie sa contina decat litere mici ale algabetului englez\n");
}

void Validator::valid_bancnote(int bancnote) {
    if(bancnote < 0)
        throw Exception("Introduceti alte bancnote\n");
}

void Validator::valid_pos(int pos, int size) {
    if(pos < 0 || pos < size)
        throw Exception("Pozitie invalida\n");
}

Validator::~Validator() = default;

Validator::Validator() = default;

