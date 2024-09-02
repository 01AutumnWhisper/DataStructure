## 基本数据类型
```
bool, 
uint, uint8, uint16, uint32, uint64, 
int, int8, int16, int32, int64, 
float32, float64
```
1. `bool类型是逻辑类型，只有两个值：true和false。不能直接与整数类型进行转换或运算（例如C/C++中true为1，false为0）`
2. `uint,uint8,uint16,uint32,uint64.前面的u表示unsigned，即无符号的，意味着数值不允许为负。后面的数字表示位数，范围从8位到64位。`
例如：`uint8范围0~255。`
3. `int,int8,int16,int32,int64.表示signed，即有符号的，意味着数值可以为负。后面的数字表示位数，范围从8位到64位。`
4. `float32,float64.表示浮点数，可以表示小数。后面的数字表示位数，32位和64位。`

## 常量与变量
### 变量
1. 先说明变量
```go
func main(){
    // 声明一个32位有符号整型变量。
    var a int32
    // a的值默认为0，因为我们没有初始化。
    fmt.Println(a)
}
//打印结果
/*
 0
 */
```
首先我们理解第一句话`var a int32`,var是默认写法，a是变量名，int32是变量类型。
我们可以给定一个初始值，如`var a int32 = 10`,这时候打印结果就为10了。
前面说明未初始化默认给0
```go
func main(){
    var a int32
    var b bool
    var c string
    var d float64
    fmt.Println("默认:a=",a)
    fmt.Println("默认:b=",b)
    fmt.Println("默认:c=",c)
    fmt.Println("默认:d=",d)
}
//打印结果
/*
默认:a= 0
默认:b= false
默认:c=
默认:d= 0
 */
```
结论：数值（int,uint,float）默认值是0，布尔值默认值是false，字符串默认值为空字符串`""`.

### 声明多种相同类型的变量。
```go
func main(){
    var a int32
    var a1 int32
    var a2 int32
    var a3 int32
    var a4 int32
    fmt.Println(a,a1,a2,a3,a4)
}
/*打印结果
0 0 0 0 0
 */
```
可以简化成：
```go
func main(){
    var a,a1,a2,a3,a4 int32
    fmt.Println(a,a1,a2,a3,a4)
}
/*打印结果
0 0 0 0 0
 */
```
`结论：声明多个相同类型的变量，可以简化成上述样子。`

### 编译器自动推导类型
Go语言编译器支持类型推导，它可以根据变量的赋值来推导变量的类型。
```go
func main(){
    var str="hello world"
    fmt.Println(str)
}
```
####  :=运算符
下面介绍一种操作符
`:=` 是 Go 语言中的简短变量声明运算符。
Go 编译器根据右边表达式的值自动推断变量的类型。
```go
func main(){
    str :="hello world"
    fmt.Println(str)
}
/*
hello world
 */
```
`注意事项`：
1. `:=` 运算符只能用在函数体内，不能用在全局变量或包变量的声明中。
2. :=不是赋值运算符，而是简化变量声明和初始化的写法。
```go
//先解释第二条。
func main(){
    var str string;
    str :="hello world"
    fmt.Println(str)
}
/*
error: no new variables on left side of :=
 */
```
这是因为
`str :="hello world"`等价于`var str string = "hello world"`,
而变量str已经声明过了，不能再声明同名变量了。

比如我们在全局声明`str:="Hi"`，会出现`syntax error: non-declaration statement outside function body`,意思是`:=`必须在函数体内声明，上面的例子都是在main函数内部声明。不能用来声明全局变量和包变量（后续解释）。

### var集中声明
var结合圆括号可以集中声明有联系的变量，可以提高代码可读性，结构更紧凑。
```go
var (
        age    int    // 声明一个int类型的变量age
        name   string // 声明一个string类型的变量name
        height float64 // 声明一个float64类型的变量height
    )
```

### 变量总结
1. 了解一个变量如何声明，初始化。
2. 多个相同类型变量放在一起简化声明。
3. 了解 := 运算符的用法。
4. 了解 var 声明变量的圆括号用法。

## 常量
### const关键字
>如果你熟悉任何一门编程语言，那么const或者其它类似如（Java中的final）关键字一定不会陌生。

变量声明前面加上const关键字，就意味着其是一个常量。
常量意味着不可修改，请看下面例子。
```go
func main(){
    const a = 10
    fmt.Println(a)
    a = 20
    fmt.Println(a)
}
/*
 cannot assign to a (neither addressable nor a map index expression)
*/
```

补充：
上面的` const a = 10`采用的隐式声明，也可以显式地声明`const a int32 = 10`.
另外`const a:=10`,对常量声明使用`:=`是错误的写法。
常量可以和圆括号结合:
```go
const (
    Red = 1
    Black= 0
)
```

## 分支语法
### if语句
```go
func main() {
    x := 10

    if x > 5 {
        fmt.Println("x is greater than 5")
    }
}
```
如果条件表达式的值为true,则执行if语句块中的代码。
反之判断一次后，跳过代码块。

### if-else语句
```go
func main() {
    x := 4

    if x > 5 {
        fmt.Println("x is greater than 5")
    } else {
        fmt.Println("x is less than or equal to 5")
    }
}
```
如果条件表达式的值为true,则执行if语句块中的代码。
反之执行else语句块中的代码。
`二者择其一`

### else-if 语句
```go
func main(){
    a := 5
    if a>5{
        fmt.Println("a is greater than 5")
    }
    else if a<5{
        fmt.Println("a is less than 5")
    }
    else{
        fmt.Println("a is equal to 5")
    }
}
```
从上到下依次判断，选择其中满足的分支执行，如果都不满足则执行else语句。
可以不写else语句，这样都不满足相当于判断完跳过所有的判断代码块了。

### switch语句
```go
func main() {
    day := "Monday"

    switch day {
    case "Monday":
        fmt.Println("It's Monday")
    case "Tuesday":
        fmt.Println("It's Tuesday")
    default:
        fmt.Println("It's another day")
    }
```
说明：switch后面跟任意类型的变量，case后面跟多个值，每个值后面跟一组代码块，但case后变量或值类型必须与前面switch后跟着的变量类型一致匹配。
default相当于if-else语句的else部分，它可以省略，否则前面case都不满足时执行。---default是可选项。
`switch后面语句可以省略变量，来实现if-elseif-else的功能。`
```go
func main(){
    day := "Monday"
    //省略了switche后面的变量day
    switch {
    case day == "Monday":
        fmt.Println("It's Monday")
    case day == "Tuesday":
        fmt.Println("It's Tuesday")
    default:
        fmt.Println("It's another day")
    }
}
```
如果你了解（C/Java）---至少我了解的这两门语言是这样,其switch语法会有一个`fall-through`现象，即case语句执行完后，不会跳出switch语句，而是继续执行下一个case语句。Go语言中，switch语句不会自动`fall-through`，每个case语句后面需要加上`break`语句，否则会继续执行下一个case语句。
相当于：
```go
func main(){
    day := "Monday"
    switch day {
    case "Monday":
        fmt.Println("It's Monday")
        break
    case "Tuesday":
        fmt.Println("It's Tuesday")
        break
    default:
        fmt.Println("It's another day")
        break
    }
}
```
可以直接去掉`break`语句，让代码更加简洁。

## 循环语句
Go语言提供了三种基本的循环结构：for循环、range循环和goto循环。
很遗憾的是没有见到大多编程语言提供的`while`循环，`do while`循环，不过好消息是我们可以用for循环模拟while循环
### for语句
for语句的三种写法
```go
func main(){
    //打印0~9
    for i:=0;i<10;i++{
        fmt.Print(i," ")
    }
}
```
for语句的三个部分：初始化、条件、迭代。
初始化：声明变量、初始化变量的值。
条件：  判断是否满足循环条件，只有为true时才执行循环体。
迭代：  每次循环结束后，执行的语句。



```go
func main(){
    i:=0
    for i<10{
        fmt.Println("Hello, World!")
        i++
    }
}
```
for语句后面只跟着一个条件，不过我们可以外部变量，内部调整来控制循环。
使用方式和`while`循环一样。


```go
func main(){
    for {
        fmt.Println("Hello, World!")
    }
}
```
死循环，除非内部达到一定条件，采用break语句打破循环。

### range 循环
range循环，不过在说明之前，先介绍一下数组。
Go语言提供数组这种内存连续的数据结构。

