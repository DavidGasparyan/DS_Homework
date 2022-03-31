package main.company;// Lilit
//
// Problem 2 - 10 points
// 1. Collection should extend Iterable interface
//
// Problem 3 - 9 points
// 1. No need to create temp array when removing and adding elements
// 2. print() -> print the .toString() of the element
// 3. Better to declare the array of type T
//
//
// Problem 4 - 9 points
// 1. empty() -> make last also null, and size = 0
// 2. print() -> print the .toString() of the element
//
//
// Problem 5 - 8 points
// 1. empty() -> make last also null, and size = 0
// 2. print() -> print the .toString() of the element
// 3. addFirst() -> does not update the link of "first"'s back
//
// Problem 6 - 10 points
//
//
// Problem 7 - 10 points
//
//
// Problem 8 - 3 points
// 1. Does not work, some of the logic is correct.
//


//package com.main.company;
//
//
//import com.main.company.utility.DoubleLinkedList;
//import com.main.company.utility.ListADT;

// The doubleListElements is at the bottom of Main file
//public class Homework2 {
//
//    public static void main(String[] args) throws Exception {
//        int a = 10 & 10;
//
//        System.out.println(a);
//    }
//
//    static void doubleListElements(ListADT<?> list) {
//        DoubleLinkedList<Object> newList = new DoubleLinkedList<>();
//
//        if (list.first() != null) {
//            newList.addLast(list.first());
//            newList.addLast(list.first());
//            list.removeFirst();
//
//            doubleListElements(list);
//        }
//
//        newList.print();
//    }
//}
