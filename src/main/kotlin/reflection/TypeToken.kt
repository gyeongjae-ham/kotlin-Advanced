package reflection

import generic.Animal
import generic.Cage
import generic.Carp
import generic.GoldFish
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.cast

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))
    cage.getFirst() as Carp // 위험!

    val typeSafeCage = TypeSafeCage()
    typeSafeCage.putOne(Carp::class, Carp("잉어"))
    typeSafeCage.getOne(Carp::class)

    // inline + reified 키워드를 사용한 함수로 사용한 경우
    // 이러한 패턴을 타입 안전 이종 컨테이너 패턴이라고 부른다
    // 하지만 타입 안전 이종 컨테이너는 만능이 아니다 데이터에 List<T> 같은 형태로 들어간다면
    // 컴파일 과정에서 List<*>로 변경되어 전혀 다른 데이터가 출력될 위험이 있다 따라서 이럴경우에는 타입을 기억하고있는 SuperTypeClass를 사용해야 한다
    typeSafeCage.putOne(Carp("잉어"))
    val one = typeSafeCage.getOne<Carp>()

    // SuperTypeClass
    val superTypeToken1 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken2 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken3 = object : SuperTypeToken<List<Carp>>() {}
    println(superTypeToken2.equals(superTypeToken1))
    println(superTypeToken3.equals(superTypeToken1))

    val superTypeSafeCage = SuperTypeSafeCage()
    superTypeSafeCage.putOne(superTypeToken2, listOf(GoldFish("금붕어1"), GoldFish("금붕어2")))
    // 없는 타입을 달라해서 형변환에서 null에 대한 타입 변경이 안되어 에러가 발생
//    val result = superTypeSafeCage.getOne(superTypeToken3)
    val result = superTypeSafeCage.getOne(superTypeToken2)
    println(result)
}

class TypeSafeCage {
    private val animals: MutableMap<KClass<*>, Animal> = mutableMapOf()

    fun <T : Animal> getOne(type: KClass<T>): T {
        return type.cast(animals[type])
    }

    fun <T : Animal> putOne(type: KClass<T>, animal: T) {
        animals[type] = type.cast(animal)
    }

    inline fun <reified T : Animal> getOne(): T {
        return this.getOne(T::class)
    }

    inline fun <reified T : Animal> putOne(animal: T) {
        this.putOne(T::class, animal)
    }
}

class SuperTypeSafeCage {
    private val animals: MutableMap<SuperTypeToken<*>, Any> = mutableMapOf()

    fun <T : Any> getOne(token: SuperTypeToken<T>): T {
        return this.animals[token] as T
    }

    fun <T : Any> putOne(token: SuperTypeToken<T>, animal: T) {
        animals[token] = animal
    }
}

// SuperTypeToken을 구현한 클래스가 인스턴스화 되자마자
// T 정보를 내부 변수에 저장해버린다
// T <- List<Int>
abstract class SuperTypeToken<T> {
    val type: KType = this::class.supertypes[0].arguments[0].type!!

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SuperTypeToken<*>) return false

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}
