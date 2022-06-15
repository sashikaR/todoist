package com.assurity.api.test.functions;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ServiceOperations {
    private ServiceOperations() {
    }

    public static Response getData(RequestSpecification payload, String baseURI) {
        log.info("Get Request Triggered for : " + baseURI );

        Response apiResponse = payload.get(baseURI );
        log.debug("API Response \n : " + apiResponse.prettyPrint() );

        return apiResponse;
    }


}
