package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SwaggerDeletePojo {
    /*
    {
    "code": 200,
    "type": "unknown",
    "message": "saadet123"
}
     */

    private Integer code;
    private String type;
    private String message;

    public SwaggerDeletePojo() {
    }

    public SwaggerDeletePojo(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SwaggerDeletePojo{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
