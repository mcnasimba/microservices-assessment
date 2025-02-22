package com.mcnasimba.msvc_clients.enums;

public enum ClientState {
    Activo, Inactivo;

    public static ClientState fromString (String value){
        return value.equalsIgnoreCase("Activo") ? Activo:Inactivo;
    }
}
