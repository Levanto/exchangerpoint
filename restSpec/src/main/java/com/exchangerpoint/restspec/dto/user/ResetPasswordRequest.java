
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
 * PasswordResetRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-05-01T06:52:50.420Z")
public class ResetPasswordRequest {
  

  @SerializedName("email")
  private String email = null;
  
  @SerializedName("verificationCode")
  private String verificationCode = null;

  @SerializedName("newPassword")
  private String newPassword = null;


  public ResetPasswordRequest email(String email) {
    this.email = email;
    return this;
  }
  
  
  public ResetPasswordRequest verificationCode(String verificationCode) {
	    this.verificationCode = verificationCode;
	    return this;
	  }

   /**
   * User new email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "User new email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
  /**
   * Email Verification Code
   * @return emailVerificationCode
  **/
  @ApiModelProperty(required = true, value = "Email Verification Code")
  public String getVerificationCode() {
    return verificationCode;
  }

  public void setVerificationCode(String verificationCode) {
    this.verificationCode = verificationCode;
  }

  
  public ResetPasswordRequest newPassword(String newPassword) {
	    this.newPassword = newPassword;
	    return this;
	  }

	   /**
	   * User new password
	   * @return newPassword
	  **/
	  @ApiModelProperty(required = true, value = "User new password")
	  public String getNewPassword() {
	    return newPassword;
	  }

	  public void setNewPassword(String newPassword) {
	    this.newPassword = newPassword;
	  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResetPasswordRequest passwordResetRequest = (ResetPasswordRequest) o;
    return 
    		Objects.equals(this.verificationCode, passwordResetRequest.verificationCode) &&
    		Objects.equals(this.newPassword, passwordResetRequest.newPassword) &&
        Objects.equals(this.email, passwordResetRequest.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, verificationCode, newPassword);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordResetRequest {\n");
    
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("    verificationCode: ").append(toIndentedString(verificationCode)).append("\n");
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



