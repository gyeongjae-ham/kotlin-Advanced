package reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.cast
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer

@Target(AnnotationTarget.CLASS)
annotation class Executable

@Executable
class Reflection {
    fun a() {
        println("A입니다")
    }

    fun b() {
        println("B입니다")
    }
}

fun executeAll(obj: Any) {
    val kClass = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }

    callableFunctions.forEach { function ->
        function.call(kClass.createInstance())
    }
}

fun main() {
//    executeAll(Reflection())

    val kClass1: KClass<Reflection> = Reflection::class

    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class

//    val kClass3: KClass<out Any> = Class.forName("reflect.Reflection").kotlin
    kClass1.java // Class<Reflection>
    kClass1.java.kotlin // KClass<Reflection>

    /**
     * 자바 리플렉션과 코틀린 리플렉션의 차이
     * 1. Class.forName -> Java
     * 2. isInner ->  Kotlin
     * 등 다른 점들이 있다
     */

    // KType, 타입을 표현
    val kType: KType = GoldFish::class.createType()

    val goldFish = GoldFish("금붕이")
    goldFish::class.members.first { it.name == "print" }.call(goldFish)
}

class GoldFish(private val name: String) {
    fun print() {
        println("금붕어 이름은 ${name}입니다.")
    }
}

// Cast 함수
fun castToGoldFish(obj: Any): GoldFish {
    return GoldFish::class.cast(obj)
}
