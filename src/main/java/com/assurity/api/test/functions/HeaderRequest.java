package com.assurity.api.test.functions;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.specification.RequestSpecification;

public class HeaderRequest {

    private final EncoderConfig encoderconfig = new EncoderConfig();
    private final RequestSpecification httpRequest = RestAssured.given().config(RestAssured.config().encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));

    public HeaderRequest() {
        //Bypassing the ssl certificate
        RestAssured.useRelaxedHTTPSValidation();
    }

    public void setHeaders(String header, String headerValue) {
        //null check is not done to validate empty headers
        httpRequest.header(header, headerValue);

    }

    public RequestSpecification createHeaderRequest() {
        return httpRequest;
    }
}
