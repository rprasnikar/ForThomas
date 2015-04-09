package newpackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robert
 */
public class publicOCCHandler {

    public publicOCCHandler() {
         anzProvider = getVehiclePools("Car2Go").size();       
    }
    private int anzProvider;

    /**
     * Get the value of anzProvider
     *
     * @return the value of anzProvider
     */
    public int getAnzProvider() {
        return anzProvider;
    }

    /**
     * Set the value of anzProvider
     *
     * @param anzProvider new value of anzProvider
     */
    public void setAnzProvider(int anzProvider) {
        this.anzProvider = anzProvider;
    }

    private static java.util.List<at.nttdata.mobile.VehiclePoolData> getVehiclePools(java.lang.String providerName) {
        at.nttdata.mobile.MobileAppInfoServiceImplService service = new at.nttdata.mobile.MobileAppInfoServiceImplService();
        at.nttdata.mobile.MobileAppInfoService port = service.getMobileAppInfoServicePort();
        return port.getVehiclePools(providerName);
    }



    
}
