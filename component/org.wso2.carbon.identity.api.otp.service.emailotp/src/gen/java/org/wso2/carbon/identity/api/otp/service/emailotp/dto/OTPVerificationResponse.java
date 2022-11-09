/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.otp.service.emailotp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPVerificationFailureReason;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class OTPVerificationResponse  {
  
    private Boolean isValid;
    private String userId;
    private OTPVerificationFailureReason failureReason;

    /**
    * EMAIL OTP verified successfully
    **/
    public OTPVerificationResponse isValid(Boolean isValid) {

        this.isValid = isValid;
        return this;
    }
    
    @ApiModelProperty(required = true, value = "EMAIL OTP verified successfully")
    @JsonProperty("isValid")
    @Valid
    @NotNull(message = "Property isValid cannot be null.")

    public Boolean getIsValid() {
        return isValid;
    }
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
    * SCIM ID of the user which the token issued and verified
    **/
    public OTPVerificationResponse userId(String userId) {

        this.userId = userId;
        return this;
    }
    
    @ApiModelProperty(example = "8b1cc9c4-b671-448a-a334-5afe838a4a3b", required = true, value = "SCIM ID of the user which the token issued and verified")
    @JsonProperty("userId")
    @Valid
    @NotNull(message = "Property userId cannot be null.")

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
    **/
    public OTPVerificationResponse failureReason(OTPVerificationFailureReason failureReason) {

        this.failureReason = failureReason;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("failureReason")
    @Valid
    public OTPVerificationFailureReason getFailureReason() {
        return failureReason;
    }
    public void setFailureReason(OTPVerificationFailureReason failureReason) {
        this.failureReason = failureReason;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OTPVerificationResponse otPVerificationResponse = (OTPVerificationResponse) o;
        return Objects.equals(this.isValid, otPVerificationResponse.isValid) &&
            Objects.equals(this.userId, otPVerificationResponse.userId) &&
            Objects.equals(this.failureReason, otPVerificationResponse.failureReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValid, userId, failureReason);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class OTPVerificationResponse {\n");
        
        sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    failureReason: ").append(toIndentedString(failureReason)).append("\n");
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
        return o.toString().replace("\n", "\n");
    }
}

