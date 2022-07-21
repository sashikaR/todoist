package com.todolist.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.api.test.dataobjects.Project;
import com.todolist.api.test.functions.HeaderRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

import static com.todolist.api.test.data.BaseData.*;
import static com.todolist.api.test.data.BaseData.Accept.ACCEPT_JSON;
import static com.todolist.api.test.data.BaseData.Content.CONTENT_JSON;
import static com.todolist.api.test.functions.ServiceOperations.deleteData;
import static com.todolist.api.test.functions.ServiceOperations.sendData;
import static com.todolist.api.test.utility.PropertyReader.propertyReader;

public class TestProjectCreate extends TestBase{

    private boolean isProjectCreated = false;
    private long projectId;

    @Test(testName = "CREATE-PROJECT_V1_API_001", description = "Validate successful project creation only with name", priority = 1, suiteName = "Regression")
    public void createProjectSuccessfully() throws JsonProcessingException {

        Properties testProperties = propertyReader();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        SoftAssert softAssert = new SoftAssert();

        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setHeaders(CONTENT_TYPE, CONTENT_JSON.toString());
        headerRequest.setHeaders(ACCEPT, ACCEPT_JSON.toString());
        headerRequest.setHeaders(AUTHORIZATION,testProperties.getProperty("api-auth-token"));
        RequestSpecification httpRequest = headerRequest.createHeaderRequest();

        Project project = new Project();
        project.setName("TestProjectName");

        Response createProjectResponse = sendData(httpRequest.body(project), testProperties.getProperty("todo-list-create-project-url"));
        softAssert.assertEquals(createProjectResponse.getStatusCode(), 200);
        Project createdProject = objectMapper.readValue(createProjectResponse.getBody().asString(), Project.class);
        isProjectCreated = true;
        projectId = createdProject.getId();

        softAssert.assertEquals(createdProject.getName(),project.getName(),"Project Name is invalid..!");
        softAssert.assertAll();

    }

    @Test(testName = "CREATE-PROJECT_V1_API_002", description = "Validate project creation with an empty name", priority = 2, suiteName = "Regression")
    public void createProjectWithEmptyName() throws JsonProcessingException {

        Properties testProperties = propertyReader();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        SoftAssert softAssert = new SoftAssert();

        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setHeaders(CONTENT_TYPE, CONTENT_JSON.toString());
        headerRequest.setHeaders(ACCEPT, ACCEPT_JSON.toString());
        headerRequest.setHeaders(AUTHORIZATION,testProperties.getProperty("api-auth-token"));
        RequestSpecification httpRequest = headerRequest.createHeaderRequest();

        Project project = new Project();
        project.setName("  ");

        Response createProjectResponse = sendData(httpRequest.body(project), testProperties.getProperty("todo-list-create-project-url"));
        softAssert.assertEquals(createProjectResponse.getStatusCode(), 200);
        Project createdProject = objectMapper.readValue(createProjectResponse.getBody().asString(), Project.class);
        isProjectCreated = true;
        projectId = createdProject.getId();

        softAssert.assertEquals(createdProject.getName(),"Undefined","Project Name is invalid..!");
        softAssert.assertAll();
    }

    @Test(testName = "CREATE-PROJECT_V1_API_003", description = "Validate project creation with an empty payload", priority = 2, suiteName = "Regression")
    public void createProjectWithEmptyPayload() throws JsonProcessingException {

        Properties testProperties = propertyReader();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        SoftAssert softAssert = new SoftAssert();

        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setHeaders(CONTENT_TYPE, CONTENT_JSON.toString());
        headerRequest.setHeaders(ACCEPT, ACCEPT_JSON.toString());
        headerRequest.setHeaders(AUTHORIZATION,testProperties.getProperty("api-auth-token"));
        RequestSpecification httpRequest = headerRequest.createHeaderRequest();

        Project project = new Project();

        Response createProjectResponse = sendData(httpRequest.body(project), testProperties.getProperty("todo-list-create-project-url"));
        softAssert.assertEquals(createProjectResponse.getStatusCode(), 400);
        softAssert.assertAll();
    }

    @AfterTest
    private void deleteProject(){
        if(isProjectCreated == true){
            Properties testProperties = propertyReader();
            SoftAssert softAssert = new SoftAssert();

            HeaderRequest headerRequest = new HeaderRequest();
            headerRequest.setHeaders(CONTENT_TYPE, CONTENT_JSON.toString());
            headerRequest.setHeaders(ACCEPT, ACCEPT_JSON.toString());
            headerRequest.setHeaders(AUTHORIZATION,testProperties.getProperty("api-auth-token"));
            RequestSpecification httpRequest = headerRequest.createHeaderRequest();

            Response createProjectResponse = deleteData(httpRequest.body(""), testProperties.getProperty("todo-list-delete-project-url"),String.valueOf(projectId));
            softAssert.assertEquals(createProjectResponse.getStatusCode(), 204);
            softAssert.assertAll();
        }

    }

}
