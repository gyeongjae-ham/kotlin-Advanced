package delegate

import kotlin.reflect.KProperty

// by lazy를 사용하지 않고 비슷한 기능을 하도록 구현
// 이 패턴을 위임 패턴이라고 한다
// Person4에서 프로퍼티의 getter를 직접 호출하는 게 아니라
// 위임한 클래스에서 getter를 통해서 받아온 결과를 전달하는 방식
class Person4 {
    private val name: String by LazyInitPerperty {
        Thread.sleep(2_000L)
        "김수한무"
    }
}

class LazyInitPerperty<T>(val init: () -> T) {
    private var _value: T? = null
    private val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }

}
