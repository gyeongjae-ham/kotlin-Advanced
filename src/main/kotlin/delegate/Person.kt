package delegate

class Person {
    // 인스턴스화 시점과 초기화 시점을 분리하기 위해 lateinit 사용
    // 하지만 primitive type에는 사용할 수 없음
    // Int? -> Integer
    // Int -> int
    lateinit var name: String

    val isKim: Boolean
        get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1 until name.length).joinToString("") { "*" }

}

// 인스턴스 시점과 초기화 시점을 분리하고, 사용되는 시점에 딱 한번만 초기화 되도록 하려면
// backing property를 사용해야 한다
class Person2 {
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2_000L)
                this._name = "김수한무"
            }
            return this._name!!
        }
}

// backing property를 사용하는 방식을 권장하나, 너무 긴 코드를 요구하기 때문에
// by lazy로 사용할 수 있다
class Person3 {
    // 해당 프로퍼티가 get 요청을 받을 때 최초 한번만 실행된다
    // Thread safe 하다
    val name: String by lazy {
        Thread.sleep(2_000L)
        "김수한무"
    }
}
