package to_do_list.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import to_do_list.common.BusinessException;
import to_do_list.common.ResponseBase;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBase> handlerBusinessException(BusinessException ex) {
        logger.info("Error business exception", ex.getMessage());
        ResponseBase responseBase = ResponseBase.build()
                .setStatus(ex.getErrorCode())
                .setMessage(ex.getMessage());
        return ResponseEntity.ok(responseBase);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> handlerException(Exception ex) {
        logger.error("Error exception: ", ex.getMessage());
        ResponseBase responseBase = ResponseBase.build()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage("System error. Please try again")
                .setDescription(ex.getMessage());
        return ResponseEntity.ok(responseBase);
    }

}
