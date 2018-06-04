package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

import java.util.ArrayList;
import java.util.List;

public class qAroundFacility {
    @FiledNameReplace(name = "景点", MethodName = "setSight",GetMethodName = "getSight")
    public List<qAroundDetailInfos> sight;
    @FiledNameReplace(name = "美食", MethodName = "setFood",GetMethodName = "getFood")
    public List<qAroundDetailInfos> food;
    @FiledNameReplace(name = "机场车站", MethodName = "setAirportTraffic",GetMethodName = "getAirportTraffic")
    public List<qAroundDetailInfos> airportTraffic;
    @FiledNameReplace(name = "地铁", MethodName = "setDitie",GetMethodName = "getDitie")
    public List<qAroundDetailInfos> ditie;


    @Override
    public String toString() {
        return "qAroundFacility{" +
                "sight=" + sight +
                ", food=" + food +
                ", airportTraffic=" + airportTraffic +
                ", ditie=" + ditie +
                '}';
    }

    public qAroundFacility() {
        sight = new ArrayList<>();
        food = new ArrayList<>();
        airportTraffic = new ArrayList<>();
        ditie = new ArrayList<>();
    }
    public qAroundFacility(List<qAroundDetailInfos> sight, List<qAroundDetailInfos> food, List<qAroundDetailInfos> airportTraffic, List<qAroundDetailInfos> ditie) {
        this.sight = sight;
        this.food = food;
        this.airportTraffic = airportTraffic;
        this.ditie = ditie;
    }

    public List<qAroundDetailInfos> getSight() {
        return sight;
    }

    public void setSight(List<qAroundDetailInfos> sight) {
        this.sight = sight;
    }

    public List<qAroundDetailInfos> getFood() {
        return food;
    }

    public void setFood(List<qAroundDetailInfos> food) {
        this.food = food;
    }

    public List<qAroundDetailInfos> getAirportTraffic() {
        return airportTraffic;
    }

    public void setAirportTraffic(List<qAroundDetailInfos> airportTraffic) {
        this.airportTraffic = airportTraffic;
    }

    public List<qAroundDetailInfos> getDitie() {
        return ditie;
    }

    public void setDitie(List<qAroundDetailInfos> ditie) {
        this.ditie = ditie;
    }
}
