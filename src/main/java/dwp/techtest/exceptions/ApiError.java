package dwp.techtest.exceptions;


import java.util.Date;

public class ApiError {
    private Date timestamp;
    private int status;
    private ErrorMessages message;
    private Object details;

    public ApiError(int status, ErrorMessages message, Object details) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public ErrorMessages getMessage() {
        return message;
    }

    public void setMessage(ErrorMessages message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}