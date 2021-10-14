# identity-otp-integration-endpoints
Welcome to the WSO2 Identity Server (IS) API endpoints for the SMS and Email OTP services.

WSO2-IS is one of the best Identity Servers, which enables you to offload your identity and user entitlement management 
burden totally from your application. It comes with many features, supports many industry standards and most importantly 
it allows you to extend it according to your security requirements. This repository contains REST-API endpoints for the SMS 
and Email OTP services.

# SMS OTP REST-API
### Prerequisites

1. Go to the [identity-outbound-auth-sms-otp](https://github.com/wso2-extensions/identity-outbound-auth-sms-otp) repository which contains the OSGi bundle that supports the SMS-OTP REST APIs, build it and put it in the
_**<IS_HOME>repository/components/dropins**_ directory.

2. Apply the configurations which mentioned in the [identity-outbound-auth-sms-otp](https://github.com/wso2-extensions/identity-outbound-auth-sms-otp)

### Add Permissions
1. Start WSO2 identity server and log in to management console `https://<hostname>:<port>/carbon/admin/login.jsp`
2. Go to `Main -> Registry -> Browse -> /_system/governance/permission/admin/manage/identity/authentication/` Add Collection, and gave that collection name `otp`. Inside the newly created collection there is an area to add properties, click there to add property and give name as `name` and value as `otp`.
3. Like that create two more permission collections inside the newly created `otp` permission as below.
    - `/_system/governance/permission/admin/manage/identity/authentication/otp/generate` this permission is to generate the `otp`.
    - `/_system/governance/permission/admin/manage/identity/authentication/otp/validate` this permission is to validate the `otp`.
   ![Adding Permissions](https://github.com/maneeshaindrachapa/wso2-otp-service-endpoints/blob/email-otp-endpoints/images/Add%20permissions.gif)
4. Go to `Main -> Identity -> Users and roles -> add -> add new role` and create a role with `generate` permission and other role with `validate` permission.
5. Assign role with `generate` to some less privileged user and assign role with `validate` permission to users you need OTP created.

### Build & Deploy
1. To build the maven artifacts and war file, run the following command,
   `mvn clean install`

2. Add the built war file located in `~/component/org.wso2.carbon.identity.api.otp.service.smsotp/target` 
directory to `<IS_HOME>/repository/deployment/server/webapps` directory.

3. Go to the **_<IS_HOME>/repository/conf/deployment.toml_** file, and add the below configurations.
   ```
   [[resource.access_control]]
   default_access = "allow"
   context = "(.*)/api/identity/sms-otp/v1/generate"
   secure = "true"
   http_method = "POST"
   permissions = "/permission/admin/manage/identity/authentication/otp/generate"
   
   [[resource.access_control]]
   default_access = "allow"
   context = "(.*)/api/identity/sms-otp/v1/validate"
   secure = "true"
   http_method = "POST"
   permissions = "/permission/admin/manage/identity/authentication/otp/validate"
   
   [tenant_context.rewrite]
   custom_webapps=["/api/identity/sms-otp/v1/"]
   ```
### Usage
1. To generate SMS-OTP check [here](https://github.com/wso2-extensions/identity-otp-integration-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.smsotp/src/main/resources/sms-otp.yaml)
   
   - `carbon.super` tenant<br>
      `https://{server-url}:{server-port}/api/identity/sms-otp/v1/generate`
   
   - Other tenants<br>
     `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/sms-otp/v1/generate`<br><br>

2. To validate SMS-OTP check [here](https://github.com/wso2-extensions/identity-otp-integration-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.smsotp/src/main/resources/sms-otp.yaml)
 
    - `carbon.super` tenant<br>
     `https://{server-url}:{server-port}/api/identity/sms-otp/v1/validate`

    - Other tenants<br>
   `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/sms-otp/v1/validate`

# Email OTP REST-API
### Prerequisites

1. Go to the [identity-outbound-auth-email-otp](https://github.com/wso2-extensions/identity-outbound-auth-email-otp) repository which contains the OSGi bundle that supports the Email-OTP REST APIs, build it and put it in the
   _**<IS_HOME>repository/components/dropins**_ directory.

2. Apply the configurations which mentioned in the [identity-outbound-auth-email-otp](https://github.com/wso2-extensions/identity-outbound-auth-email-otp)

### Build & Deploy
1. To build the maven artifacts and war file, run the following command,
   `mvn clean install`

2. Add the built war file located in `~/component/org.wso2.carbon.identity.api.otp.service.emailotp/target`
   directory to <IS_HOME>/repository/deployment/server/webapps` directory.

3. Go to the **_<IS_HOME>/repository/conf/deployment.toml_** file, and add the below configurations.
   ```
   [[resource.access_control]]
   default_access ="allow"
   context = "(.*)/api/identity/email-otp/v1/emailotp/generate"
   secure = "true"
   http_method = "POST"
   permissions = "/permission/admin/manage/identity/authentication/otp/generate"

   [[resource.access_control]]
   default_access ="allow"
   context = "(.*)/api/identity/email-otp/v1/emailotp/validate"
   secure = "true"
   http_method = "POST"
   permissions = "/permission/admin/manage/identity/authentication/otp/validate"
   
   [tenant_context.rewrite]
   custom_webapps=["/api/identity/email-otp/v1/"]
   ```
### Usage
1. To generate Email-OTP check [here](https://github.com/wso2-extensions/identity-otp-integration-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.emailotp/src/main/resources/email-otp.yaml)

    - `carbon.super` tenant<br>
      `https://{server-url}:{server-port}/api/identity/email-otp/v1/generate`

    - Other tenants<br>
      `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/email-otp/v1/generate`<br><br>

2. To validate Email-OTP check [here](https://github.com/wso2-extensions/identity-otp-integration-endpoints/blob/master/component/org.wso2.carbon.identity.api.otp.service.emailotp/src/main/resources/email-otp.yaml)

    - `carbon.super` tenant<br>
      `https://{server-url}:{server-port}/api/identity/email-otp/v1/validate`

    - Other tenants<br>
      `https://{server-url}:{server-port}/t/{tenant-name}/api/identity/email-otp/v1/validate`