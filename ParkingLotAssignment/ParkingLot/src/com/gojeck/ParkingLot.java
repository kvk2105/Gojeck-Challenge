package com.gojeck;

import java.util.*;

/**
 * Created by vamshi on 7/7/16.
 */
public class ParkingLot {
    private Map<Long,Slot> slots;
    private long currentCapacity;
    private LinkedHashMap<Long,Boolean> slotsAvailabilityMap;
    private long actualCapacity;

    public void setIsLotEmpty(boolean isLotEmpty) {
        this.isLotEmpty = isLotEmpty;
    }

    private boolean isLotEmpty;
    private boolean isLotFull;

    public ParkingLot(long capacity){
        this.slots=new HashMap<Long, Slot>();
        this.currentCapacity=capacity;
        this.actualCapacity=capacity;
        this.slotsAvailabilityMap=new LinkedHashMap<Long, Boolean>();
        long i=0;
        while(i++ < actualCapacity)
            this.slotsAvailabilityMap.put(i,true);
        this.isLotEmpty =false;

    }

    public long getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(long actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public boolean isLotEmpty() {
        return isLotEmpty;
    }

    public void setIsLotFull(boolean isLotFull) {
        this.isLotFull = isLotFull;
    }

    public boolean isLotFull() {
        return isLotFull;
    }

    public long getCurrentCapacity() {
        return currentCapacity;
    }

    public Map<Long, Slot> getSlots() {
        return slots;
    }

    public void setSlots(Map<Long, Slot> slots) {
        this.slots = slots;
    }

    public LinkedHashMap<Long, Boolean> getSlotsAvailabilityMap() {
        return slotsAvailabilityMap;
    }

    public void setSlotsAvailabilityMap(LinkedHashMap<Long, Boolean> slotsAvailabilityMap) {
        this.slotsAvailabilityMap = slotsAvailabilityMap;
    }

    public void park(Slot slot){
        this.slots.put(slot.getSlot(), slot);
        this.slotsAvailabilityMap.put(slot.getSlot(),false);
        this.currentCapacity--;
        if(this.currentCapacity==0)
            this.setIsLotFull(true);
        else
            this.setIsLotEmpty(false);
    }

    public void leave(long slotNumber){
        this.slots.remove(slotNumber);
        this.slotsAvailabilityMap.put(slotNumber,true);
        this.currentCapacity++;
        if(this.currentCapacity==this.actualCapacity)
            this.setIsLotEmpty(true);
        else
            this.setIsLotFull(false);
    }

    public List<String> getRegNumbersByColor(String color){
        List<String> regNumbers=new ArrayList<String>();
        for(Slot slot:this.slots.values()){
            if(slot.getColor().equalsIgnoreCase(color))
                regNumbers.add(slot.getRegNumber());
        }
        return  regNumbers;
    }

    public long getSlotByRegNumber(String regNumber){
        for (Slot slot:this.slots.values())
            if(regNumber.equalsIgnoreCase(slot.getRegNumber()))
                return slot.getSlot();
        return -1;
    }

    public List<Long> getSlotsByColor(String color){
        List<Long> slots=new ArrayList<Long>();
        for(Slot slot:this.slots.values()){
            if(slot.getColor().equalsIgnoreCase(color))
                slots.add(slot.getSlot());
        }
        return slots;
    }

    public long getFreeSlot(){
        for (Map.Entry<Long,Boolean> entry:this.slotsAvailabilityMap.entrySet()) {
            if (entry.getValue() == true)
                return entry.getKey();
        }
        return -1;
    }


}
