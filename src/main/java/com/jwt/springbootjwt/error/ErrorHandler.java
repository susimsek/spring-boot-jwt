package com.jwt.springbootjwt.error;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//default error pathi atadık
//Springin Error kontrolleri yerine uygulamamıza özgü bir error controller yazdık.
//Error handler sayesinde hata mesajlarının ApiError tipinde olacağını kesinleştirdik
@RestController
@RequiredArgsConstructor
public class ErrorHandler implements ErrorController {

    //error attributeyi çağırdık
    private final ErrorAttributes errorAttributes;

    //web isteklerindeki tüm hatalar /error requestine düşsün
    @Override
    public String getErrorPath() {
        return "/error";
    }

    //gelen requestdeki hata mesajlarının tümü /errora düşer
    //validation mesajlarının ve hata mesajlarının döndürüldüğü genel bir error controller implemente ettik
    @RequestMapping("/error")
    public ApiError handleError(WebRequest webRequest){
        Map<String, Object> attributes = this.errorAttributes.getErrorAttributes(webRequest, true);//hata sonucu dönen attributesleri aldık
        String message = (String) attributes.get("message");//hata mesajı içindeki mesajı aldık
        String path = (String) attributes.get("path"); //hata mesajı içindeki patih aldık
        int status = (Integer) attributes.get("status"); //hata statusunu aldık
        long timestamp = ((Date) attributes.get("timestamp")).getTime(); //timestampi aldık
        ApiError error = new ApiError(status,message,path,timestamp);
        if(attributes.containsKey("errors")){//error objesi varsa,yani hata mesajı varsa.bu kontrolü yapamazsak null pointer döner
            List<FieldError> fieldErrors = (List<FieldError>) attributes.get("errors");//error fieldları aldık.validation için
            Map<String, String> errors = new HashMap<>();
            for(FieldError fieldError: fieldErrors){
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());//her bir  field ve valuesi için hata mesajını mapledik.
            }
            error.setErrors(errors);//hata errorlerini atadık
        }
        return error;
    }
}
