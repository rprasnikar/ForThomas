/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp.dao;

import at.prasnikar.robert.springloaddataapp.domain.manufacturer;
import java.util.Collection;

/**
 *
 * @author Robert
 */
public interface manufacturerDao {
    manufacturer getManufacturer(Long id);
    
    boolean createManufacturer(manufacturer m);

    public Collection<manufacturer> getAll();
}
