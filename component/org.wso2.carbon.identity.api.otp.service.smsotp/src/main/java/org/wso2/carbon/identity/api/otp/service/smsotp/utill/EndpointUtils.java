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

package org.wso2.carbon.identity.api.otp.service.smsotp.utill;

import org.apache.commons.logging.Log;
import org.apache.log4j.MDC;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.Error;
import org.wso2.carbon.identity.api.otp.service.smsotp.exception.BadRequestException;
import org.wso2.carbon.identity.api.otp.service.smsotp.exception.ConflictRequestException;
import org.wso2.carbon.identity.api.otp.service.smsotp.exception.ForbiddenException;
import org.wso2.carbon.identity.api.otp.service.smsotp.exception.InternalServerErrorException;
import org.wso2.carbon.identity.api.otp.service.smsotp.exception.NotFoundException;
import org.wso2.carbon.identity.smsotp.common.SMSOTPService;
import org.wso2.carbon.identity.smsotp.common.constant.Constants;
import org.wso2.carbon.identity.smsotp.common.exception.SMSOTPClientException;
import org.wso2.carbon.identity.smsotp.common.exception.SMSOTPException;

import java.util.UUID;
import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.api.otp.service.smsotp.constant.Constants.CORRELATION_ID_MDC;

/**
 * This class provides util functions for SMS OTP REST APIs.
 */
public class EndpointUtils {

    public static SMSOTPService getSMSOTPService() {

        return (SMSOTPService) PrivilegedCarbonContext.getThreadLocalCarbonContext().
                getOSGiService(SMSOTPService.class, null);
    }

    private static void logDebug(Log log, Throwable throwable) {

        if (log.isDebugEnabled()) {
            log.debug(Response.Status.BAD_REQUEST, throwable);
        }
    }

    private static void logError(Log log, Throwable throwable) {

        log.error(throwable.getMessage(), throwable);
    }

    public static String getCorrelation() {

        String ref;
        if (isCorrelationIDPresent()) {
            ref = MDC.get(CORRELATION_ID_MDC).toString();
        } else {
            ref = UUID.randomUUID().toString();
        }
        return ref;
    }

    public static boolean isCorrelationIDPresent() {

        return MDC.get(CORRELATION_ID_MDC) != null;
    }

    private static Error getError(String message, String description, String code) {

        Error errorDTO = new Error();
        errorDTO.setCode(code);
        errorDTO.setMessage(message);
        errorDTO.setDescription(description);
        errorDTO.setTraceId(getCorrelation());
        return errorDTO;
    }

    public static Response handleBadRequestResponse(SMSOTPClientException e, Log log) {

        if (isNotFoundError(e)) {
            throw buildNotFoundRequestException(e.getDescription(), e.getMessage(), e.getErrorCode(), log, e);
        }

        if (isConflictError(e)) {
            throw buildConflictRequestException(e.getDescription(), e.getMessage(), e.getErrorCode(), log, e);
        }

        if (isForbiddenError(e)) {
            throw buildForbiddenException(e.getDescription(), e.getMessage(), e.getErrorCode(), log, e);
        }
        throw buildBadRequestException(e.getDescription(), e.getMessage(), e.getErrorCode(), log, e);
    }

    public static Response handleServerErrorResponse(SMSOTPException e, Log log) {

        throw buildInternalServerErrorException(e.getErrorCode(), log, e);
    }

    public static Response handleUnexpectedServerError(Throwable e, Log log) {

        throw buildInternalServerErrorException(Constants.ErrorMessage.SERVER_UNEXPECTED_ERROR.getCode(), log, e);
    }

    private static boolean isNotFoundError(SMSOTPClientException e) {

        return Constants.isNotFoundError(e.getErrorCode());
    }

    private static boolean isConflictError(SMSOTPClientException e) {

        return Constants.isConflictError(e.getErrorCode());
    }

    private static boolean isForbiddenError(SMSOTPClientException e) {

        return Constants.isForbiddenError(e.getErrorCode());
    }

    public static NotFoundException buildNotFoundRequestException(String description, String message, String code,
                                                                  Log log, Throwable e) {

        Error errorDTO = getError(message, description, code);
        logDebug(log, e);
        return new NotFoundException(errorDTO);
    }

    public static ForbiddenException buildForbiddenException(String description, String message, String code, Log log,
                                                             Throwable e) {

        Error errorDTO = getError(message, description, code);
        logDebug(log, e);
        return new ForbiddenException(errorDTO);
    }

    public static BadRequestException buildBadRequestException(String description, String message, String code, Log log,
                                                               Throwable e) {

        Error errorDTO = getError(message, description, code);
        logDebug(log, e);
        return new BadRequestException(errorDTO);
    }

    public static InternalServerErrorException buildInternalServerErrorException(String code, Log log, Throwable e) {

        Error errorDTO = getError(Response.Status.INTERNAL_SERVER_ERROR.toString(),
                Response.Status.INTERNAL_SERVER_ERROR.toString(), code);
        logError(log, e);
        return new InternalServerErrorException(errorDTO);
    }

    public static ConflictRequestException buildConflictRequestException(String description, String message,
                                                                         String code, Log log, Throwable e) {

        Error errorDTO = getError(message, description, code);
        logDebug(log, e);
        return new ConflictRequestException(errorDTO);
    }
}
