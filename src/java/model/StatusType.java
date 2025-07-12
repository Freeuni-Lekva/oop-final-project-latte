package model;

public enum StatusType {
    waiting, accepted, rejected;
    public static StatusType fromString(String type){
        return StatusType.valueOf(type.toLowerCase());
    }
    public String getType(){
        return this.name().toLowerCase();
    }
}
