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
public interface manufacturerController {
    manufacturerService getManufacturerService();
    
    boolean add(manufacturer m);
    
    void print();
}
