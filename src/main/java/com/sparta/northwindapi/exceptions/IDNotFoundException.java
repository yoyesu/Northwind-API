package com.sparta.northwindapi.exceptions;

public class IDNotFoundException extends Exception {
    private final int statusCode;
    private final int path;
    private final String message;

    public IDNotFoundException(int statusCode, int path, String message){
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public int getPath() {
        return path;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "\n\tException=IDNotFoundException," +
                        "\n\tstatusCode=" + statusCode +
                        ",\n\tpath=" + path +
                        ",\n\tmessage='" + message + '\'' +
                '}';
    }
}
