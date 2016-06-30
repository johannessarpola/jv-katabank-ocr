/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.johannes.kata.ocr.cells;

import java.util.List;

/**
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class RowBundle<T> {
    List<T> items;

    public RowBundle(List<T> items) {
        if(items.size() != 3) {
            this.items = items;
        }
    }
    public List<T> getRows(){
        return items;
    }
    
}
