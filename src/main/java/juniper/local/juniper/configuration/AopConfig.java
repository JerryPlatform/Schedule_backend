package juniper.local.juniper.configuration;

import juniper.local.juniper.vo.Response;
import juniper.local.juniper.vo.Result;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopConfig {

    @AfterReturning(value = "execution(* juniper.local.juniper.controller.*.*(..))", returning = "result")
    public Object AfterReturning(Object result) {

        if (result != null) {
            if(result instanceof Result) {
                ((Result)result).setToken("");
            } else if (result instanceof Response) {
                ((Response)result).getResponse().setToken("");
            } else if (result instanceof ResponseEntity){
                Response response = (Response)((ResponseEntity)result).getBody();
                response.getResponse().setToken("");
            }
        }

        return result;
    }

}
