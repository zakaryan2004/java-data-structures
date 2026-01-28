/*
 * MIT License
 * Copyright (c) 2026 Gegham Zakaryan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.zakaryan2004.DataStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A basic singly linked list implementation.
 *
 * <p>
 * This implementation implements only methods that are appropriate for a
 * singly linked list data structure. Meaning, it will implement methods that
 * have asymptotic O(1) time complexity, or can reasonably be explained to
 * have a higher time complexity.
 *
 * <p>
 * For that reason, it doesn't implement the List interface.
 *
 * @param <E> The type of the elements in this collection
 */
public class SinglyLinkedList<E> implements Collection<E> {
    /**
     * A nested Node class of a {@link SinglyLinkedList}.
     * <p>
     * Stores references to its element and to the next node in the list.
     *
     * @param <E> The type of element in this node
     */
    private static class Node<E> {
        private final E element;
        private Node<E> next;

        /**
         * Creates a node with the given element and next node.
         *
         * @param element   the element to be stored
         * @param next      reference to a node that will follow this new node
         */
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        /**
         * {@return the element stored at the node}
         */
        public E getElement() {
            return this.element;
        }

        /**
         * {@return the following node (or null if this is the last node}
         */
        public Node<E> getNext() { return this.next; }

        /**
         * Sets the node's next reference to point to Node {@code node}
         * @param node  the node to set as the new next
         */
        public void setNext(Node<E> node) {
            this.next = node;
        }

        @Override
        public String toString() {
            return this.element.toString();
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    /**
     * An intentionally empty constructor.
     */
    public SinglyLinkedList() { }

    /**
     * A simple copy constructor.
     * @param copy the {@link SinglyLinkedList} to be copied
     * @noinspection CopyConstructorMissesField
     */
    public SinglyLinkedList(SinglyLinkedList<E> copy) {
        for (E element : copy) {
            this.addLast(element);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@return the first element of the {@link SinglyLinkedList}, or null if
     * it's empty}
     */
    public E first() {
        if (this.isEmpty()) {
            return null;
        }

        return this.head.getElement();
    }

    /**
     * {@return the last element of the {@link SinglyLinkedList}, or null if
     * it's empty}
     */
    public E last() {
        if (this.isEmpty()) {
            return null;
        }

        return this.tail.getElement();
    }

    /**
     * Add a new element at the beginning of the {@link SinglyLinkedList}.
     * @param element the new element to be added
     */
    public void addFirst(E element) {
        this.head = new Node<>(element, this.head);

        if (this.isEmpty()) {
            this.tail = this.head;
        }

        this.size++;
    }

    /**
     * Add a new element at the end of the {@link SinglyLinkedList}.
     * @param element the new element to be added
     */
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);

        if (this.isEmpty()) {
            this.head = newNode;
        }
        else {
            this.tail.setNext(newNode);
        }

        this.tail = newNode;
        this.size++;
    }

    /**
     * Removes and returns the first element of the {@link SinglyLinkedList}, or
     * null if it's empty.
     *
     * @return the first element of the {@link SinglyLinkedList}, or null
     */
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        E old = this.head.getElement();

        this.head = this.head.getNext();
        this.size--;

        if (this.isEmpty()) {
            this.tail = null;
        }

        return old;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node<E> walk = this.head;

        while (walk != this.tail) {
            result.append(walk.getElement());
            result.append(" -> ");
            walk = walk.getNext();
        }

        result.append(this.tail.getElement());
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null) { return false; }
        if (this.getClass() != o.getClass()) { return false; }

        SinglyLinkedList<?> other = (SinglyLinkedList<?>) o;
        if (this.size() != other.size()) {
            return false;
        }

        Node<E> walkFirst = this.head;
        Node<?> walkSecond = other.head;

        while (walkFirst != null) {
            if (!java.util.Objects.equals(walkFirst.getElement(),
                    walkSecond.getElement())) {
                return false;
            }
            walkFirst = walkFirst.getNext();
            walkSecond = walkSecond.getNext();
        }

        return true;
    }

    /**
     * An {@link Iterator} class for the {@link SinglyLinkedList}.
     */
    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> current = SinglyLinkedList.this.head;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No next element");
            }

            E old = this.current.getElement();
            this.current = this.current.getNext();
            return old;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }
}
