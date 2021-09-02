# identity-otp-integration-endpoints
Welcome to the WSO2 Identity Server (IS) API endpoints for the SMS and Email OTP services.

WSO2-IS is one of the best Identity Servers, which enables you to offload your identity and user entitlement management 
burden totally from your application. It comes with many features, supports many industry standards and most importantly 
it allows you to extend it according to your security requirements. This repo contains REST-API endpoints for the SMS 
and Email OTP services.

# SMS OTP REST-API
### Prerequisites

1. Go to the [identity-outbound-auth-sms-otp](https://github.com/wso2-extensions/identity-outbound-auth-sms-otp) repository which contains the OSGi bundle that supports the SMS-OTP REST APIs, build it and put it in the
_**<IS_HOME>repository/components/dropins**_ directory.

2. Apply the configurations which mentioned in the [identity-outbound-auth-sms-otp](https://github.com/wso2-extensions/identity-outbound-auth-sms-otp)

### Build & Deploy
1. To build the maven artifacts and war file, run the following command,
   `mvn clean install`

2. Add the built war file located in `~/component/org.wso2.carbon.identity.api.otp.service.smsotp/target` 
directory to <IS_HOME>/repository/deployment/server/webapps` directory.

3. Go to the **_<IS_HOME>/repository/conf/deployment.toml_** file, and add the below configurations.
   ```
   [[resource.access_control]]
   default_access = "allow"
   context = "(.*)/api/identity/sms-otp/v1/(.*)"
   secure = "true"
   http_method = "POST"
   
   [tenant_context.rewrite.custom_webapps]
   webapps="/api/identity/sms-otp/v1/"
   ```
### Usage
1. To generate SMS-OTP check [here](https://github.com/maneeshaindrachapa/wso2-otp-service-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.smsotp/src/main/resources/sms-otp.yaml)
   
   - `carbon.super` tenant<br>
      `https://{server-url}:{server-port}/api/identity/sms-otp/v1/generate`
   
   - Other tenants<br>
     `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/sms-otp/v1/generate`<br><br>

2. To validate SMS-OTP check [here](https://github.com/maneeshaindrachapa/wso2-otp-service-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.smsotp/src/main/resources/sms-otp.yaml)
 
    - `carbon.super` tenant<br>
     `https://{server-url}:{server-port}/api/identity/sms-otp/v1/validate`

    - Other tenants<br>
   `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/sms-otp/v1/validate`