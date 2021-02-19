package com.edson.teachercallroll.apidata.request;

public class UpdateInfoRequest {

    private String identifierNumber;
    private String password;

    public UpdateInfoRequest() {
    }

    public UpdateInfoRequest(String identifierNumber, String password) {
        this.identifierNumber = identifierNumber;
        this.password = password;
    }

}
