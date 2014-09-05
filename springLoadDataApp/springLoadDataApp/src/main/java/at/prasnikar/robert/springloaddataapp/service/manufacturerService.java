/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp.service;

import at.prasnikar.robert.springloaddataapp.domain.manufacturer;



/**
 *
 * @author Robert
 */
public interface manufacturerService {
    Boolean createManufacturer(manufacturer m);
    manufacturer getManufacturer(Long id);
}
