package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int MIDDLE_ELEMS = 1000;
    private static final int ELEMS = 100_000;
    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 2000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = MIN_VALUE ; i < MAX_VALUE; i++ ) {
            arrayList.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addAll(arrayList);
        
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final Integer first = arrayList.get(0);
        arrayList.set(0, arrayList.get(arrayList.size()-1));
        arrayList.set(arrayList.size()-1, first);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer integer : arrayList) {
            System.out.println(integer);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        
        //ArrayList
        long time = System.nanoTime();
        for (int i = 1; i <= ELEMS; i++) {
            arrayList.add(i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Adding " + ELEMS + " elements into ArrayList took " + time + "ns (" + millis + "ms)");

        //LinkedList
        time = System.nanoTime();
        for (int i = 1; i <= ELEMS; i++) {
            linkedList.add(i);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Adding " + ELEMS + " elements into LinkedList took " + time + "ns (" + millis + "ms)");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */

        //ArrayList
        time = System.nanoTime();
        for (int i = 1; i <= MIDDLE_ELEMS; i++) {
            arrayList.get(arrayList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Reading " + MIDDLE_ELEMS + " elements into ArrayList took " + time + "ns (" + millis + "ms)");

        //LinkedList
        time = System.nanoTime();
        for (int i = 1; i <= MIDDLE_ELEMS; i++) {
            linkedList.get(linkedList.size()/2);
        }
        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println("Reading " + MIDDLE_ELEMS + "elements into LinkedList took " + time + "ns (" + millis + "ms)");

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> map = new HashMap<>();
        map.put("Africa", 1_110_635_000L);
        map.put("Americas", 972_005_000L);
        map.put("Antarctica", 0L);
        map.put("Asia", 4_298_723_000L);
        map.put("Europe", 742_452_000L);
        map.put("Oceania", 38_304_000L);

        /*
         * 8) Compute the population of the world
         */
        Long population = 0L;
        for (Map.Entry<String,Long> entry : map.entrySet()) {
            population = population + entry.getValue();
        }
        System.out.println("World population is " + population);
    }
}
