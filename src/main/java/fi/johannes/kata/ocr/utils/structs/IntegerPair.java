/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.johannes.kata.ocr.utils.structs;

/** 
 *
 * @author Johannes Sarpola <johannes.sarpola at gmail.com>
 * @date Jun 30, 2016
 */
public class IntegerPair {
    final Integer x;
    final Integer y;

    public IntegerPair(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getY() {
        return y;
    }
    
    public Integer getX() {
        return x;
    }
    
    
}
