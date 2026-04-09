package org.example

import org.example.list.CustomArrayList
import org.example.list.SingleLinkedList
import org.example.list.SingleLinkedList.Companion.singleLinkedListOf
import org.example.stack.ArrayListStack
import org.example.stack.SingleLinkedStack


fun main() {
    val t = singleLinkedListOf(1, 2, 3)

    ListPrinter.printList(t)

    val arrayList = CustomArrayList(5)
    arrayList.add(1)
    arrayList.add(2)
    arrayList.add(3)
    println("CustomArrayList:  ")
    ListPrinter.printList(arrayList)

    val linkedList = SingleLinkedList()
    linkedList.add(1)
    linkedList.add(2)
    linkedList.add(3)
    println("SingleLinkedList: ")
    ListPrinter.printList(linkedList)

    val arrayStack = ArrayListStack()
    arrayStack.push(1)
    arrayStack.push(2)
    arrayStack.push(3)
    println("ArrayListStack:   ")
    ListPrinter.printList(arrayStack)

    val linkedStack = SingleLinkedStack()
    linkedStack.push(1)
    linkedStack.push(2)
    linkedStack.push(3)
    println("SingleLinkedStack:")
    ListPrinter.printList(linkedStack)
}