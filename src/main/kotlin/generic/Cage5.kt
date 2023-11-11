package generic

// 제네릭 제약과 제네릭 함수
fun main() {
    // 제약 조건으로 지정한 타입과 하위 타입이 아닌 다른 타입은 들어오지 못한다
    // Cage5<Int>

    val cage = Cage51(mutableListOf(Eagle(), Sparrow()))
    cage.printAfterSorting()

    // null이 들어올 수 없음
    // Null<Bird?>()
}

// 해당 타입에 null이 못 들어오도록 Any를 선언할 수 있다
class Null<T : Any> {}

abstract class Bird(
    name: String,
    private val size: Int,
) : Animal(name), Comparable<Bird> {

    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }

}

class Sparrow : Bird("참새", 100)
class Eagle : Bird("독수리", 500)

// Cage5에 Animal 타입만 들어오도록 제약
class Cage5<T : Animal>(
    private val animals: MutableList<T> = mutableListOf()
) {

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage5<T>) {
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage5<T>) {
        otherCage.animals.addAll(this.animals)
    }

}

// 여러 조건의 제약을 걸 경우
class Cage51<T>(
    private val animals: MutableList<T> = mutableListOf()
) where T : Animal, T : Comparable<T> {

    fun printAfterSorting() {
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(otherCage: Cage51<T>) {
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: Cage51<T>) {
        otherCage.animals.addAll(this.animals)
    }

}

// 제네릭 함수를 이용하면 효율적인 프로그래밍이 가능하다
// 제네릭을 활용한 확장함수 상황에 따라 여러 타입의 함수로 만들지 않아도 된다
fun <T> List<T>.hasIntersect(other: List<T>): Boolean {
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}
