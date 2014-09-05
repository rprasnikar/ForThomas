/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp.service;

import at.prasnikar.robert.springloaddataapp.dao.manufacturerDao;
import at.prasnikar.robert.springloaddataapp.domain.manufacturer;
import lombok.Data;

/**
 *
 * @author Robert
 */
@Data
public class manufacturerServiceImpl implements manufacturerService{
    private manufacturerDao manufacturerDao;

    @Override
    public Boolean createManufacturer(manufacturer m) {
        return manufacturerDao.createManufacturer(m); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public manufacturer getManufacturer(Long id) {
        return manufacturerDao.getManufacturer(id); //To change body of generated methods, choose Tools | Templates.
    }
    
}
