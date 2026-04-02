package org.example

class SingleLinkedList : CustomList {

    private class Node(var value: Int, var next: Node?)

    private var begin: Node? = null
    private var tail: Node? = null

    override var size: Int = 0
        get() {
            var count = 0
            var current = begin
            while (current != null) {
                count++
                current = current.next
            }
            return count
        }

    override fun add(element: Int) {
        var newNode = Node(element,null)
        if (begin == null) {
            begin = newNode
            tail = newNode
            return
        }

        tail?.next = newNode
        tail = newNode
    }

    override operator fun set(index: Int, value: Int) {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is negative")
        }

        var count = 0
        var current = begin
        while (count != index && current != null) {
            current = current.next
            count++
        }

        if (current == null) {
            throw IndexOutOfBoundsException("Index $index out of bounds, size = $count") // size по-варварски тут вызывать. Count все посчитал
        }

        current.value = value
    }

    override fun addFirst(element: Int) {
        var newNode = Node(element,null)
        if (begin == null) {
            begin = newNode
            tail = newNode
        }

        newNode.next = begin
        begin = newNode
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
            throw IndexOutOfBoundsException("Index $index out of bounds, size = $count") // аналогично set
        }

        return current.value
    }

    override fun indexOf(element: Int): Int { // поиск вхождения же?
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
                } else {
                    prev.next = current.next
                }

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
                if (!hasNext()) {
                    throw NoSuchElementException()
                }

                val value = current!!.value
                current = current!!.next
                return value
            }
        }
    }

    companion object {
        fun singleLinkedListOf(vararg items: Int) =
            items.fold(SingleLinkedList()) { list, item ->
                list.also{ it.add(item) }
            }
    }
}