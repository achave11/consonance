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


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ga4ghWesLog
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-12-07T15:00:20.150-08:00")
public class Ga4ghWesLog {
  @SerializedName("name")
  private String name = null;

  @SerializedName("cmd")
  private List<String> cmd = null;

  @SerializedName("startTime")
  private String startTime = null;

  @SerializedName("endTime")
  private String endTime = null;

  @SerializedName("stdout")
  private String stdout = null;

  @SerializedName("stderr")
  private String stderr = null;

  @SerializedName("exitCode")
  private Integer exitCode = null;

  public Ga4ghWesLog name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ga4ghWesLog cmd(List<String> cmd) {
    this.cmd = cmd;
    return this;
  }

  public Ga4ghWesLog addCmdItem(String cmdItem) {
    if (this.cmd == null) {
      this.cmd = new ArrayList<String>();
    }
    this.cmd.add(cmdItem);
    return this;
  }

   /**
   * Get cmd
   * @return cmd
  **/
  @ApiModelProperty(value = "")
  public List<String> getCmd() {
    return cmd;
  }

  public void setCmd(List<String> cmd) {
    this.cmd = cmd;
  }

  public Ga4ghWesLog startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public Ga4ghWesLog endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Ga4ghWesLog stdout(String stdout) {
    this.stdout = stdout;
    return this;
  }

   /**
   * Get stdout
   * @return stdout
  **/
  @ApiModelProperty(value = "")
  public String getStdout() {
    return stdout;
  }

  public void setStdout(String stdout) {
    this.stdout = stdout;
  }

  public Ga4ghWesLog stderr(String stderr) {
    this.stderr = stderr;
    return this;
  }

   /**
   * Get stderr
   * @return stderr
  **/
  @ApiModelProperty(value = "")
  public String getStderr() {
    return stderr;
  }

  public void setStderr(String stderr) {
    this.stderr = stderr;
  }

  public Ga4ghWesLog exitCode(Integer exitCode) {
    this.exitCode = exitCode;
    return this;
  }

   /**
   * Get exitCode
   * @return exitCode
  **/
  @ApiModelProperty(value = "")
  public Integer getExitCode() {
    return exitCode;
  }

  public void setExitCode(Integer exitCode) {
    this.exitCode = exitCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghWesLog ga4ghWesLog = (Ga4ghWesLog) o;
    return Objects.equals(this.name, ga4ghWesLog.name) &&
        Objects.equals(this.cmd, ga4ghWesLog.cmd) &&
        Objects.equals(this.startTime, ga4ghWesLog.startTime) &&
        Objects.equals(this.endTime, ga4ghWesLog.endTime) &&
        Objects.equals(this.stdout, ga4ghWesLog.stdout) &&
        Objects.equals(this.stderr, ga4ghWesLog.stderr) &&
        Objects.equals(this.exitCode, ga4ghWesLog.exitCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, cmd, startTime, endTime, stdout, stderr, exitCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghWesLog {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    cmd: ").append(toIndentedString(cmd)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    stdout: ").append(toIndentedString(stdout)).append("\n");
    sb.append("    stderr: ").append(toIndentedString(stderr)).append("\n");
    sb.append("    exitCode: ").append(toIndentedString(exitCode)).append("\n");
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

