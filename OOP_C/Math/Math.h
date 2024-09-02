
#include<math.h>
//结构体加全局变量来模拟一个静态方法
const double PI = 3.14159265358979323846;
const double E = 2.71828182845904523536;
typedef struct
{
    double PI;
    double E;
    //求平方根
    double (*sqrt)(double);
    //求正弦值
    double (*sin)(double);
    //求余弦值
    double (*cos)(double);

} MathClass;

MathClass Math = {PI,E,sqrt};