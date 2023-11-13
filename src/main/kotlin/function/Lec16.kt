package function

/**
 * SAM(Single Abstract Method)
 * Java에서는 SAM interface를 람다로 인스턴스화 할 수 있다
 * Kotlin에서는 SAM interface를 람다로 인스턴스화 할 수 없다
 * 만약 변수에 넣는게 아니라, 파라미터에 넣는거라면 바로 람다식을 쓸 수 있다
 */

// Kotlin에서 SAM interface를 익명 함수로 인스턴스화 한 예제
fun main() {
    val filter: StringFilter = object : StringFilter {
        override fun predicate(str: String?): Boolean {
            return str?.startsWith("A") ?: false
        }
    }
}

// Koltin에서 바로 SAM interface를 람다로 인스턴스화는 불가능하고,
// 앞에 어떤 SAM interface인지 명시하면 가능하다
fun main3() {
    // SAM 생성자
    val filter = StringFilter { s -> s.startsWith("A") }

    // SAM interface를 파라미터에서 바로 람다식으로 쓰기
    consumeFilter { s -> s.startsWith("A") }
}

fun consumeFilter(filter: StringFilter) {}
