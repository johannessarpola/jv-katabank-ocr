/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.johannes.kata.ocr.cells;

import java.util.List;

/**
 * Cell for the numbers, designed to be built by appending when read line by line, basically a wrapper around StringBuilder.
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 25, 2016
 */
public class Cell {
    StringBuilder cellContent;
    
    public Cell(String completeString) {
        cellContent = new StringBuilder(completeString);
    }
    public Cell(List<String> lines){
        cellContent = new StringBuilder();
        for(String line : lines) {
            cellContent.append(line);
        }
    }
    public Cell(){
        cellContent = new StringBuilder();
    }
    public void append(String stuff){
        cellContent.append(stuff);
    }
    @Override
    public String toString(){
        return cellContent.toString();
    }
    
}
