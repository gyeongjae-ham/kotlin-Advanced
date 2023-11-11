package generic

fun main() {

    val fishCage = Cage3<Fish>()
    val animalCage: Cage3<Animal> = fishCage

}

// Cage3는 데이터를 생산만 하고 있다
// 생산만 하고 있는 클래스를 클래스 단에서 공변하게 만들 수 있다
class Cage3<out T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return this.animals.first()
    }

//    fun getAll(): List<T> {
//        return this.animals
//    }

}

// out 선언지점 변성 활용 예시
// 생산만 하는 클래스에서 다른 타입의 클래스를 받아서 처리해야 하는 경우
// @UnsafeVariance를 사용해서 허용할 수 있다
public interface List<out E> : Collection<E> {
    // Query Operations

    override val size: Int
    override fun isEmpty(): Boolean

    // 어차피 이 메서드는 데이터를 저장하는 것이 아니라 그냥 비교만 하는 것이라서
    // @UnsafeVariance를 사용해 선언지점 변성에 사용한 E를 함수 파라미터에서 사용했다
    override fun contains(element: @UnsafeVariance E): Boolean
    override fun iterator(): Iterator<E>
}
