package com.credorax.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private boolean approved;


    public static BaseResponse approved() {
        return new BaseResponse(true);
    }

}


