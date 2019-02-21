package pl.damiankotynia.model;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseType responseType;
    private String message;

    public Response() {
    }

    public Response(ResponseType responseType) {
        this.responseType = responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
