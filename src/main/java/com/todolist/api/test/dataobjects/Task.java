package com.todolist.api.test.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    public long id;
    public String content;
    public String description;
    public int comment_count;
    public boolean completed;
    public int order;
    public int priority;
    public long project_id;
    public int [] label_ids;
    public int section_id;
    public int parent_id;
    public int creator;
    public Date created;
    public Object assigner;
    public String url;
    public String due_string;
}
