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

/**
 * The main interface that all data structures implement.
 *
 * @param <E> The type of the elements in this collection
 */
public interface Collection<E> extends Iterable<E> {
    /**
     * Returns the size of the collection
     *
     * @return the number of elements in the collection
     */
    int size();

    /**
     * Checks if the collection is considered empty. This method is
     * equivalent to checking if {@link #size() size()} is {@code 0}.
     *
     * @return whether the collection is empty
     * @see #size()
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }
}
