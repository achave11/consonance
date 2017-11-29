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


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * Ga4ghWesWorkflowTypeVersion
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-11-07T13:18:23.936-08:00")
public class Ga4ghWesWorkflowTypeVersion   {
  @JsonProperty("workflow_type_version")
  private List<String> workflowTypeVersion = null;

  public Ga4ghWesWorkflowTypeVersion workflowTypeVersion(List<String> workflowTypeVersion) {
    this.workflowTypeVersion = workflowTypeVersion;
    return this;
  }

  public Ga4ghWesWorkflowTypeVersion addWorkflowTypeVersionItem(String workflowTypeVersionItem) {
    if (this.workflowTypeVersion == null) {
      this.workflowTypeVersion = new ArrayList<String>();
    }
    this.workflowTypeVersion.add(workflowTypeVersionItem);
    return this;
  }

  /**
   * Get workflowTypeVersion
   * @return workflowTypeVersion
   **/
  @JsonProperty("workflow_type_version")
  @ApiModelProperty(value = "")
  public List<String> getWorkflowTypeVersion() {
    return workflowTypeVersion;
  }

  public void setWorkflowTypeVersion(List<String> workflowTypeVersion) {
    this.workflowTypeVersion = workflowTypeVersion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghWesWorkflowTypeVersion ga4ghWesWorkflowTypeVersion = (Ga4ghWesWorkflowTypeVersion) o;
    return Objects.equals(this.workflowTypeVersion, ga4ghWesWorkflowTypeVersion.workflowTypeVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workflowTypeVersion);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghWesWorkflowTypeVersion {\n");
    
    sb.append("    workflowTypeVersion: ").append(toIndentedString(workflowTypeVersion)).append("\n");
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

