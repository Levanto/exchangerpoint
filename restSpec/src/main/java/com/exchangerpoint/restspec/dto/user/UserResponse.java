/*
 * USER
 * ExchangerPoint Crypto Exchange Plateform - USER Specification
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.exchangerpoint.restspec.dto.user;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * UserResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-01T06:52:50.420Z")
public class UserResponse {
  @SerializedName("userName")
  private String userName = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("phone")
  private String phone = null;

  


  @SerializedName("country")
  private String country = null;

  

  @SerializedName("lastLoginIp")
  private String lastLoginIp = null;

  @SerializedName("lastLoginTime")
  private String lastLoginTime = null;

  @SerializedName("failedLoginAttempts")
  private String failedLoginAttempts = null;

	
  @SerializedName("role")
  private String role;
  
  @SerializedName("comment")
  private String comment = null;

  @SerializedName("createdDate")
  private String createdDate = null;

  @SerializedName("modifiedDate")
  private String modifiedDate = null;

  @SerializedName("accountStatus")
  private String accountStatus = null;

  public UserResponse userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Get userName
   * @return userName
  **/
  @ApiModelProperty(value = "")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserResponse name(String name) {
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

  public UserResponse email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserResponse phone(String phone) {
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @ApiModelProperty(value = "")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public UserResponse country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(value = "")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  

  

  public UserResponse lastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
    return this;
  }

   /**
   * Get lastLoginIp
   * @return lastLoginIp
  **/
  @ApiModelProperty(value = "")
  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public UserResponse lastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
    return this;
  }

   /**
   * Get lastLoginTime
   * @return lastLoginTime
  **/
  @ApiModelProperty(value = "")
  public String getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(String lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public UserResponse failedLoginAttempts(String failedLoginAttempts) {
    this.failedLoginAttempts = failedLoginAttempts;
    return this;
  }

   /**
   * Get failedLoginAttempts
   * @return failedLoginAttempts
  **/
  @ApiModelProperty(value = "")
  public String getFailedLoginAttempts() {
    return failedLoginAttempts;
  }

  public void setFailedLoginAttempts(String failedLoginAttempts) {
    this.failedLoginAttempts = failedLoginAttempts;
  }

  public UserResponse role(String role) {
	    this.role = role;
	    return this;
	  }

	   /**
	   * Get role
	   * @return role
	  **/
	  @ApiModelProperty(value = "")
	  public String getRole() {
	    return role;
	  }

	  public void setRole(String role) {
	    this.role = role;
	  }
	  
  public UserResponse comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * Get comment
   * @return comment
  **/
  @ApiModelProperty(value = "")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  


  public UserResponse createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

   /**
   * Get createdDate
   * @return createdDate
  **/
  @ApiModelProperty(value = "")
  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public UserResponse modifiedDate(String modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

   /**
   * Get modifiedDate
   * @return modifiedDate
  **/
  @ApiModelProperty(value = "")
  public String getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(String modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public UserResponse accountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
    return this;
  }

   /**
   * Get accountStatus
   * @return accountStatus
  **/
  @ApiModelProperty(value = "")
  public String getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(String accountStatus) {
    this.accountStatus = accountStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResponse UserResponse = (UserResponse) o;
    return Objects.equals(this.userName, UserResponse.userName) &&
        Objects.equals(this.name, UserResponse.name) &&
        Objects.equals(this.email, UserResponse.email) &&
        Objects.equals(this.phone, UserResponse.phone) &&
        Objects.equals(this.country, UserResponse.country) &&
        Objects.equals(this.lastLoginIp, UserResponse.lastLoginIp) &&
        Objects.equals(this.lastLoginTime, UserResponse.lastLoginTime) &&
        Objects.equals(this.failedLoginAttempts, UserResponse.failedLoginAttempts) &&
        Objects.equals(this.role, UserResponse.role) &&
        Objects.equals(this.comment, UserResponse.comment) &&
        Objects.equals(this.createdDate, UserResponse.createdDate) &&
        Objects.equals(this.modifiedDate, UserResponse.modifiedDate) &&
        Objects.equals(this.accountStatus, UserResponse.accountStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, name, email, phone, country, lastLoginIp, lastLoginTime, failedLoginAttempts, role,  comment, createdDate, modifiedDate, accountStatus);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class s {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    lastLoginIp: ").append(toIndentedString(lastLoginIp)).append("\n");
    sb.append("    lastLoginTime: ").append(toIndentedString(lastLoginTime)).append("\n");
    sb.append("    failedLoginAttempts: ").append(toIndentedString(failedLoginAttempts)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
    sb.append("    accountStatus: ").append(toIndentedString(accountStatus)).append("\n");
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

