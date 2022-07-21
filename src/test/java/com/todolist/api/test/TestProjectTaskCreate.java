package com.todolist.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.api.test.dataobjects.Project;
import com.todolist.api.test.dataobjects.Task;
import com.todolist.api.test.functions.HeaderRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

import static com.todolist.api.test.data.BaseData.*;
import static com.todolist.api.test.data.BaseData.Accept.ACCEPT_JSON;
import static com.todolist.api.test.data.BaseData.Content.CONTENT_JSON;
import static com.todolist.api.test.functions.ServiceOperations.deleteData;
import static com.todolist.api.test.functions.ServiceOperations.sendData;
import static com.todolist.api.test.utility.PropertyReader.propertyReader;

public class TestProjectTaskCreate extends TestBase{
    private boolean isProjectCreated = false;
    private long projectId;

    @BeforeClass
    private void createProject() throws JsonProcessingException {
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
    }

    @Test(testName = "CREATE-TASK_V1_API_001", description = "Validate successful task creation ", priority = 1, suiteName = "Regression")
    public void createTaskSuccessfully() throws JsonProcessingException {

        Properties testProperties = propertyReader();
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        SoftAssert softAssert = new SoftAssert();

        HeaderRequest headerRequest = new HeaderRequest();
        headerRequest.setHeaders(CONTENT_TYPE, CONTENT_JSON.toString());
        headerRequest.setHeaders(ACCEPT, ACCEPT_JSON.toString());
        headerRequest.setHeaders(AUTHORIZATION,testProperties.getProperty("api-auth-token"));
        RequestSpecification httpRequest = headerRequest.createHeaderRequest();

        Task task = new Task();
        task.setContent("Buy Milk");
        task.setProject_id(projectId);
        int[] ids = new int[1];
        task.setLabel_ids(ids);
        task.setSection_id(7025);
        task.setPriority(1);

        Response createTaskResponse = sendData(httpRequest.body(task), testProperties.getProperty("todo-list-create-task-url"));
        softAssert.assertEquals(createTaskResponse.getStatusCode(), 200);
        Task createdTask = objectMapper.readValue(createTaskResponse.getBody().asString(), Task.class);

        softAssert.assertEquals(createdTask.getContent(),task.getContent(),"Task Content is invalid..!");
        softAssert.assertAll();
    }

    @AfterClass
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
