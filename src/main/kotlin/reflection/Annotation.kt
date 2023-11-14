package reflection

/**
 * 1. 어노테이션을 만드는 방법
 * 2. 어노테이션을 사용하는 방법
 * 3. Repeatable 어노테이션
 *
 * @Retention: 어노테이션이 저장되고 유지되는 방식을 제어
 *  - SOURCE: 컴파일 때만 존재
 *  - BINARY: 런타임 때도 있지만, 리플렉션을 쓸 수 없다
 *  - RUNTIME(기본값): 런타임 때 존재하고, 리플렉션을 쓸 수 있다
 *
 *  @Target: 어노테이션을 어디에 붙일지 결정
 */
annotation class Shape(
    val texts: Array<String>
)

class Annotation {
}

/**
 * Repeatable 어노테이션
 * 한 요소에 반복적으로 붙일 수 있는 어노테이션
 */
@Repeatable
annotation class Shape2()
