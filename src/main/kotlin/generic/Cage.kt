package generic

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))

    // val carp: Carp = cage.getFirst()
    // type mismatch error 발생
    // getFirst()는 Animal 반환 하므로
    // 바로 타입 캐스팅하는 방법은 컴파일 단계에서 발견하지 못하고 런타임 에러가 발생하는 위험이 있으므로 안된다

    // 1. Safe Type Casting과 Elvis Operator로 타입 캐스팅하기
    val carp: Carp = cage.getFirst() as? Carp
        ?: throw IllegalArgumentException()
    // 하지만 이 방법 또한 Cage에 GoldFish를 넣을 수 있고
    // 이로 인해 IllegalArgumentException이 발생할 수 있다


    // 제네릭
    val cage2 = Cage2<Carp>()
    cage2.put(Carp("잉어"))
    val carp2 = cage2.getFirst()


    // 더 까다로운 요구사항: 금붕어 케이지에 금붕어를 넣고 물고기 케이지로 옮겨라
    val goldFishCage = Cage2<GoldFish>()
    goldFishCage.put(GoldFish("금붕어"))

    val fishCage = Cage2<Fish>()

    // GoldFish가 Fish 클래스 하위 클래스인데도 옮길 수 없다는 에러가 발생
    fishCage.moveFrom(goldFishCage)
}

class Cage {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage) {
        this.animals.addAll(cage.animals)
    }

}

class Cage2<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveFrom(cage: Cage2<T>) {
        this.animals.addAll(cage.animals)
    }
}
