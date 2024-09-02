package main

import "fmt"

var (  // 这种因式分解关键字的写法一般用于声明全局变量
    a int
    b bool
)

const (
    Unknown = 0
    Female = 1
    Male = 2
)

//指针
func main(){
    var a int = 20
    fmt.Printf("&a : = %x\n", &a)
}

// func main(){
//     // card := [5]int32{1,2,3,4,5}
//     // for i := 0; i < len(card); i++ {
//     //     fmt.Print(card[i])
//     // }
//     numbers := [...]int32{1,2,3,4,5,6,7,8}
//     for i:=0 ;i<len(numbers);i++{
//         fmt.Print(numbers[i])
//     }
//     var salary int32 = numbers[5]
//     fmt.Println(salary)
// }
// func main() {
//    /* 这是我的第一个简单的程序 */
//    fmt.Println("Hello, World!")
//    fmt.Println("Hi")
//    fmt.Println("我想加入"+"Google")

//    //
//    var x int
//    var y float64
//    x++
//    fmt.Println(x,y)
//    var a string = "Google"
//    fmt.Println(a)

//    const LENGTH = 10
//    const WIDTH = 5

// }
// package main

// import (
//     "fmt"
//     "time"
// )

// func main() {

//     c1 := make(chan string) // Create a channel to send and receive strings
//     c2 := make(chan string) // Create another channel to send and receive strings

//     go func() { // Create a goroutine to send a message after a delay
//         time.Sleep(1 * time.Second)
//         c1 <- "one"
//     }()
//     go func() { // Create another goroutine to send a message after a delay
//         time.Sleep(2 * time.Second)
//         c2 <- "two"
//     }()

//     for i := 0; i < 2; i++ { // Loop until both messages have been received
//         select {
//         case msg1 := <-c1: // Receive message from c1 and print it
//             fmt.Println("received", msg1)
//         case msg2 := <-c2: // Receive message from c2 and print it
//             fmt.Println("received", msg2)
//         }
//     }
// }
//     go func() {
//         time.Sleep(1 * time.Second)
//         c1 <- "one"
//     }()
//     go func() {
//         time.Sleep(2 * time.Second)
//         c2 <- "two"
//     }()

//     for i := 0; i < 2; i++ {
//         select {
//         case msg1 := <-c1:
//             fmt.Println("received", msg1)
//         case msg2 := <-c2:
//             fmt.Println("received", msg2)
//         }
//     }
// }

//单行注释
/*
   多行注释
*/