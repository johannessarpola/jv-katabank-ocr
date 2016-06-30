/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.cells;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class RowBundles<T> implements Iterable<RowBundle<T>> {

    private List<RowBundle<T>> bundles;
    private final int bundleSize;

    public RowBundles(List<T> items, int bundleSize) {
        this.bundleSize = bundleSize;
        bundles = new ArrayList<>();
        divideToBundles(items);

    }

    private void divideToBundles(List<T> items) {
        int counter = 0;
        List<T> bundle = null;
        for (T item : items) {
            if (counter == 0) {
                bundle = new ArrayList<>(bundleSize);
            }
            if (counter <= bundleSize && bundle!=null) {
                bundle.add(item);
                counter++;
            } 
            if(counter == bundleSize) {
                bundles.add(new RowBundle<T>(items));
                counter = 0;
            }
        }
    }

    @Override
    public Iterator<RowBundle<T>> iterator() {
        RowBundles<T> instance = this;
        Iterator<RowBundle<T>> it = new Iterator<RowBundle<T>>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < instance.bundles.size() && instance.bundles.get(currentIndex) != null;
            }

            @Override
            public RowBundle<T> next() {
                return instance.bundles.get(currentIndex++);
            }

            @Override
            public void remove() {
                instance.bundles.remove(currentIndex);
            }
        };
        return it;
    }

}
