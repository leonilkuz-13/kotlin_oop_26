package org.example.stack

import org.example.list.CustomArrayList
import org.example.list.CustomList

class ArrayListStack (private val storage: CustomList = CustomArrayList(10)) : Stack, CustomList by storage {
    override fun push(value: Int) {
        storage.add(value)
    }

    override fun pop(): Int {
        if (isEmpty) {
            throw NoSuchElementException()
        }

        val index = storage.size - 1
        return storage.get(index)
    }

    override fun peek(): Int {
        if (isEmpty) {
            throw NoSuchElementException()
        }

        return storage.get(storage.size - 1)
    }

    override val isEmpty: Boolean
        get() = storage.size == 0
}