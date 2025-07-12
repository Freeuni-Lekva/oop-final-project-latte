package model;

public enum MessageType {
    friend_request, challenge, text;
    public static MessageType fromString(String type){
        return MessageType.valueOf(type.toLowerCase());
    }
    public String getType(){
        return this.name().toLowerCase();
    }

}
