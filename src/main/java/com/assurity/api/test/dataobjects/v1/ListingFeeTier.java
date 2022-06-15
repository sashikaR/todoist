package com.assurity.api.test.dataobjects.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingFeeTier {

    @JsonProperty("MinimumTierPrice")
    public int minimumTierPrice;

    @JsonProperty("FixedFee")
    public int fixedFee;
}
