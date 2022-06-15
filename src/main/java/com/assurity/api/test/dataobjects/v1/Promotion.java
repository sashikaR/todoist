package com.assurity.api.test.dataobjects.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Promotion {
    @JsonProperty("Id")
    public int id;

    @JsonProperty("Name")
    public String name;

    @JsonProperty("Description")
    public String description;

    @JsonProperty("Price")
    public int price;

    @JsonProperty("MinimumPhotoCount")
    public int minimumPhotoCount;

    @JsonProperty("OriginalPrice")
    public int originalPrice;

    @JsonProperty("Recommended")
    public boolean recommended;
}
