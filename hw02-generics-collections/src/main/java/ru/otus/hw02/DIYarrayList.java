package ru.otus.hw02;

import java.util.*;

class DIYarrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    public DIYarrayList() {
        elements = new Object[0];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() { throw new UnsupportedOperationException(); }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size());
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) { throw new UnsupportedOperationException(); }

    @Override
    public boolean add(T t) {
        if (this.size+1 > this.elements.length) {
            this.grow();
        }
        this.elements[this.size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public boolean containsAll(Collection<?> collection) { throw new UnsupportedOperationException(); }

    @Override
    public boolean addAll(Collection<? extends T> collection) { throw new UnsupportedOperationException(); }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) { throw new UnsupportedOperationException(); }

    @Override
    public boolean retainAll(Collection<?> collection) { throw new UnsupportedOperationException(); }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int i) {
        this.checkIndex(i);
        return (T) this.elements[i];
    }

    @Override
    public T set(int i, T t) {
        this.checkIndex(i);
        this.elements[i] = t;
        return (T) this.elements[i];
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int i) { throw new UnsupportedOperationException(); }

    @Override
    public int indexOf(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public int lastIndexOf(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public ListIterator<T> listIterator() {
        return new Itr();
    }

    @Override
    public ListIterator<T> listIterator(int i) { throw new UnsupportedOperationException(); }

    @Override
    public List<T> subList(int i, int i1) { throw new UnsupportedOperationException(); }

    private class Itr implements ListIterator<T> {
        private int cursor;

        public Itr() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() { throw new UnsupportedOperationException(); }

        @Override
        public T next() {
            return DIYarrayList.this.get(cursor++);
        }

        @Override
        public boolean hasPrevious() { throw new UnsupportedOperationException(); }

        @Override
        public T previous() { throw new UnsupportedOperationException(); }

        @Override
        public int nextIndex() { throw new UnsupportedOperationException(); }

        @Override
        public int previousIndex() { throw new UnsupportedOperationException(); }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            DIYarrayList.this.set(cursor-1, t);
        }

        @Override
        public void add(T t) { throw new UnsupportedOperationException(); }
    }

    private void grow() {
        int newCapacity;
        if (this.elements.length == 0) {
            newCapacity = 10;
        } else {
            newCapacity = this.elements.length + (this.elements.length >> 1);
        }

        this.elements = Arrays.copyOf(this.elements, newCapacity);
    }

    private void checkIndex(int i) {
        if (i > this.size()-1 || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + this.size());
        }
    }
}