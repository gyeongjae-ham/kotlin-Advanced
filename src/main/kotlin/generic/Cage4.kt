package generic

// Cage4는 데이터를 소비만 하고 있다
// 소비만 하고 있는 클래스를 클래스 단에서 반공변하게 만들 수 있다
class Cage4<in T> {
    private val animals: MutableList<T> = mutableListOf()

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun putAll(animals: List<T>) {
        this.animals.addAll(animals)
    }

}
