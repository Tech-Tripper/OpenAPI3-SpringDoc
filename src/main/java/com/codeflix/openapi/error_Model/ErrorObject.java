package com.codeflix.openapi.error_Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {

    private  String errorCode;
    private String errorDescription;
}
