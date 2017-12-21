/*
 * workflow_execution.proto
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: version not set
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.wes.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets ga4gh_wes_state
 */
public enum Ga4ghWesState {
  
  UNKNOWN("Unknown"),
  
  QUEUED("Queued"),
  
  RUNNING("Running"),
  
  PAUSED("Paused"), // TODO: Not available yet.
  
  COMPLETE("Complete"),
  
  ERROR("Error"),
  
  SYSTEMERROR("SystemError"),
  
  CANCELED("Canceled"),  // TODO: Added feature
  
  INITIALIZING("Initializing");

  private String value;

  Ga4ghWesState(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static Ga4ghWesState fromValue(String text) {
    for (Ga4ghWesState b : Ga4ghWesState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

