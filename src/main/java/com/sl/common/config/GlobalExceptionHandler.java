package com.sl.common.config;

import com.sl.common.ResponseData;
import com.sl.common.exception.GlobalErrorCodeEnum;
import com.sl.common.exception.SchemeNotFoundException;
import com.sl.common.exception.internal.DataParseException;
import com.sl.common.exception.model.ModelCalculationException;
import com.sl.common.exception.model.ModelInputException;
import com.sl.common.exception.model.ModelResultParseException;
import com.sl.common.exception.model.ModelSaveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ModelInputException.class)
    public ResponseData<Void> handleModelInputException(ModelInputException e) {
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }

    @ExceptionHandler(SchemeNotFoundException.class)
    public ResponseData<Void> handleSchemeNotFoundException(SchemeNotFoundException e) {
        log.error("方案查询失败，原因：{}",
                e.getMessage()
        );
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }

    @ExceptionHandler(ModelCalculationException.class)
    public ResponseData<Void> handleModelCalculationException(ModelCalculationException e) {
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }

    @ExceptionHandler(ModelResultParseException.class)
    public ResponseData<Void> handleModelCalculationException(ModelResultParseException e) {
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }

    @ExceptionHandler(DataParseException.class)
    public ResponseData<Void> handleModelCalculationException(DataParseException e) {
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }
    @ExceptionHandler(ModelSaveException.class)
    public ResponseData<Void> handleModelCalculationException(ModelSaveException e) {
        return ResponseData.error(e.getErrorCode() , e.getMessage() );
    }

    @ExceptionHandler(Exception.class)
    public ResponseData<Void> handleGlobalException(Exception e) {

        return ResponseData.error(GlobalErrorCodeEnum.INTERNAL_SERVER_ERROR , e.getMessage() );
    }
}