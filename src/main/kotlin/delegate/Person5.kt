package delegate

import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

// notNull
class Primitive {
    // primitive type은 initlate 대신에 notNull 사용
    var age: Int by notNull()
}

// observable
class Person5 {
    var age: Int by observable(20) { _, oldValue, newValue ->
        println("이전 값 : $oldValue 새로운 값 : $newValue")
    }
}

fun main() {
    val p = Person5()
    p.age = 30
}

// vetoable
// onChange 함수가 true 일 때는 변경을 실행하고, false라면 그냥 이전 값으로 둔다
class Person6 {
    var age: Int by vetoable(20) { _, _, newValue -> newValue >= 1 }
}

// Map
// person의 name과 age를 호출하면 map["name"]과 map["age"]가 호출된다
class Person7(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
}

fun main2() {
    val person = Person7(mapOf("name" to "ABC"))
    println(person.name)
    println(person.age) // 예외 발생
}
