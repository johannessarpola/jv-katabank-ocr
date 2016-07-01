/* 
 * The MIT License
 *
 * Copyright 2016 Johannes Sarpola.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
                bundles.add(new RowBundle(bundle));
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
