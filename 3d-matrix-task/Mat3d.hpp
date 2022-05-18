#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <vector>
#include <stdexcept>
using namespace std;

template<class t> class Mat3d{
    std::vector<t> mat;
    int d1 = 0;
    int d2 = 0;
    int d3 = 0;
public:
    Mat3d(int d1, int d2, int d3);
    t get(int i, int j, int k);
    void assign(int i, int j, int k, t ele);
    Mat3d (vector<vector<vector<t>>> in3d);
};

#endif
