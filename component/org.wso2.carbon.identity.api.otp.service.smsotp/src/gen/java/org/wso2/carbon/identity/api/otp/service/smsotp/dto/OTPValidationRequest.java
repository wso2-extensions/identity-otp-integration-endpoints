/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
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

package org.wso2.carbon.identity.api.otp.service.smsotp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class OTPValidationRequest  {
  
    private String userId;
    private String transactionId;
    private String smsOTP;

    /**
    * SCIM ID of the user
    **/
    public OTPValidationRequest userId(String userId) {

        this.userId = userId;
        return this;
    }
    
    @ApiModelProperty(example = "8b1cc9c4-b671-448a-a334-5afe838a4a3b", value = "SCIM ID of the user")
    @JsonProperty("userId")
    @Valid
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
    * Unique identifier of the generated OTP
    **/
    public OTPValidationRequest transactionId(String transactionId) {

        this.transactionId = transactionId;
        return this;
    }
    
    @ApiModelProperty(value = "Unique identifier of the generated OTP")
    @JsonProperty("transactionId")
    @Valid
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
    * SMS OTP
    **/
    public OTPValidationRequest smsOTP(String smsOTP) {

        this.smsOTP = smsOTP;
        return this;
    }
    
    @ApiModelProperty(value = "SMS OTP")
    @JsonProperty("smsOTP")
    @Valid
    public String getSmsOTP() {
        return smsOTP;
    }
    public void setSmsOTP(String smsOTP) {
        this.smsOTP = smsOTP;
    }



    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OTPValidationRequest otPValidationRequest = (OTPValidationRequest) o;
        return Objects.equals(this.userId, otPValidationRequest.userId) &&
            Objects.equals(this.transactionId, otPValidationRequest.transactionId) &&
            Objects.equals(this.smsOTP, otPValidationRequest.smsOTP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, transactionId, smsOTP);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class OTPValidationRequest {\n");
        
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
        sb.append("    smsOTP: ").append(toIndentedString(smsOTP)).append("\n");
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

