package com.gojeck;

/**
 * Created by vamshi on 7/7/16.
 */
public class Slot {
    private long slot;
    private String regNumber;
    private String color;

    public Slot(long slot, String regNumber, String color) {
        this.slot = slot;
        this.regNumber = regNumber;
        this.color = color;
    }

    public long getSlot() {
        return slot;
    }

    public void setSlot(long slot) {
        this.slot = slot;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return slot+"\t"+regNumber+"\t"+color;
    }
}
