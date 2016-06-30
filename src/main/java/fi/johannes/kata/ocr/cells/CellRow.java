/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johannes.kata.ocr.cells;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Builds list of three by three cells from input
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
// Wrap this around with multi three line stuff (height)
public class CellRow {

    private List<Cell> cells;
    private int celllength;
    private Splitter fixedLengthSplitter;

    public CellRow(List<String> lines, int cellLength) {
        init(cellLength);
        cells = readFromList(lines);
    }

    public CellRow(Stream stream, int cellLength) {
        init(cellLength);
        cells = readCellsFromStream(stream);
    }

    private void init(int cellLength) {
        this.celllength = cellLength;
        this.fixedLengthSplitter = Splitter.fixedLength(celllength);

    }

    /**
     * Gets cell
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException implicintly throw exception as index
     * might be too big
     */
    public Cell getCell(int index) throws IndexOutOfBoundsException {
        if (index < cells.size()) {
            return cells.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * gets cells as list
     *
     * @return
     */
    public List<Cell> getAsList() {
        return cells;
    }

    /**
     * checks if index fits
     *
     * @param index
     * @return
     */
    public boolean fits(int index) {
        return index < cells.size();
    }

    /**
     * returns size
     *
     * @return
     */
    public int size() {
        return cells.size();
    }

    /**
     * Reads from list of lines
     *
     * @param ls
     * @return
     */
    private List<Cell> readFromList(List<String> ls) {
        List<Cell> tCell = new ArrayList<>();
        for (String line : ls) {
            List<String> parts = fixedLengthSplitter.splitToList(line);
            for (int i = 0; i < parts.size(); i++) {
                // if cell doesn't exist yet create
                try {
                    tCell.get(i).append(parts.get(i));
                } catch (IndexOutOfBoundsException e) {
                    Cell newCell = new Cell();
                    newCell.append(parts.get(i));
                    tCell.add(i, newCell);
                }
            }
            // otherwise append
        }

        return tCell;
    }

    /**
     * Reads from stream
     *
     * @param st
     * @return
     */
    private List<Cell> readCellsFromStream(Stream<String> st) {
        List<String> lines = new ArrayList<>();
        st.forEach((String t) -> {
            lines.add(t);
        });
        return readFromList(lines);
    }
}
