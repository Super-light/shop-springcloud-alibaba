package io.plus.shop.utils.exception;

import io.plus.shop.utils.contants.HttpCode;
import io.plus.shop.utils.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description:全局异常处理器
 *
 * @author Super_light
 * @date 4/22/22 3:20 PM
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    /**
     * 全局异常处理，统一返回状态码
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        logger.error("服务器抛出了异常：{}", e);
        return new Result<String>(HttpCode.FAILURE, "执行失败", e.getMessage());
    }
}
