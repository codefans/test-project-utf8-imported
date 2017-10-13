package com.codefans.basicjava.lang.util;

import java.util.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-10-12 19:59
 */

public class HashMapTest<K,V> {


    class Entry<K,V> implements Map.Entry<K,V> {
        Entry<K, V> next;
        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        K key;
        V value;
        int hash;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }

        @Override
        public String toString() {
            return new StringBuilder().append(value).toString();
        }
    }

    private abstract class HashIterator<E> implements Iterator<E> {
        Entry<K,V> next;	// next entry to return
        int expectedModCount;	// For fast-fail
        int index;		// current slot
        Entry<K,V> current;	// current entry

        HashIterator() {
//            expectedModCount = modCount;
            if (size > 0) { // advance to first entry
                Entry[] t = elementDatas;
                //因为数据并不是按下标从小到大顺序存储的, 而是根据key的hash值随机存储的
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
        }

        public final boolean hasNext() {
            return next != null;
        }

        final Entry<K,V> nextEntry() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
            Entry<K,V> e = next;
            if (e == null)
                throw new NoSuchElementException();

            if ((next = e.next) == null) {
                Entry[] t = elementDatas;
                while (index < t.length && (next = t[index++]) == null)
                    ;
            }
            current = e;
            return e;
        }


    }

    Entry[] elementDatas;
    int size;
    int index;

    Set<K> keySet = null;
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null ? ks : (keySet = new KeySet()));
    }

    private final class KeySet extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return newKeyIterator();
        }
        public int size() {
            return size;
        }
//        public boolean contains(Object o) {
//            return containsKey(o);
//        }
//        public boolean remove(Object o) {
//            return this.removeEntryForKey(o) != null;
//        }
//        public void clear() {
//            this.clear();
//        }
    }

    private final class KeyIterator extends HashIterator<K> {
        public K next() {
            return nextEntry().getKey();
        }

        @Override
        public void remove() {

        }

    }


    // Subclass overrides these to alter behavior of views' iterator() method
    Iterator<K> newKeyIterator()   {
        return new KeyIterator();
    }

    public HashMapTest() {
        this.init();
    }

    /**
     *  1-11-12
     *  2-21
     *  3-31-32
     *  4
     */
    public void init() {
        elementDatas = new Entry[10];

        elementDatas[index] = new Entry(12, 12, 12, null);
        Entry entry = elementDatas[index];
        elementDatas[index] = new Entry(11, 11, 11, entry);
        Entry entry1 = elementDatas[index];
        elementDatas[index] = new Entry(1, 1, 1, entry1);

        index++;
        elementDatas[index] = new Entry(21, 21, 21, null);
        Entry entry2 = elementDatas[index];
        elementDatas[index] = new Entry(2, 2, 2, entry2);

        index++;
        elementDatas[index] = new Entry(32, 32, 32, null);
        Entry entry3 = elementDatas[index];
        elementDatas[index] = new Entry(31, 31, 31, entry3);
        Entry entry4 = elementDatas[index];
        elementDatas[index] = new Entry(3, 3, 3, entry4);

        index++;
        elementDatas[index] = new Entry(4, 4, 4, null);

        size = index;

    }

    public static void main(String[] args) {
        HashMapTest hmt = new HashMapTest();
        Iterator iter = hmt.keySet().iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
