package pl.damiankotynia.model;

import java.io.File;

public class Response {
    private File image;
    private ResponseStatus responseStatus;


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
