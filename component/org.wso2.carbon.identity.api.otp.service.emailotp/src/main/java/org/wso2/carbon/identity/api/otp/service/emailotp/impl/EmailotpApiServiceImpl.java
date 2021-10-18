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

package org.wso2.carbon.identity.api.otp.service.emailotp.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.extension.identity.emailotp.common.dto.FailureReasonDTO;
import org.wso2.carbon.extension.identity.emailotp.common.dto.GenerationResponseDTO;
import org.wso2.carbon.extension.identity.emailotp.common.dto.ValidationResponseDTO;
import org.wso2.carbon.extension.identity.emailotp.common.exception.EmailOtpClientException;
import org.wso2.carbon.extension.identity.emailotp.common.exception.EmailOtpException;
import org.wso2.carbon.identity.api.otp.service.emailotp.EmailotpApiService;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPGenerateResponse;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPGenerationRequest;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPValidationFailureReason;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPValidationRequest;
import org.wso2.carbon.identity.api.otp.service.emailotp.dto.OTPValidationResponse;
import org.wso2.carbon.identity.api.otp.service.emailotp.util.EndpointUtils;

import javax.ws.rs.core.Response;

/**
 * This class implements the service layer for
 * org.wso2.carbon.identity.api.otp.service.emailotp.EmailotpApi.
 */
public class EmailotpApiServiceImpl implements EmailotpApiService {

    private static final Log log = LogFactory.getLog(EmailotpApiServiceImpl.class);

    /**
     * This method is implemented from org.wso2.carbon.identity.api.otp.service.emailotp.EmailotpApi
     * to generate OTP.
     *
     * @param otpGenerationRequest Otp generation request object.
     * @return Response
     */
    @Override
    public Response emailotpGeneratePost(OTPGenerationRequest otpGenerationRequest) {

        String userId = StringUtils.trim(otpGenerationRequest.getUserId());
        try {
            GenerationResponseDTO responseDTO = EndpointUtils.getEmailOTPService().generateEmailOTP(userId);
            OTPGenerateResponse response = new OTPGenerateResponse()
                    .transactionId(responseDTO.getTransactionId())
                    .emailOtp(responseDTO.getEmailOTP());
            return Response.ok(response).build();
        } catch (EmailOtpClientException e) {
            return EndpointUtils.handleBadRequestResponse(e, log);
        } catch (EmailOtpException e) {
            return EndpointUtils.handleServerErrorResponse(e, log);
        } catch (Throwable e) {
            return EndpointUtils.handleUnexpectedServerError(e, log);
        }
    }

    /**
     * This method is implemented from org.wso2.carbon.identity.api.otp.service.emailotp.EmailotpApi
     * to validate OTP.
     *
     * @param otpValidationRequest Otp validation request object.
     * @return Response
     */
    @Override
    public Response emailotpValidatePost(OTPValidationRequest otpValidationRequest) {

        String transactionId = StringUtils.trim(otpValidationRequest.getTransactionId());
        String userId = StringUtils.trim(otpValidationRequest.getUserId());
        String emailOtp = StringUtils.trim(otpValidationRequest.getEmailOtp());
        try {
            ValidationResponseDTO responseDTO = EndpointUtils.getEmailOTPService().validateEmailOTP(
                    transactionId, userId, emailOtp);
            FailureReasonDTO failureReasonDTO = responseDTO.getFailureReason();
            OTPValidationFailureReason failureReason = null;
            if (failureReasonDTO != null) {
                failureReason = new OTPValidationFailureReason()
                        .code(failureReasonDTO.getCode())
                        .message(failureReasonDTO.getMessage())
                        .description(failureReasonDTO.getDescription());
            }
            OTPValidationResponse response = new OTPValidationResponse()
                    .isValid(responseDTO.isValid())
                    .userId(responseDTO.getUserId())
                    .failureReason(failureReason);
            return Response.ok(response).build();
        } catch (EmailOtpClientException e) {
            return EndpointUtils.handleBadRequestResponse(e, log);
        } catch (EmailOtpException e) {
            return EndpointUtils.handleServerErrorResponse(e, log);
        } catch (Throwable e) {
            return EndpointUtils.handleUnexpectedServerError(e, log);
        }
    }
}
