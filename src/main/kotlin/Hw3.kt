package org.example

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun divideOrZero(a: Int, b: Int): Int {
    return when {
        b == 0 -> 0
        else -> a / b
    }
}

// мне понравилось на уровне интерфейса задавать ковариантность и контравариантность.

interface Supplier<out T> {
    fun get(): T
}

interface Consumer<in T> {
    fun consume(item: T)
}

class StringSupplier : Supplier<String> {
    override fun get(): String {
        return "hello!!"
    }
}

class AnyConsumer: Consumer<Any> {
    override fun consume(item: Any) {
        println("consume $item")
    }
}

var initCount = 0
var initCount3 = 0

class DelegateOwner {
    val item by lazy2 {
        initCount++
        10 + 2
    }
    val item2 by lazy2 {
        "2" + "10"
    }
    val item3 by lazy2 {
        initCount3++
        null
    }
}

class lazy2<T>(private val initializer: () -> T) {

    private var isInitialized = false
    private var cached: Any? = null

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (!isInitialized) {
            cached = initializer()
            isInitialized = true
        }

        return cached as T
    }
}

fun main() {
    if (divideOrZero(10, 2) != 5) {
        error("Incorrect division")
    }
    if (divideOrZero(10, 0) != 0) {
        error("Incorrect division for zero")
    }

    if (divideOrZero(-10, 8) != -1) {
        error("Incorrect division for positive")
    }

    val supplier: Supplier<String> = StringSupplier()
    val consumer: Consumer<Any> = AnyConsumer()

    val item: String = supplier.get()
    println("Got item from supplier: $item")

    consumer.consume(item)
    consumer.consume(39)

    val owner = DelegateOwner()
    if (initCount != 0) {
        error("Not lazy init")
    }
    val res = owner.item
    if (res != 12) {
        error("Not correct res")
    }
    val res2 = owner.item
    if (res2 != 12) {
        error("Not correct res2")
    }
    if (initCount > 1) {
        error("Too much inits")
    }

    val res3 = owner.item3
    if (res3 != null) {
        error("Not correct res3")
    }

    val res32 = owner.item3
    if (res32 != null) {
        error("Not correct res32")
    }
    if (initCount3 > 1) {
        error("Too much inits")
    }
}