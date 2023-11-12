package delegate

fun main() {
    val fruits = listOf(
        MyFruit("사과", 1000L),
        MyFruit("바나나", 3000L),
    )

    // filter만 쓰면, 2,000,000 -> 모두 필터링
    // Sequence를 사용하면 최종연산까지 가지만 정해진 연산들이 모두 될때까지 기다린다
    // 2,000,000개를 모두 돌지 않고 정해진 연산이 충족되면 그대로 종료(더 효율적)
    // 데이터의 양이 많다면 Sequence가 압도적으로 빠르다
    // 데이터의 양이 적다면 iterable이 좀 더 빠르다
    val avg = fruits.asSequence()
        .filter { it.name == "사과" }
        .map { it.price }
        .take(10_000)
        .average()
}

data class MyFruit(
    val name: String,
    val price: Long,
)
