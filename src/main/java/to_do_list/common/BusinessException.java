package to_do_list.common;

import lombok.Data;

import javax.annotation.PostConstruct;

@Data
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;


    public BusinessException(String errorCode, String message) {
        super(errorCode);
        this.errorCode = errorCode;
        this.message = message;

    }




}
