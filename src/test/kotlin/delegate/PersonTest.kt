package delegate

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {

    // 테스틍서 인스턴스화를 한번만 하고, 변수를 초기화 하고 싶지 않은 경우
    // 또한, 인스턴스화를 할 때 초기값을 넣어주고 싶지 않을 경우

    @Test
    fun isKimTest() {
        // given
        val person = Person("김수한무")

        // when & then
        assertThat(person.isKim).isTrue
    }

    @Test
    fun maskingNameTest() {
        // given
        val person = Person("최태현")

        // when & then
        assertThat(person.maskingName).isEqualTo("최**")
    }

}
