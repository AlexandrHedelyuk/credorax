package com.credorax.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ValidationErrorResponse extends BaseResponse {

    private Map<String, String> errors = new HashMap<>();
}
