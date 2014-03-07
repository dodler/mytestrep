#include <iostream>
#include <cmath>

#define ARG 2

using namespace std;

double func(double x, double e, int N);

int main(){
    std::cout << func(ARG, 0.0000001, 101) << " " << sin(ARG);
}

double func(double x, double e, int N){
    double n = x;
    double sum = 0.0;
    int i = 1;

    do
    {
        sum += n;
        n *= -1.0 * x * x / ((2 * i) * (2 * i + 1));
        i++;
    }
    while (fabs(n) > e && i<N);

    std::cout << i << " " ;

    return sum;
}
