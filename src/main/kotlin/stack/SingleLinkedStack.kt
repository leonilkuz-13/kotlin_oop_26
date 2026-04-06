package org.example.stack

import org.example.list.SingleLinkedList

class SingleLinkedStack : SingleLinkedList(), Stack {

    override fun push(value: Int) {
        addFirst(value)
    }

    override fun pop(): Int {
        val value = get(0)
        remove(value)
        return value
    }

    override fun peek(): Int {
        return get(0)
    }

    override val isEmpty: Boolean
        get() = size == 0
}