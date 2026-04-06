package org.example.list

open class SingleLinkedList : CustomList {

    private class Node(var value: Int, var next: Node?)

    private var begin: Node? = null
    private var tail: Node? = null

    override var size = 0

    override fun add(element: Int) {
        val newNode = Node(element, null)
        val currentTail = tail
        if (currentTail == null) {
            begin = newNode
            tail = newNode
        } else {
            currentTail.next = newNode
            tail = newNode
        }
        size++
    }

    override operator fun set(index: Int, value: Int) {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is negative")
        }

        if (index >= size) {
            throw IndexOutOfBoundsException("Index $index is greater than size: $size")
        }

        var count = 0
        var current = begin
        while (count != index) {
            current = current?.next
            count++
        }

        current?.value = value
    }

    override fun addFirst(element: Int) {
        var newNode = Node(element, null)
        val currentBegin = begin
        if (currentBegin == null) {
            begin = newNode
            tail = newNode
        } else {
            newNode.next = currentBegin
            begin = newNode
        }
        size++
    }

    override operator fun get(index: Int): Int {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is negative")
        }

        var current = begin
        var count = 0
        while (count < index && current != null) {
            count++
            current = current.next
        }

        if (current == null) {
            throw IndexOutOfBoundsException("Index $index out of bounds, size = $size")
        }

        return current.value
    }

    override fun indexOf(element: Int): Int {
        var current = begin
        var index = 0

        while (current != null) {
            if (current.value == element) {
                return index
            }

            current = current.next
            index++
        }

        return -1
    }

    override fun remove(element: Int): Boolean {
        var current = begin
        var prev: Node? = null

        while (current != null) {
            if (current.value == element) {
                if (prev == null) {
                    begin = current.next
                    if (begin == null) tail = null
                } else {
                    prev.next = current.next
                    if (current.next == null) {
                        tail = prev
                    }
                }
                size--
                return true
            }
            prev = current
            current = current.next
        }
        return false
    }

    override fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {

            private var current = begin

            override fun hasNext(): Boolean {
                return current != null
            }

            override fun next(): Int {
                val node = current ?: throw NoSuchElementException() // такой snapshot придуман тут
                val value = node.value
                current = node.next
                return value
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) = SingleLinkedList().apply {
            for (item in items) {
                add(item)
            }
        }
    }
}