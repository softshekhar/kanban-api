package edu.shekhar.parsnip.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {

    @JsonProperty("Unstarted")
    UN_STARTED,

    @JsonProperty("In Progress")
    IN_PROGRESS,

    @JsonProperty("Completed")
    COMPLETED;


}
