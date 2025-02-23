package com.mcnasimba.msvc_clients.enums;

public enum ClientState {
    Active, Inactive;

    public static ClientState fromString (String value){
        return value.equalsIgnoreCase("Active") ? Active:Inactive;
    }
}
