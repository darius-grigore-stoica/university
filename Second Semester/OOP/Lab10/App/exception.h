//
// Created by Darius on 5/16/2023.
//

#ifndef LAB10_EXCEPTION_H
#define LAB10_EXCEPTION_H
class Exception{
private:
    const char* message;
public:
    Exception(const char* m): message(m){};
    const char* getMessage(){ return message;}
};
#endif //LAB10_EXCEPTION_H
