/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package at.prasnikar.robert.springloaddataapp;

import at.prasnikar.robert.springloaddataapp.controller.manufacturerController;
import at.prasnikar.robert.springloaddataapp.domain.manufacturer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Robert
 */
public class loadDataApp {
    public static void main(String args[]) {
 		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:META-INF/spring/applicationContext.xml");

		manufacturerController manufacturerController = (manufacturerController) context
				.getBean("controller");
                
                manufacturer mf = new manufacturer();
                mf.setManufacturerName("AA");
                mf.setId(1L);
                
                manufacturerController.getManufacturerService().createManufacturer(mf);
                System.out.println(manufacturerController.getManufacturerService().getManufacturer(1L).getManufacturerName());
                
                mf.setManufacturerName("ABC");
                mf.setId(2L);
                manufacturerController.add(mf);
                
                manufacturerController.print();
    }
}
