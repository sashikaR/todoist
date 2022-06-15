package com.assurity.api.test.v1;

import com.assurity.api.test.dataobjects.v1.CarbonCredits;
import com.assurity.api.test.functions.HeaderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static com.assurity.api.test.functions.ServiceOperations.getData;

public class CarbonCreditsV1Test {

    @Test(testName = "CARBONCREDIT_V1_API_001", description = "Get And Validate Carbon Credits V1 API Response", priority = 1, suiteName = "Regression")
    private void getAndValidateCarbonCreditsDataRequest() throws JsonProcessingException, InterruptedException {

         String apiUrl ="http://api.tmsandbox.co.nz/v1/Categories/6327/Details.json";

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        SoftAssert softAssert = new SoftAssert();


        Map<String,String> queryParam=new HashMap<String, String>();
        queryParam.put("catalogue","false");

        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setHeaders("Content-Type", "application/json");
        headerRequest.setHeaders("Accept", "application/json");
        RequestSpecification httpRequest = headerRequest.createHeaderRequest();

        Response getResponseCarbonCredit = getData(httpRequest.body("").params(queryParam), apiUrl);
        softAssert.assertEquals(getResponseCarbonCredit.getStatusCode(), 200);

        CarbonCredits getCarbonCreditApiResponse = objectMapper.readValue(getResponseCarbonCredit.getBody().asString(), CarbonCredits.class);

        // Acceptance Criteria 1
        softAssert.assertEquals(getCarbonCreditApiResponse.getName(), "Carbon credits","Invalid name has found for Carbon credits -"+getCarbonCreditApiResponse.getName());
        // Acceptance Criteria 2
        softAssert.assertTrue(getCarbonCreditApiResponse.canRelist, "Invalid condition has found for CanRelist attribute");

        boolean isFound = false;
        for (int i=0; i<getCarbonCreditApiResponse.getPromotions().size(); i++){
            String promotionName = getCarbonCreditApiResponse.getPromotions().get(i).name;
            if(promotionName.equals("Gallery")){
                isFound = true;
                // Acceptance Criteria 3
                softAssert.assertEquals(getCarbonCreditApiResponse.getPromotions().get(i).description, "Good position in category","Invalid Description has found for Promotion of Gallery");
            }
        }

        if(!isFound){
            softAssert.assertTrue(isFound , "The Promotion of Gallery is not found...!");
        }

        softAssert.assertAll();
    }
}
