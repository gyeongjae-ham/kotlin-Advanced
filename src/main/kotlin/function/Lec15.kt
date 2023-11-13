package function

class Lec15 {
}

inline fun add(num1: Int, num2: Int): Int {
    return num1 + num2
}

// 외부와 상관없이 내부에서 람다를 inline 시키고 싶지 않은 경우
inline fun repeat(
    times: Int,
    noinline exec: () -> Unit
) {
    for (i in 1..times) {
        exec()
    }
}

// inline에서 return을 못쓰게 하기 위해서
inline fun iterate2(
    numbers: List<Int>,
    crossinline exec: (Int) -> Unit,
) {
    for (num in numbers) {
        exec(num)
    }
}

fun main() {
    val num1 = 1
    val num2 = 2
    val result = add(num1, num2)
}

// 위 코드를 decompile해보면
// public static final void main() {
//     int num1 = 1;
//     int num2 = 2;
//     아래 코드가 inline 처리된 부분
//     int var10000 = num1 + num2;
//}

fun main2() {
    repeat(2) { println("Hello World") }
}

// 위 코드를 decompile해보면
// public static final void main() {
//     int i$iv = 1;
//     while(true) {
//         System.out.println("Hello World");
//         if (i$iv == 2) {
//             return;
//         }
//     ++i$iv;
//   }
//}

// inline 함수는 본인만 인라이닝 되는게 아니라 알 수 있는 함수 파라미터도 인라이닝 시키고
// non-local return 역시 쓸 수 있게 해준다
// 따라서 main 함수가 종료되는 등 원하지 않은 방식으로 로직이 동작할 수도 있다
