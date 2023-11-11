package generic

fun main() {
    // star projection
    // 해당 타입 파라미터에 어떤 타입이 들어 있을지 모른다는 의미
    // 런타임 시 타입 파라미터는 사라지기 때문에

    // inline + reified 함수 T의 정보를 유지하기
    val numbers = listOf(1, 2f, 3.0)
    numbers.filterIsInstance<Float>() // [2f]
}

fun checkList(data: Any) {
    // data가 최소한 List인지는 확인할 수 있다
    if (data is List<*>) {
        println("List 이다")
    }
}

// 하지만 그래도 런타임 시에 T의 정보를 알고 싶을 경우
// inline + reified
// inline 함수 : 코드의 본문을 호출 지점으로 이동시켜 컴파일 되는 함수
inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}
