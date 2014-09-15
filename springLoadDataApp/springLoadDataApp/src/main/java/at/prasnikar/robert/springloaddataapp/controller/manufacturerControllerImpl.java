/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp.controller;

import at.prasnikar.robert.springloaddataapp.domain.manufacturer;
import at.prasnikar.robert.springloaddataapp.service.manufacturerService;

/**
 *
 * @author Robert
 */
public class manufacturerControllerImpl implements manufacturerController {
    private manufacturerService m;

    /**
     *
     * @return
     */
    @Override
    public manufacturerService getManufacturerService() {
        return this.m;
    }
    
    public void setManufacturerService(manufacturerService ms) {
        this.m = ms;
    }

    public manufacturerControllerImpl() {
        System.out.println("init manufacturerControllerImpl.");
    }

    public boolean add(manufacturer mf) {
        m.createManufacturer(mf);
        return true;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void print() {
        m.print(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
