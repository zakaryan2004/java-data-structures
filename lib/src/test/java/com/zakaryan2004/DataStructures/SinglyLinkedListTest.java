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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A unit test suite for the {@link SinglyLinkedList} data structure.
 */
class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        this.list = new SinglyLinkedList<>();
    }

    @Test
    @DisplayName("New list should be empty")
    void testNewList() {
        assertTrue(this.list.isEmpty());
        assertEquals(0, this.list.size());
        assertNull(this.list.first());
        assertNull(this.list.last());
    }

    @Test
    @DisplayName("addFirst should update head and tail on empty list")
    void testAddFirstEmpty() {
        this.list.addFirst(10);
        assertEquals(10, this.list.first());
        assertEquals(10, this.list.last());
        assertEquals(1, this.list.size());
    }

    @Test
    @DisplayName("addLast should update head and tail on empty list")
    void testAddLastEmpty() {
        this.list.addLast(20);
        assertEquals(20, this.list.first());
        assertEquals(20, this.list.last());
    }

    @Test
    @DisplayName("Multiple additions should maintain correct order")
    void testMultipleAdditions() {
        this.list.addFirst(2);
        this.list.addFirst(1);
        this.list.addLast(3);
        // List should be 1 -> 2 -> 3
        assertEquals(1, this.list.first());
        assertEquals(3, this.list.last());
        assertEquals(3, this.list.size());
    }

    @Test
    void testRemoveFirst() {
        this.list.addLast(1);
        this.list.addLast(2);
        assertEquals(1, this.list.removeFirst());
        assertEquals(2, this.list.first());
        assertEquals(1, this.list.size());
    }

    @Test
    @DisplayName("Removing last element should nullify head and tail")
    void testRemoveToEmpty() {
        this.list.addFirst(100);
        this.list.removeFirst();
        assertTrue(this.list.isEmpty());
        assertNull(this.list.first());
        assertNull(this.list.last());
    }

    @Test
    @DisplayName("Equals should compare each element")
    void testEquals() {
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        this.list.addLast(1);
        this.list.addLast(2);
        list2.addLast(1);
        list2.addLast(2);

        assertEquals(this.list, list2);

        list2.addLast(3);
        assertNotEquals(this.list, list2);
    }

    @Test
    @DisplayName("Copy constructor should create an independent deep copy")
    void testCopyConstructor() {
        this.list.addLast(1);
        this.list.addLast(2);

        SinglyLinkedList<Integer> copy = new SinglyLinkedList<>(this.list);
        assertEquals(this.list, copy);

        // Modify original, the copy should remain unchanged
        this.list.addLast(3);
        assertNotEquals(this.list, copy);
        assertEquals(2, copy.size());
    }

    @Test
    @DisplayName("Iterator should traverse elements in the correct insertion order")
    void testIterator() {
        this.list.addLast(10);
        this.list.addLast(20);
        this.list.addLast(30);

        java.util.Iterator<Integer> it = this.list.iterator();

        assertTrue(it.hasNext());
        assertEquals(10, it.next());

        assertTrue(it.hasNext());
        assertEquals(20, it.next());

        assertTrue(it.hasNext());
        assertEquals(30, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterator should throw NoSuchElementException when exhausted")
    void testIteratorException() {
        this.list.addFirst(1);
        java.util.Iterator<Integer> it = this.list.iterator();

        it.next(); // Consume only element

        assertFalse(it.hasNext());
        assertThrows(java.util.NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("toString should produce the correct visual representation")
    void testToString() {
        // Test empty
        assertEquals("", this.list.toString(), "Empty list should return empty string");

        // Test populated
        this.list.addLast(1);
        this.list.addLast(2);
        this.list.addLast(3);

        assertEquals("1 -> 2 -> 3", this.list.toString());
    }
}