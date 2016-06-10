package pl.mobile.fuelradar.data.model.remote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.mobile.fuelradar.data.model.FueilingStation;

/**
 * Created by zjuroszek on 14.05.16.
 */
public class Service {

    private static Service service;

    private Service() {

    }

    public static Service getServiceInstance(){
        if(service==null){
          return   service = new Service();
        }
        else {
            return service;
        }
    }

    List<FueilingStation> fueilingStations = new ArrayList<FueilingStation>( Arrays.asList(new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"),
            new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"),
            new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url"), new FueilingStation("Adress", "Cena", "url")
    ));



    public List<FueilingStation> getNearbyFueilingStations(){

        return fueilingStations;
    }


}
