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
 * A basic doubly linked list implementation.
 *
 * <p>
 * This implementation implements only methods that are appropriate for a
 * doubly linked list data structure. Meaning, it will implement methods that
 * have asymptotic O(1) time complexity, or can reasonably be explained to
 * have a higher time complexity.
 *
 * <p>
 * For that reason, it doesn't fully implement the List interface and omits
 * methods like {@code get}, {@code put}, {@code remove} and so on.
 *
 * <p>
 * This data structure uses the concept of sentinel nodes (an additional
 * phantom header and trailer nodes) to make most of the method
 * implementations cleaner and with less edge cases.
 *
 * @param <E> The type of the elements in this collection
 */
public class DoublyLinkedList<E> implements Collection<E> {
    /**
     * A nested Node class of a {@link SinglyLinkedList}.
     * <p>
     * Stores references to its element and to the next node in the list.
     *
     * @param <E> The type of element in this node
     */
    private static class Node<E> {
        private final E element;
        private Node<E> prev;
        private Node<E> next;

        /**
         * Creates a node with the given element, the previous node, and next
         * node.
         *
         * @param element   the element to be stored
         * @param prev      reference to a node that will precede this new node
         * @param next      reference to a node that will follow this new node
         */
        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        /**
         * {@return the element stored at the node}
         */
        public E getElement() {
            return this.element;
        }

        /**
         * {@return the preceding node (or null if this is the first node}
         */
        public Node<E> getPrev() {
            return this.prev;
        }

        /**
         * Sets the node's prev reference to point to Node {@code node}
         * @param node  the node to set as the new prev
         */
        public void setPrev(Node<E> node) {
            this.prev = node;
        }

        /**
         * {@return the following node (or null if this is the last node}
         */
        public Node<E> getNext() {
            return this.next;
        }

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

    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    /**
     * A simple constructor to initialize the {@link DoublyLinkedList} data
     * structure using the concept of sentinel nodes.
     */
    public DoublyLinkedList() {
        this.header = new Node<>(null, null, null);
        this.trailer = new Node<>(null, this.header, null);
        this.header.setNext(this.trailer);
    }

    /**
     * A simple copy constructor.
     * @param copy the {@link DoublyLinkedList} to be copied
     * @noinspection CopyConstructorMissesField
     */
    public DoublyLinkedList(DoublyLinkedList<E> copy) {
        this();
        for (E element : copy) {
            this.addLast(element);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@return the first element of the {@link DoublyLinkedList}, or null if
     * it's empty}
     */
    public E first() {
        if (this.isEmpty()) {
            return null;
        }

        return this.header.getNext().getElement();
    }

    /**
     * {@return the last element of the {@link DoublyLinkedList}, or null if
     * it's empty}
     */
    public E last() {
        if (this.isEmpty()) {
            return null;
        }

        return this.trailer.getPrev().getElement();
    }

    /**
     * Add a new element at the beginning of the {@link DoublyLinkedList}.
     * @param element the new element to be added
     */
    public void addFirst(E element) {
        this.addBetween(element, this.header, this.header.getNext());
    }

    /**
     * Add a new element at the end of the {@link DoublyLinkedList}.
     * @param element the new element to be added
     */
    public void addLast(E element) {
        this.addBetween(element, this.trailer.getPrev(), this.trailer);
    }

    /**
     * Removes and returns the first element of the {@link DoublyLinkedList}, or
     * null if it's empty.
     *
     * @return the first element (removed), or null
     */
    public E removeFirst() {
        return this.remove(this.header.getNext());
    }

    /**
     * Removes and returns the last element of the {@link DoublyLinkedList}, or
     * null if it's empty.
     *
     * @return the last element (removed), or null
     */
    public E removeLast() {
        return this.remove(this.trailer.getPrev());
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node<E> walk = this.header.getNext();

        while (walk != this.trailer.getPrev()) {
            result.append(walk.getElement());
            result.append(" -> ");
            walk = walk.getNext();
        }

        result.append(this.trailer.getPrev().getElement());
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null) { return false; }
        if (this.getClass() != o.getClass()) { return false; }

        DoublyLinkedList<?> other = (DoublyLinkedList<?>) o;
        if (this.size() != other.size()) {
            return false;
        }

        Node<E> walkFirst = this.header.getNext();
        Node<?> walkSecond = other.header.getNext();

        while (walkFirst != this.trailer) {
            if (!java.util.Objects.equals(walkFirst.getElement(),
                    walkSecond.getElement())) {
                return false;
            }
            walkFirst = walkFirst.getNext();
            walkSecond = walkSecond.getNext();
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    /**
     * An {@link Iterator} class for the {@link DoublyLinkedList}.
     */
    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node<E> current = DoublyLinkedList.this.header.getNext();

        @Override
        public boolean hasNext() {
            return this.current != DoublyLinkedList.this.trailer;
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

    /**
     * Add element to the {@link DoublyLinkedList} in between the given nodes.
     * @param element       the element to add
     * @param predecessor   the previous node
     * @param successor     the next node
     */
    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        if (predecessor == null || successor == null) {
            throw new NullPointerException("Can't add between a null Node!");
        }

        if (predecessor.getNext() != successor || successor.getPrev() != predecessor) {
            throw new UnsupportedOperationException("Can't add between " +
                    "non-consecutive Nodes!");
        }

        Node<E> newNode = new Node<>(element, predecessor, successor);

        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        this.size++;
    }

    /**
     * Remove the given node from the list and return its element.
     * @param node  the node to remove
     * @return the element of the removed node
     */
    private E remove(Node<E> node) {
        Node<E> pred = node.getPrev();
        Node<E> next = node.getNext();

        pred.setNext(next);
        next.setPrev(pred);
        this.size--;

        return node.getElement();
    }
}
