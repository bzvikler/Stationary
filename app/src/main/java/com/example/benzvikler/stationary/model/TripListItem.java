package com.example.benzvikler.stationary.model;

import java.util.Date;

/**
 * Created by Mojio on 2016-12-30.
 */

public class TripListItem {
    private Date date;
    private String startAddress;
    private String endAddress;

    public String getDate() {
        return date.toString() ;
    }

    public void setDate(Long endTimeStamp) {
        this.date = new Date(endTimeStamp);
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }
}
