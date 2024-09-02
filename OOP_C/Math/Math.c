
#include"Math.h"

double sqrt_custom(double x)
{
    if (x == 0.0)
        return 0.0;

    double epsilon = 1e-7;  // 精度要求
    double guess = x / 2.0; // 初始猜测值

    while (fabs(guess * guess - x) > epsilon)
    {
        guess = (guess + x / guess) / 2.0;
    }

    return guess;
}