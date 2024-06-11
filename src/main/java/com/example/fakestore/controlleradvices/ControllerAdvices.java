package com.example.fakestore.controlleradvices;


import com.example.fakestore.productDTOs.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.View;

@ControllerAdvice
public class ControllerAdvices {

    private final View error;

    public ControllerAdvices(View error) {
        this.error = error;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handlerArithmeticException(){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("arithmetic problems occurred");
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity(errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerException(){

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setMessage("null pointer exception");

        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
