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
    // fishCage.moveFrom(goldFishCage)

    // Cage<Fish>에 Cage<GoldFish>를 넣으려고 했기 때문이다!
    // 상속관계에서 Fish는 GoldFish의 상위 클래스가 맞지만,
    // Cage<Fish>와 Cage<GoldFish>는 아무런 관계가 아니다 -> 이걸 Cage는 무공변(불공변)하다라고 한다

    // 제네릭 타입을 가진 클래스들간의 관계를 만들어 주기
    // 타입 앞에 out 키워드 작성해 주기
    fishCage.moveFrom(goldFishCage)
    val fish: Fish = fishCage.getFirst()
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

    fun moveFrom(otherCage: Cage2<out T>) {
        // out 키워드로 변성을 준 클래스는 get만 가능해진다
        otherCage.getFirst() // 가능
        // otherCage.put(Carp("잉어")) 불가능
        // otherCage는 생산자 역할만 가능하다
        this.animals.addAll(otherCage.animals)
    }

    // 반대의 경우에는 반공변을 해줘야 한다
    // 상위 클래스 타입의 제네릭에 하위 클래스 타입 제네릭을 넣어주기 위해서는 상위 클래스 타입의 제네릭이 오히려 하위 계층이 되어야 한다
    // 따라서 공변의 반대로 해야 하므로 반공변이라 한다
    fun moveTo(otherCage: Cage2<in T>) {
        // in 키워드가 붙은 otherCage는 데이터를 받을 수만 있다
        // otherCage는 소비자 역할만 가능하다
        otherCage.animals.addAll(this.animals)
    }

}
