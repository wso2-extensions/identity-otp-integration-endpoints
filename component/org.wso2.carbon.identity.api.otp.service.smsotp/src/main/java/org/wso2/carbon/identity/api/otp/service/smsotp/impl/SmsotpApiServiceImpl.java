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

package org.wso2.carbon.identity.api.otp.service.smsotp.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.api.otp.service.smsotp.SmsotpApiService;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPGenerateResponse;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPGenerationRequest;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPValidationFailureReason;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPValidationRequest;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPValidationResponse;
import org.wso2.carbon.identity.api.otp.service.smsotp.utill.EndpointUtils;
import org.wso2.carbon.identity.smsotp.common.dto.FailureReasonDTO;
import org.wso2.carbon.identity.smsotp.common.dto.GenerationResponseDTO;
import org.wso2.carbon.identity.smsotp.common.dto.ValidationResponseDTO;
import org.wso2.carbon.identity.smsotp.common.exception.SMSOTPClientException;
import org.wso2.carbon.identity.smsotp.common.exception.SMSOTPException;

import javax.ws.rs.core.Response;

/**
 * This class implements the service layer for
 * {@link org.wso2.carbon.identity.api.otp.service.smsotp.SmsotpApi}.
 */
public class SmsotpApiServiceImpl implements SmsotpApiService {

    private static final Log log = LogFactory.getLog(SmsotpApiServiceImpl.class);

    @Override
    public Response smsotpGeneratePost(OTPGenerationRequest otpGenerationRequest) {

        String userId = StringUtils.trim(otpGenerationRequest.getUserId());
        try {
            GenerationResponseDTO responseDTO = EndpointUtils.getSMSOTPService().generateSMSOTP(userId);
            OTPGenerateResponse response = new OTPGenerateResponse()
                    .transactionId(responseDTO.getTransactionId())
                    .smsOTP(responseDTO.getSmsOTP());
            return Response.ok(response).build();
        } catch (SMSOTPClientException e) {
            return EndpointUtils.handleBadRequestResponse(e, log);
        } catch (SMSOTPException e) {
            return EndpointUtils.handleServerErrorResponse(e, log);
        } catch (Throwable e) {
            return EndpointUtils.handleUnexpectedServerError(e, log);
        }
    }

    @Override
    public Response smsotpValidatePost(OTPValidationRequest otpValidationRequest) {

        String transactionId = StringUtils.trim(otpValidationRequest.getTransactionId());
        String userId = StringUtils.trim(otpValidationRequest.getUserId());
        String smsOtp = StringUtils.trim(otpValidationRequest.getSmsOTP());
        try {
            ValidationResponseDTO responseDTO = EndpointUtils.getSMSOTPService().validateSMSOTP(
                    transactionId, userId, smsOtp);
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
        } catch (SMSOTPClientException e) {
            return EndpointUtils.handleBadRequestResponse(e, log);
        } catch (SMSOTPException e) {
            return EndpointUtils.handleServerErrorResponse(e, log);
        } catch (Throwable e) {
            return EndpointUtils.handleUnexpectedServerError(e, log);
        }
    }
}
