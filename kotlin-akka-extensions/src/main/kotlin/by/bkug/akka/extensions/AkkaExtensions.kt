package by.bkug.akka.extensions

import kotlin.reflect.KClass

fun akkaMain(vararg clazzes: KClass<*>) {
    val array = clazzes.map { it.java.name }.toTypedArray()
    akka.Main.main(array)
}