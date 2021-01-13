## Srping Boot集成Keycloak
### 概述
在本文中，我们将介绍设置Keycloak服务器的基础知识，如何将Spring Boot应用程序连接到它，以及如何将它与Spring Security一起使用。
### Keycloak是什么
Keycloak是一个面向现代应用和服务的开源身份和访问管理解决方案。

Keycloak提供了诸如单点登录(SSO)、身份代理和社交登录、用户联合、客户端适配器、管理控制台和帐户管理控制台等功能。想了解更多关于Keycloak的信息，请访问官方页面。

在我们的教程中，我们将使用Keycloak的管理控制台进行设置，然后使用Keycloak客户端适配器连接到Spring Boot。
### 安装设置Keycloak服务
#### 下载安装Keycloak
[官网下载](https://www.keycloak.org/downloads.html)最新的发行版，将下载的包解压运行
```bash
unzip keycloak-12.0.1.zip 
cd keycloak-12.0.1/bin
./standalone.sh -Djboss.socket.binding.port-offset=100
```
通过浏览器访问http://127.0.0.1:8180，被重定向到http://127.0.0.1:8180/auth来创建一个管理登录界面
![初始化界面](./src/main/resources/images/keycloak_init.png)
创建一个初始管理用户：admin/123456，单击`Create`按钮。然后进入登录界面，输入我们设定的用户帐号信息进行登录。
#### 创建一个Realm
成功的登录将进入到控制台，并为我们打开默认的主Realm
![添加新的Realm](./src/main/resources/images/keycloak-add-realm.png)
在左上角的下拉菜单中选择`Add realm`，创建一个新的`SpringBootKeycloak`Realm
![创建新的Realm](./src/main/resources/images/keycloak-new-realm.png)
#### 创建一个Client
导航到Clients页面。正如我们可以在下图中看到的，Keycloak自带的客户端已经内置了
![Clients](./src/main/resources/images/keycloak-clients.png)
但是我们可以向应用程序添加一个新的客户端，因此我们将单击右上角`Create`。我们将新客户端命名为`login-app`
![添加Client](./src/main/resources/images/keycloak-add-client-1.png)
在本教程的应用中，我们将保留所有默认值，除了有效的重定向uri字段。此字段应该包含将使用此客户端进行身份验证的应用程序URL
![设置重定向RUI](./src/main/resources/images/keycloak-valid-redirect-uris.png)
稍后，我们将创建一个在端口8081上运行的`Spring Boot`启动应用程序，该应用程序将使用这个客户端。因此，我们在上面使用了一个重定向URL http://localhost:8081/*。
#### 创建一个角色与用户
Keycloak使用基于角色的访问。因此，每个用户都必须有一个角色。 要做到这一点，我们需要导航到角色页面:
![Roles](./src/main/resources/images/keycloak-roles.png)
然后，我们将添加user角色:
![添加新角色](./src/main/resources/images/keycloak-add-role.png)
现在我们已经有了一个可以分配给用户的角色，但是还没有用户。所以让我们去用户页面添加一个:
![Users](./src/main/resources/images/keycloak-users.png)
我们将添加一个名为user1的用户:
![添加新用户](./src/main/resources/images/keycloak-add-user.png)
一旦用户被创建，一个页面将显示其详细信息:
![用户信息](./src/main/resources/images/keycloak-user.png)
现在我们可以转到Credentials选项卡。我们将设置初始密码为`xsw2@WSX`:
![设置用户密码](./src/main/resources/images/keycloak-credentials.png)
> 记得把`Temporary`设置为`OFF`，不然访问`http://127.0.0.1:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token`会返回如下错误
> ```json
> {
>    "error": "invalid_grant",
>    "error_description": "Account is not fully set up"
> }
> ```
最后，我们将导航到Role Mappings选项卡。我们将为user1分配用户角色:
![角色关联用户](./src/main/resources/images/role-mappings.png)
#### 使用Keycloak API生成访问令牌
Keycloak提供了一个用于生成和刷新访问令牌的REST API。我们可以轻松地使用这个API创建自己的登录页面。

首先，我们需要通过发送一个POST请求到这个URL来获取Keycloak的访问令牌:
```bash
http://127.0.0.1:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token
```
请求的主体应该是x-www-form-urlencoded格式:
```http request
client_id:login-app
username:user1
password:123456
grant_type:password
```
该响应，我们将获得一个access_token和一个refresh_token：
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKbjllQXktdXZqUGU2c2RKa3RrQmxCeXF2Q3RUSUg2Rk51WTNpeDFpNkNZIn0.eyJleHAiOjE2MTA1MjkxMjMsImlhdCI6MTYxMDUyODgyMywianRpIjoiMTdmYjE2ZjgtMGE5OC00Yzc3LWI2NmMtNDFlYTdlMmY0MGNjIiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MTgwL2F1dGgvcmVhbG1zL1NwcmluZ0Jvb3RLZXljbG9hayIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIzMDYyZjljYi1hZjA4LTQzNzYtODhkMC02ZWMyZWM5OWU2MTYiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJsb2dpbi1hcHAiLCJzZXNzaW9uX3N0YXRlIjoiM2NmM2UyNzYtNGVmOS00YjA4LTgxN2ItMzNiMDU1M2JiYTIyIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoidXNlcjEifQ.m5FjMBFfD_vCcXRj1_EUu85bNaIHqRqh8uFiti6ZZvtNk3sKQ61R_x-ol9LNf0H8moZiZalNISL-nZxDfiBvU80rPC4rdXm9yvGjF0M_0nEK5tmL4NHXCRbfm2r89gDHr5YQE2dfmCh_BWo6C2S3blrPAbnocs3l0-z2c8MxpuxvFBWtKwvv3kMKaP6UA1fhELdA7ZgIFAgXYiqA-G7fk5XKE1mid83T_o2hWGdjnchwM-51uh_EhRdp3FsL62VwirzCWwg4OLq3L7uApHOC8h5mbQWdvbF8HL-itELPVAlhqnddBBOLIrKpOcQuNLETjRuOdCXz1tQZPIYmovqawA",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxYzdhYjdlMS01ZWMxLTQyMWMtYTc3OS00ZDFhNmYxYmQxNTAifQ.eyJleHAiOjE2MTA1MzA2MjMsImlhdCI6MTYxMDUyODgyMywianRpIjoiMGQ4YjkxOTctYTA5Yi00OTFmLWI1MDktNGViMDIyZjU3MDc3IiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MTgwL2F1dGgvcmVhbG1zL1NwcmluZ0Jvb3RLZXljbG9hayIsImF1ZCI6Imh0dHA6Ly8xMjcuMC4wLjE6ODE4MC9hdXRoL3JlYWxtcy9TcHJpbmdCb290S2V5Y2xvYWsiLCJzdWIiOiIzMDYyZjljYi1hZjA4LTQzNzYtODhkMC02ZWMyZWM5OWU2MTYiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoibG9naW4tYXBwIiwic2Vzc2lvbl9zdGF0ZSI6IjNjZjNlMjc2LTRlZjktNGIwOC04MTdiLTMzYjA1NTNiYmEyMiIsInNjb3BlIjoiZW1haWwgcHJvZmlsZSJ9.RYyAD3QEtbJ_hHIwxV6-hhVbqDSTvJ97XhR24n3wLW0",
    "token_type": "Bearer",
    "not-before-policy": 0,
    "session_state": "3cf3e276-4ef9-4b08-817b-33b0553bba22",
    "scope": "email profile"
}
```
访问令牌应该在每个对keycloak保护的资源的请求中使用，只需将它放在授权头中:
```http request
headers: {
    'Authorization': 'Bearer' + access_token
}
```
一旦访问令牌过期，我们可以通过发送一个POST请求到上面相同的URL来刷新它，但是包含刷新令牌而不是用户名和密码:
```http request
{
    'client_id': 'your_client_id',
    'refresh_token': refresh_token_from_previous_request,
    'grant_type': 'refresh_token'
}
```
Keycloak会用一个新的access_token和refresh_token来响应它。
#### 参考
https://www.baeldung.com/spring-boot-keycloak
