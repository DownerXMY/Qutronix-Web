package com.qutronix.cloud.feynmanserver.exception;

import com.qutronix.common.exception.BizExceptionCodeEnum;
import com.qutronix.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongqikuan
 * @email zhong.qikuan@163.com
 * @date 2020/4/25 12:47
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.qutronix.cloud.feynmanserver.controller")
public class FeynmanExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验失败：{}，异常类型{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            map.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(BizExceptionCodeEnum.VALID_EXCEPTION.getCode(), BizExceptionCodeEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }
    @ExceptionHandler(value = Throwable.class)
    public  R HandleThrowable(Throwable throwable){
        log.error("HandleThrowable:{}",throwable);
        return  R.error(BizExceptionCodeEnum.UNKNOWN_EXCEPTION.getCode(),BizExceptionCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
