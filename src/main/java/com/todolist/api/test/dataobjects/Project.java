package com.todolist.api.test.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    public long id;
    public int order;
    public int color;
    public String name;
    public int comment_count;
    public boolean shared;
    public boolean favorite;
    public int sync_id;
    public String url;
}
