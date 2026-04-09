package org.example

// import org.example.list.CustomArrayList // зачем, если интерфейс один ?
import org.example.list.CustomList

object ListPrinter {
    fun printList(list: CustomList) {
        val substring = StringBuilder("[")

        val iterator = list.iterator()
        while (iterator.hasNext()) {
            substring.append(iterator.next())
            if (iterator.hasNext()) {
                substring.append(", ")
            }
        }

        substring.append("]")
        println(substring.toString())
    }
}