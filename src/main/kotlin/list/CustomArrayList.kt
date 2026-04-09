package org.example.list

class CustomArrayList(startSize: Int): CustomList {
    private var inner = IntArray(startSize)
    private var lastIndex = 0

    override val size: Int
        get() = lastIndex

    private fun checkIndex(index: Int): Unit {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is negative")
        }

        if (index >= lastIndex) {
            throw IndexOutOfBoundsException("Index $index is greater than size = $size")
        }
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return inner[index]
    }

    override fun set(index: Int, value: Int) {
        checkIndex(index)
        inner[index] = value
    }

    override fun add(element: Int) {
        if (lastIndex == inner.size) {
            resize((inner.size + 1) * 2)
        }

        inner[lastIndex++] = element
    }

    override fun addFirst(element: Int) {
        if (size == inner.size) {
            resize((inner.size + 1) * 2)
        }

        for (i in lastIndex downTo 1) {
            inner[i] = inner[i - 1]
        }
        inner[0] = element
        lastIndex++
    }

    private fun resize(newSize: Int) {
        inner = inner.copyOf(newSize)
    }

    override fun remove(element: Int): Boolean {
        if (size == 0) {
            return false
        }

        for (i in 0 until size) {
            if (inner[i] == element) {
                for (j in i until size - 1) {
                    inner[j] = inner[j + 1]
                }
                lastIndex--
                return true
            }
        }
        return false
    }

    override fun indexOf(element: Int): Int {
        for (i in 0 until size) {
            if (inner[i] == element) {
                return i
            }
        }
        return -1
    }

    override fun iterator(): Iterator<Int> {
        return inner.take(lastIndex).iterator()
    }

    companion object {
        fun customArrayListOf(vararg items: Int) =
            items.fold(CustomArrayList(items.size)) { list, item ->
                list.also { it.add(item) }
            }
    }
}