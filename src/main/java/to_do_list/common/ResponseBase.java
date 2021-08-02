package to_do_list.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseBase<T> {
    private HttpStatus status;
    private String message;
    private String description;
    private T result;

    public static ResponseBase build(){
        return new ResponseBase();
    }
    public ResponseBase setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ResponseBase setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResponseBase setDescription(String description){
        this.description = description;
        return this;
    }

    public ResponseBase<T> setResult(T result){
        this.result = result;
        return this;
    }
}
