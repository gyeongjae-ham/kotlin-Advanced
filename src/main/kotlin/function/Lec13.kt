package function

class Lec13 {
}

fun main() {
    // 고차함수 사용법
    // 1. 람다식(마지막 파라미터의 람다식은 밖으로 뺄 수 있다
    // return을 사용할 수 없다
    compute(5, 3) { a, b -> a + b }

    // 2. 익명함수(타입이 추론이 가능할 경우 파라미터의 타입을 생략할 수 있다
    // 함수 바디를 축약할 수 있다
    // return을 사용할 수 있다
    compute(5, 3, fun(a, b) = a + b)
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun calculate(num1: Int, num2: Int, oper: Char): Int {
    return when (oper) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        '/' -> {
            if (num2 == 0) {
                throw IllegalArgumentException("0으로 나눌 수 없습니다!")
            } else {
                num1 / num2
            }
        }

        else -> throw IllegalArgumentException("들어올 수 없는 연산자($oper)입니다!")
    }
}

fun calculate2(num1: Int, num2: Int, oper: Operator) = oper.calcFun(num1, num2)

enum class Operator(
    private val oper: Char,
    val calcFun: (Int, Int) -> Int,
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('*', { a, b -> a * b }),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            throw IllegalArgumentException("0으로 나눌 수 없습니다!")
        } else {
            a / b
        }
    }),

}
