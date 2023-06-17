//
// Created by Darius on 5/21/2023.
//

#ifndef LAB10_VALIDATOR_H
#define LAB10_VALIDATOR_H
class Validator{
public:
    Validator();
    ~Validator();
    void valid_pret(float pret);
    void valid_cod(char* cod);
    void valid_nume(char* nume);
    void valid_bancnote(int bancnote);
    void valid_pos(int pos, int size);
};
#endif //LAB10_VALIDATOR_H
