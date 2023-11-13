package function

class Lec14 {
}

fun main() {
    compute2(5, 3) { num1, num2 -> num1 * num2 }

    var num = 5
    num += 1
    val plusOne = { num += 1 }
}

fun compute2(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

// Java로 decompile 해보면 고차함수는 FunctionN의 형태로 변한다
/**
 * 1. 고차함수를 사용하게 되면 FunctionN 클래스가 만들어지고 인스턴스화 되어야 하므로 오버헤드가 발생할 수 있다
 * 2. 함수에서 변수를 포획할 경우(Closure), 해당 변수를 Ref라는 객체로 감싸야 한다. 때문에 오버헤드가 발생할 수 있다
 */
