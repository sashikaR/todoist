package com.assurity.api.test.dataobjects.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarbonCredits {
    @JsonProperty("CategoryId")
    public int categoryId;

    @JsonProperty("Name")
    public String name;

    @JsonProperty("Path")
    public String path;

    @JsonProperty("CanListAuctions")
    public boolean canListAuctions;

    @JsonProperty("CanListClassifieds")
    public boolean canListClassifieds;

    @JsonProperty("CanRelist")
    public boolean canRelist;

    @JsonProperty("LegalNotice")
    public String legalNotice;

    @JsonProperty("DefaultDuration")
    public int defaultDuration;

    @JsonProperty("AllowedDurations")
    public ArrayList<Integer> allowedDurations;

    @JsonProperty("Fees")
    public Fees fees;

    @JsonProperty("FreePhotoCount")
    public int freePhotoCount;

    @JsonProperty("MaximumPhotoCount")
    public int maximumPhotoCount;

    @JsonProperty("IsFreeToRelist")
    public boolean isFreeToRelist;

    @JsonProperty("Promotions")
    public ArrayList<Promotion> promotions;

    @JsonProperty("EmbeddedContentOptions")
    public ArrayList<Object> embeddedContentOptions;

    @JsonProperty("MaximumTitleLength")
    public int maximumTitleLength;

    @JsonProperty("AreaOfBusiness")
    public int areaOfBusiness;

    @JsonProperty("DefaultRelistDuration")
    public int defaultRelistDuration;

    @JsonProperty("CanUseCounterOffers")
    public boolean canUseCounterOffers;

}
