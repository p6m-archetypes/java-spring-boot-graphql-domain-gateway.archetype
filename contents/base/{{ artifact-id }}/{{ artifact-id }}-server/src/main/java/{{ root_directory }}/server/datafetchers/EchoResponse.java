package {{ root_package }}.server.datafetchers;

public class EchoResponse {
    private String message;

    public EchoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
