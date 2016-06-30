/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.cells;

import fi.johannes.kata.ocr.utils.FileConnection;
import fi.johannes.kata.ocr.utils.structs.IntegerPair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class CellRows implements Iterable<CellRow> {

    private List<CellRow> cellRows;
    private Integer height;
    private Integer width;
    private FileConnection connection;

    public CellRows(IntegerPair pair, FileConnection connection) throws IOException {
        init(pair, connection);
        buildRows();
    }

    private void init(IntegerPair pair, FileConnection connection) {
        this.height = pair.getY();
        this.width = pair.getX();
        this.connection = connection;
        cellRows = new ArrayList<>();

    }
   
    private void buildRows() throws IOException{
        List<String> lines = connection.readLines();
        RowBundles<String> bundles = new RowBundles<>(lines, height);
        Iterator<RowBundle<String>> iterator = bundles.iterator();
        while (iterator.hasNext()) {
            RowBundle<String> rb = iterator.next();
            CellRow cr = new CellRow(rb.getRows(), width);
            cellRows.add(cr);
        }
    }
    
    @Override
    public Iterator<CellRow> iterator() {
        CellRows instance = this;
        Iterator<CellRow> it = new Iterator<CellRow>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < instance.cellRows.size() && instance.cellRows.get(currentIndex) != null;
            }

            @Override
            public CellRow next() {
                return instance.cellRows.get(currentIndex++);
            }

            @Override
            public void remove() {
                instance.cellRows.remove(currentIndex);
            }
        };
        return it;
    }


}
