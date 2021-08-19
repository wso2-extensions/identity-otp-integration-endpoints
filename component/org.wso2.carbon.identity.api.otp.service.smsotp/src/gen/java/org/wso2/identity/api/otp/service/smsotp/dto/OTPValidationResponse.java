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

package org.wso2.identity.api.otp.service.smsotp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import javax.validation.Valid;

/**
 * OTPValidationResponse DTO to get the validate OTP response.
 */
public class OTPValidationResponse {

    private Boolean isValid;
    private String userId;

    /**
     * SMS OTP validated successfully.
     *
     * @param  isValid
     **/
    public OTPValidationResponse isValid(Boolean isValid) {

        this.isValid = isValid;
        return this;
    }

    @ApiModelProperty(value = "SMS OTP validated successfully")
    @JsonProperty("isValid")
    @Valid
    public Boolean getIsValid() {

        return isValid;
    }

    public void setIsValid(Boolean isValid) {

        this.isValid = isValid;
    }

    /**
     * SCIM ID of the user which the token issued and validated
     **/
    public OTPValidationResponse userId(String userId) {

        this.userId = userId;
        return this;
    }

    @ApiModelProperty(example = "8b1cc9c4-b671-448a-a334-5afe838a4a3b", value = "SCIM ID of the user which the token " +
            "issued and validated")
    @JsonProperty("userId")
    @Valid
    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OTPValidationResponse otPValidationResponse = (OTPValidationResponse) o;
        return Objects.equals(this.isValid, otPValidationResponse.isValid) &&
                Objects.equals(this.userId, otPValidationResponse.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isValid, userId);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class OTPValidationResponse {\n");

        sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

