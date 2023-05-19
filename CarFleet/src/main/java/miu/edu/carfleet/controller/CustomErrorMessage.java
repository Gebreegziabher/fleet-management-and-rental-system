package miu.edu.carfleet.controller;

public class CustomErrorMessage {
    private String message;
    public CustomErrorMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
