package com.example.demo.common;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.SimpleExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class Exceptions {
    @ExceptionHandler()
    public Result<String>ExecptionHandler(Exception exception){
        if (exception.getMessage().contains("Duplicate entry")){
            String[] split = exception.getMessage().split(" ");
            String msg = split[2]+"已存在";
            return Result.error(msg);
        }
        else if(exception.getMessage().contains(""))
        {

        }
        return Result.error(exception.getMessage());
    }
}
