package com.suivenergies.app.service.dto.api;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize
@JsonPropertyOrder({ "total", "results" })
public class DpeApi {
    @JsonProperty("total")
    private Long total;

    @JsonProperty("results")
    private List<Dpe> dpes = null;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public DpeApi() {}

    /**
     *
     * @param total
     * @param results
     */
    public DpeApi(Long total, List<Dpe> dpes) {
        super();
        this.total = total;
        this.dpes = dpes;
    }

    @JsonProperty("total")
    public Long getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Long total) {
        this.total = total;
    }

    @JsonProperty("results")
    public List<Dpe> getDpes() {
        return dpes;
    }

    @JsonProperty("results")
    public void setDpes(List<Dpe> dpes) {
        this.dpes = dpes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
