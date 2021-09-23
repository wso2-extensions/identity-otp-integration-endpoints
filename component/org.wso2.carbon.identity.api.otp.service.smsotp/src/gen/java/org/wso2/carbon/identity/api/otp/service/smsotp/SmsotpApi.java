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

package org.wso2.carbon.identity.api.otp.service.smsotp;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import java.io.InputStream;
import java.util.List;

import org.wso2.carbon.identity.api.otp.service.smsotp.dto.Error;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPGenerateResponse;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPGenerationRequest;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPValidationRequest;
import org.wso2.carbon.identity.api.otp.service.smsotp.dto.OTPValidationResponse;
import org.wso2.carbon.identity.api.otp.service.smsotp.SmsotpApiService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.annotations.*;

import javax.validation.constraints.*;

@Path("/smsotp")
@Api(description = "The smsotp API")

public class SmsotpApi  {

    @Autowired
    private SmsotpApiService delegate;

    @Valid
    @POST
    @Path("/generate")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "This API is used to generate the smsotp. <b>Permission required:</b><br> * none<br> <b>Scope required:</b><br> * internal_login ", response = OTPGenerateResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "smsotp", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OTPGenerateResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden", response = Void.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response smsotpGeneratePost(@ApiParam(value = "" ,required=true) @Valid OTPGenerationRequest otPGenerationRequest) {

        return delegate.smsotpGeneratePost(otPGenerationRequest );
    }

    @Valid
    @POST
    @Path("/validate")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @ApiOperation(value = "", notes = "This API is used to validate the smsotp. <b>Permission required:</b><br> * none<br> <b>Scope required:</b><br> * internal_login ", response = OTPValidationResponse.class, authorizations = {
        @Authorization(value = "BasicAuth"),
        @Authorization(value = "OAuth2", scopes = {
            
        })
    }, tags={ "smsotp" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = OTPValidationResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = Void.class),
        @ApiResponse(code = 403, message = "Resource Forbidden", response = Void.class),
        @ApiResponse(code = 500, message = "Server Error", response = Error.class)
    })
    public Response smsotpValidatePost(@ApiParam(value = "" ,required=true) @Valid OTPValidationRequest otPValidationRequest) {

        return delegate.smsotpValidatePost(otPValidationRequest );
    }

}
