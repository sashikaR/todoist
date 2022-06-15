package com.assurity.api.test.dataobjects.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fees {
    @JsonProperty("Bundle")
    public int bundle;

    @JsonProperty("EndDate")
    public double endDate;

    @JsonProperty("Feature")
    public int feature;

    @JsonProperty("Gallery")
    public int gallery;

    @JsonProperty("Listing")
    public int listing;

    @JsonProperty("Reserve")
    public double reserve;

    @JsonProperty("Subtitle")
    public double subtitle;

    @JsonProperty("TenDays")
    public double tenDays;

    @JsonProperty("ListingFeeTiers")
    public ArrayList<ListingFeeTier> listingFeeTiers;

    @JsonProperty("SecondCategory")
    public double secondCategory;
}
