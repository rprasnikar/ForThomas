/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nttdata.imobility.occ.csvimporter;

/**
 *
 * @author Robert
 */
public class Card {
    private String tag;

    public Card() {
    }

    public Card(String tag) {
        this.tag = tag;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
}
