package to_do_list.common;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class BusinessException extends RuntimeException {
    private HttpStatus errorCode;
    private String message;


    public BusinessException(HttpStatus errorCode, String message) {
        super(errorCode.toString());
        this.errorCode = errorCode;
        this.message = message;

    }
    public static void handleBussinessException(Exception e) {
        String message = e.getMessage().toLowerCase();
        if (message.contains("null")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Null Pointer Exception");
        } else if (message.contains("duplicate")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Duplicate Data Error");
        } else if (message.contains("format")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Invalid Data Format.");
        }
    }





}
