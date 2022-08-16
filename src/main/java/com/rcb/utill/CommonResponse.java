package com.rcb.utill;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================================
 * A Common response with payload/status used as return results
 * of Controller/Service methods
 * ==============================================================
 **/
@Getter
@Setter
public class CommonResponse {

    private List<Object> payload = null;
    private List<String> errorMessages = new ArrayList<>();
    private boolean status = false;
}
