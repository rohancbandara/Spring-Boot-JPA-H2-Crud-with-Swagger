package com.rcb.utill;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================================
 * A Common response with payload/status used as return results
 * of Controller/Service methods
 * ==============================================================
 **/
public class CommonResponse {

    private final List<Object> payload = null;
    private final List<String> errorMessages = new ArrayList<>();
    private final boolean status = false;
}
