#include "Mat3d.hpp"

template<class t>
Mat3d<t>::Mat3d(int _d1, int _d2, int _d3) {
    if (_d1 <= 0 || _d2 <= 0 || _d3 <= 0) {
        throw invalid_argument("invalid dimensions");
        // return;
    } else {
        this->d1 = _d1, this->d2 = _d2, this->d3 = _d3;
        this->mat.resize(this->d1 * this->d2 * this->d3);
    }
}

template<class t>
Mat3d<t>::Mat3d (vector<vector<vector<t>>> in3d) {
    try {
        if (in3d.size() != 0 || in3d[0].size() != 0 || in3d[0][0].size() != 0) {
            this->d1 = in3d.size();
            this->d2 = in3d[0].size();
            this->d3 = in3d[0][0].size();
            for (int i = 0; i < this->d1; i++) {
                for (int j = 0; j < this->d2; j++) {
                    for (int k = 0; k < this->d3; k++) {
                        assign(i, j, k, in3d[i][j][k]);
                    }
                }
            }
        } else {
            throw out_of_range("Inavlid input");
        }
    } catch (out_of_range &e) {
        cout << e.what() << endl;

    }
}

template<class t>
t Mat3d<t>::get(int i, int j, int k) {
    if (i < this->d1 && j < this->d2 && k < this->d3) {
        return this->mat[i * this->d1 * this->d2 + j * this->d2 + this->d3];
    } else {
        throw out_of_range("indexes out of range");
    }
}

template<class t>
void Mat3d<t>::assign(int i, int j, int k, t ele) {
    if (i < this->d1 && j < this->d2 && k < this->d3) {
        this->mat[i * this->d1 * this->d2 + j * this->d2 + this->d3] = ele;
    } else {
        throw out_of_range("indexes out of range");
    }

}



int main() {
    Mat3d<int> mat(3, 3, 3);
    vector<vector<vector<int>>> matv(3, vector<vector<int>>(3, vector<int>(3)));
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                mat.assign(i, j, k, i + j + k);
                matv[i][j][k] = i+j+k;
            }
        }
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                cout << mat.get(i, j, k) << "==" << matv[i][j][k];
            }
            cout << endl;
        }
        cout << "," << endl;
    }

}
