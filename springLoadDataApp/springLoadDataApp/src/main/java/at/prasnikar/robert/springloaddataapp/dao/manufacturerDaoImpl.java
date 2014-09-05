/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp.dao;

import at.prasnikar.robert.springloaddataapp.domain.manufacturer;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Robert
 */
public class manufacturerDaoImpl implements manufacturerDao {
    private Map<Long,manufacturer> manufacturers = new HashMap<Long, manufacturer>();

    @Override
    public manufacturer getManufacturer(Long id) {
        return manufacturers.get(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createManufacturer(manufacturer m) {
        manufacturers.put(m.getId(), m); //To change body of generated methods, choose Tools | Templates.
        return true;
    }
    
    
}
