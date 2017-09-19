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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.*;

/**
 * Ga4ghWesWorkflowRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-09-15T17:06:31.319-07:00")
public class Ga4ghWesWorkflowRequest   {
  @JsonProperty("workflow_descriptor")
  private String workflowDescriptor = null;

  @JsonProperty("workflow_params")
  private String workflowParams = null;

  @JsonProperty("workflow_type")
  private String workflowType = null;

  @JsonProperty("workflow_type_version")
  private String workflowTypeVersion = null;

  @JsonProperty("key_values")
  private Map<String, String> keyValues = null;

  public Ga4ghWesWorkflowRequest workflowDescriptor(String workflowDescriptor) {
    this.workflowDescriptor = workflowDescriptor;
    return this;
  }

  /**
   * Get workflowDescriptor
   * @return workflowDescriptor
   **/
  @JsonProperty("workflow_descriptor")
  @ApiModelProperty(value = "")
  public String getWorkflowDescriptor() {
    return workflowDescriptor;
  }

  public void setWorkflowDescriptor(String workflowDescriptor) {
    this.workflowDescriptor = workflowDescriptor;
  }

  public Ga4ghWesWorkflowRequest workflowParams(String workflowParams) {
    this.workflowParams = workflowParams;
    return this;
  }

  /**
   * Get workflowParams
   * @return workflowParams
   **/
  @JsonProperty("workflow_params")
  @ApiModelProperty(value = "")
  public String getWorkflowParams() {
    return workflowParams;
  }

  public void setWorkflowParams(String workflowParams) {
    this.workflowParams = workflowParams;
  }

  public Ga4ghWesWorkflowRequest workflowType(String workflowType) {
    this.workflowType = workflowType;
    return this;
  }

  /**
   * Get workflowType
   * @return workflowType
   **/
  @JsonProperty("workflow_type")
  @ApiModelProperty(value = "")
  public String getWorkflowType() {
    return workflowType;
  }

  public void setWorkflowType(String workflowType) {
    this.workflowType = workflowType;
  }

  public Ga4ghWesWorkflowRequest workflowTypeVersion(String workflowTypeVersion) {
    this.workflowTypeVersion = workflowTypeVersion;
    return this;
  }

  /**
   * Get workflowTypeVersion
   * @return workflowTypeVersion
   **/
  @JsonProperty("workflow_type_version")
  @ApiModelProperty(value = "")
  public String getWorkflowTypeVersion() {
    return workflowTypeVersion;
  }

  public void setWorkflowTypeVersion(String workflowTypeVersion) {
    this.workflowTypeVersion = workflowTypeVersion;
  }

  public Ga4ghWesWorkflowRequest keyValues(Map<String, String> keyValues) {
    this.keyValues = keyValues;
    return this;
  }

  public Ga4ghWesWorkflowRequest putKeyValuesItem(String key, String keyValuesItem) {
    if (this.keyValues == null) {
      this.keyValues = new HashMap<String, String>();
    }
    this.keyValues.put(key, keyValuesItem);
    return this;
  }

  /**
   * Get keyValues
   * @return keyValues
   **/
  @JsonProperty("key_values")
  @ApiModelProperty(value = "")
  public Map<String, String> getKeyValues() {
    return keyValues;
  }

  public void setKeyValues(Map<String, String> keyValues) {
    this.keyValues = keyValues;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghWesWorkflowRequest ga4ghWesWorkflowRequest = (Ga4ghWesWorkflowRequest) o;
    return Objects.equals(this.workflowDescriptor, ga4ghWesWorkflowRequest.workflowDescriptor) &&
        Objects.equals(this.workflowParams, ga4ghWesWorkflowRequest.workflowParams) &&
        Objects.equals(this.workflowType, ga4ghWesWorkflowRequest.workflowType) &&
        Objects.equals(this.workflowTypeVersion, ga4ghWesWorkflowRequest.workflowTypeVersion) &&
        Objects.equals(this.keyValues, ga4ghWesWorkflowRequest.keyValues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workflowDescriptor, workflowParams, workflowType, workflowTypeVersion, keyValues);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghWesWorkflowRequest {\n");
    
    sb.append("    workflowDescriptor: ").append(toIndentedString(workflowDescriptor)).append("\n");
    sb.append("    workflowParams: ").append(toIndentedString(workflowParams)).append("\n");
    sb.append("    workflowType: ").append(toIndentedString(workflowType)).append("\n");
    sb.append("    workflowTypeVersion: ").append(toIndentedString(workflowTypeVersion)).append("\n");
    sb.append("    keyValues: ").append(toIndentedString(keyValues)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

