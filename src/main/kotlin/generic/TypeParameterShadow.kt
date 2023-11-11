package generic

class CageShadow<T : Animal> {
    fun <T : Animal> addAnimal(animal: T) {
    }
}

fun main() {
    // 마치 전역 변수보다 해당 범위에서 지역 변수가 우선되듯이 동작해서
    // 예상치 못한 에러가 발생한다
    val cageShadow = CageShadow<GoldFish>()
    cageShadow.addAnimal(GoldFish("금붕어"))
    // GoldFish 제네릭이지만 함수의 T가 우선되어 잉어도 들어가게 된다
    cageShadow.addAnimal(Carp("잉어"))
}

// 제네릭 클래스 상속

open class CageV1<T : Animal> {
    open fun addAnimal(animal: T) {
    }
}

// 1번째 방법
// T를 일치시켜서 전달해주는 방법
class CageV2<T : Animal> : CageV1<T>() {
    override fun addAnimal(animal: T) {
        super.addAnimal(animal)
    }
}

// 2번째 방법
// 애초에 타입을 지정하는 방법
class GoldFishCageV2 : CageV1<GoldFish>() {
    override fun addAnimal(animal: GoldFish) {
        super.addAnimal(animal)
    }
}

class PersonDto
class PersonDtoKey

// 제네릭 Type Alias
typealias PersonDtoStore = Map<PersonDtoKey, MutableList<PersonDto>>

fun handleCacheStore(store: PersonDtoStore) {
}
