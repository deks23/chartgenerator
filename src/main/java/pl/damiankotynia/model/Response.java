package pl.damiankotynia.model;

public class Response {
    private ResponseType responseType;

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
}
