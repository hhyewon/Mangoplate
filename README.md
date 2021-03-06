## π ERD μ€κ³λ
![image](https://user-images.githubusercontent.com/73240332/124960284-1db35280-e057-11eb-85a6-ceb79cb57dfe.png)
> URL : https://aquerytool.com/aquerymain/index/?rurl=c5fd8665-5070-41c9-9863-2d52a577f04d&

> Password : t420f1


## π API λͺμΈμ
> URL: https://docs.google.com/spreadsheets/d/1Ojbjete8f-Afuofcz5ch0kyBbzSOYg-I7VEKBeauLQw/edit?usp=sharing


## π κ°λ°νκ²½
 <img src="https://img.shields.io/badge/-Amazon AWS-232F3E?style=flat&logo=AmazonAWS"> <img src="https://img.shields.io/badge/-EC2-232F3E?style=flat&logo=EC2"> <img src="https://img.shields.io/badge/-RDS-232F3E?style=flat&logo=RDS">
 <img src="https://img.shields.io/badge/-NGINX-009639?style=flat&logo=NGINX">

 <img src="https://img.shields.io/badge/-IntelliJ IDEA-000000?style=flat&logo=IntelliJIDEA">  <img src="https://img.shields.io/badge/-DataGrip-000000?style=flat&logo=DataGrip"> <img src="https://img.shields.io/badge/-Postman-FF6C37?style=flat&logo=Postman"> <img src="https://img.shields.io/badge/-AqueryTool-4AA8D8?style=flat&logo=AqueryTool">

 <img src="https://img.shields.io/badge/-Spring Boot-6DB33F?style=flat&logo=SpringBoot"> <img src="https://img.shields.io/badge/-Java-007396?style=flat&logo=Java"> <img src="https://img.shields.io/badge/-JSON-000000?style=flat&logo=JSON"> <img src="https://img.shields.io/badge/-MySQL-4479A1?style=flat&logo=MySQL">



## π REST API 

### Folder Structure
- `src`: λ©μΈ λ‘μ§
  `src`μλ λλ©μΈ λ³λ‘ ν¨ν€μ§λ₯Ό κ΅¬μ±νλλ‘ νλ€. **λλ©μΈ**μ΄λ νμ(User), κ²μκΈ(Post), λκΈ(Comment), μ£Όλ¬Έ(Order) λ± μννΈμ¨μ΄μ λν μκ΅¬μ¬ν­ νΉμ λ¬Έμ  μμ­μ΄λΌκ³  μκ°νλ©΄ λλ€. κ°μ μ€κ³ν  APPμ λΆμνκ³  νμν λλ©μΈμ λμΆνμ¬ `src` ν΄λλ₯Ό κ΅¬μ±νμ.
- `config` λ° `util` ν΄λ: λ©μΈ λ‘μ§μ μλμ§λ§ `src` μμ νμν λΆμ°¨μ μΈ νμΌλ€μ λͺ¨μλμ ν΄λ
- λλ©μΈ ν΄λ κ΅¬μ‘°
> Route - Controller - Provider/Service - DAO

- Route: Requestμμ λ³΄λΈ λΌμ°ν μ²λ¦¬
- Controller: Requestλ₯Ό μ²λ¦¬νκ³  Response ν΄μ£Όλ κ³³. (Provider/Serviceμ λκ²¨μ£Όκ³  λ€μ λ°μμ¨ κ²°κ³Όκ°μ νμν), νμμ  Validation
- Provider/Service: λΉμ¦λμ€ λ‘μ§ μ²λ¦¬, μλ―Έμ  Validation
- DAO: Data Access Objectμ μ€μλ§. Queryκ° μμ±λμ΄ μλ κ³³.

- λ©μλ λ€μ΄λ°λ£°
  μ΄ ννλ¦Ώμμλ μ¬μ©λλ λ©μλ λͺλͺ κ·μΉμ User λλ©μΈμ μ°Έκ³ νμ. ν­μ μ΄ κ·μΉμ λ°λΌμΌ νλ κ²μ μλμ§λ§, λ€μ΄λ°μ ν΅μΌμ± μκ² ν΄μ£Όλ κ² μ’λ€.


### Comparison
#### Springboot java (ν¨ν€μ§λ§€λμ  = Maven (= Spring μ νΈ), Gradle (Springboot μ νΈ))
> Request(μμ) / Response(λ) β Controller(= Router + Controller) β Service (CUD) / Provider (R) β DAO (DB)

### Validation
μλ² API κ΅¬μ±μ κΈ°λ³Έμ Validationμ μ μ²λ¦¬νλ κ²μ΄λ€. μΈλΆμμ μ΄λ€ κ°μ λ λ¦¬λ  Validationμ μ μ²λ¦¬νμ¬ μλ²κ° ν°μ§λ μΌμ΄ μλλ‘ μ μνμ.
κ°, νμ, κΈΈμ΄ λ±μ νμμ  Validationμ Controllerμμ,
DBμμ κ²μ¦ν΄μΌ νλ μλ―Έμ  Validationμ Provider νΉμ Serviceμμ μ²λ¦¬νλ©΄ λλ€.

## πStructure
μμ (*)μ΄ λΆμ΄μλ νμΌ(or ν΄λ)μ μΆκ°μ μΈ κ³Όμ  μ΄νμ μμ±λλ€.
```text
api-server-spring-boot
  > * build
  > gradle
  > * logs
    | app.log // warn, error λ λ²¨μ ν΄λΉνλ λ‘κ·Έκ° μμ± λλ νμΌ
    | app-%d{yyyy-MM-dd}.%i.gz
    | error.log // error λ λ²¨μ ν΄λΉνλ λ‘κ·Έκ° μμ± λλ νμΌ
    | error-%d{yyyy-MM-dd}.%i.gz
  > src.main.java.com.example.demo
    > config
      > secret
        | Secret.java // gitμ μΆμ λμ§ μμμΌ ν  μν¬λ¦Ώ ν€ κ°λ€μ΄ μμ±λμ΄μΌ νλ κ³³
      | BaseException.java // Controller, Service, Provider μμ Response μ©μΌλ‘ κ³΅ν΅μ μΌλ‘ μ¬μ© λ  μ΅μμ ν΄λμ€
      | BaseResponse.java // Controller μμ Response μ©μΌλ‘ κ³΅ν΅μ μΌλ‘ μ¬μ©λλ κ΅¬μ‘°λ₯Ό μν λͺ¨λΈ ν΄λμ€
      | BaseResponseStatus.java // Controller, Service, Provider μμ μ¬μ© ν  Response Status κ΄λ¦¬ ν΄λμ€ 
      | Constant.java // κ³΅ν΅μ μΌλ‘ μ¬μ©λ  μμ κ°λ€μ κ΄λ¦¬νλ κ³³
    > src
      > test
        | TestController.java // loggerλ₯Ό μ΄λ»κ² μ¨μΌνλμ§ λ³΄μ¬μ£Όλ νμ€νΈ ν΄λμ€
      > eatdeal  //eatdeal κ΄λ ¨ ν¨ν€μ§
        > models
          | GetEatDealDetailRes.java        
          | GetEatDealRes.java 
        | EatDealController.java
        | EatDealProvider.java
        | EatDealService.java
        | EatDealDao.java
      > kakao  //kakao λ‘κ·ΈμΈ κ΄λ ¨ ν¨ν€μ§
        > models
          | User.java
        | KakaoController.java
        | KakaoProfile.java
        | OAuthToken.java
        | UserRepository.java
        
        ...

      | WebSecurityConfig.java // spring-boot-starter-security, jwt λ₯Ό μ¬μ©νκΈ° μν ν΄λμ€ 
    > utils
      | AES128.java // μνΈν κ΄λ ¨ ν΄λμ€
      | JwtService.java // jwt κ΄λ ¨ ν΄λμ€
      | ValidateRegex.java // μ κ·ννμ κ΄λ ¨ ν΄λ
    | DemoApplication // SpringBootApplication μλ² μμ μ§μ 
  > resources
    | application.yml // Database μ°λμ μν μ€μ  κ° μΈν λ° Port μ μ νμΌ
    | logback-spring.xml // logger μ¬μ©μ console, file μ€μ  κ° μ μ νμΌ
build.gradle // gradle λΉλμμ νμν dependency μ€μ νλ κ³³
.gitignore // git μ ν¬ν¨λμ§ μμμΌ νλ ν΄λ, νμΌλ€μ μμ± ν΄λλ κ³³

```
## πDescription

### Annotation
μ€νλ§ λΆνΈλ `μ΄λΈνμ΄μ`μ λ€μνκ² μλ κ²μ΄ μ€μνλ€. SpringBootμ μμμ μ μλ¦¬λ `@SpringBootApplication` μ΄λΈνμ΄μ λΏλ§ μλλΌ `μ€νλ§ λΆνΈ μ΄λΈνμ΄μ` λ±μ ν€μλλ‘ κ΅¬κΈλ§ ν΄μ **μ€νλ§ λΆνΈμμ μμ£Ό μ¬μ©λλ λ€μν μ΄λΈνμ΄μμ μ΄ν΄νκ³  μΈμλμ.**

### Lombok
Java λΌμ΄λΈλ¬λ¦¬λ‘ λ°λ³΅λλ getter, setter, toString λ±μ λ©μλ μμ± μ½λλ₯Ό μ€μ¬μ£Όλ λΌμ΄λΈλ¬λ¦¬μ΄λ€. κΈ°λ³Έμ μΌλ‘ κ° λλ©μΈμ model ν΄λ λ΄μ μμ±νλ ν΄λμ€μ lombokμ μ¬μ©νμ¬ μ½λλ₯Ό ν¨μ¨μ μΌλ‘ μ§€ μ μλλ‘ κ΅¬μ±νλ€. μμΈν λ΄μ©μ κ΅¬κΈλ§κ³Ό model > PostUser, Userλ₯Ό ν΅ν΄ μ΄ν΄νμ.


### src - main - resources
ννλ¦Ώμ ν¬κ² log ν΄λμ src ν΄λλ‘ λλλ€. logλ ν΅μ  μμ λ°μνλ μ€λ₯λ€μ κΈ°λ‘νλ κ³³μ΄λ€. μ€μ  λ©μΈ μ½λλ srcμ λ΄κ²¨μλ€. src > main > resourcesλ₯Ό λ¨Όμ  μ΄ν΄λ³΄μ.

`application.yml`

μμ **ν¬νΈ λ²νΈλ₯Ό μ μ**νκ³  **DataBase μ°λ**μ μν κ°μ μ€μ νλ€.

`logback-spring.xml`

logs ν΄λμ λ‘κ·Έ κΈ°λ‘μ μ΄λ€ νμμΌλ‘ λ¨κΈΈ κ²μΈμ§ μ€μ νλ€. logs ν΄λμ μ΄λ»κ² κΈ°λ‘μ΄ λ¨κ²¨μ Έ μλμ§ νμΈν΄λ³΄μλΌ. (μ»€μ€ν νμ§ μμλ λλ€λ©΄`logback-spring.xml` λ₯Ό μμ ν  νμλ μλ€.)

### src - main - java

`com.example.demo` ν¨ν€μ§μλ ν¬κ² `config` ν΄λ, `src` ν΄λμ μ΄ νλ‘μ νΈμ μμμ μΈ `DemoApplication.java`κ° μλ€.

`DemoApplication.java` μ μ€νλ§ λΆνΈ νλ‘μ νΈμ μμμ μλ¦¬λ `@SpringBootApplication` μ΄λΈνμ΄μμ μ¬μ©νκ³  μλ€. (κ΅¬κΈλ§ ν΅ν΄ `@SpringBootApplication`μ λ€λ₯Έ κΈ°λ₯λ μ΄ν΄λ³΄μ.)

`src`ν΄λμλ μ€μ  **APIκ° λμνλ νλ‘μΈμ€**λ₯Ό λ΄μκ³  `config` ν΄λμλ `src`μμ νμν Secret key, Base ν΄λμ€, μμ ν΄λμ€λ₯Ό, `util` ν΄λμλ JWT, μνΈν, μ κ·ννμ λ±μ ν΄λμ€λ₯Ό λͺ¨μλ¨λ€.

`src`λ₯Ό μμΈνκ² μ΄ν΄λ³΄μ. `src`λ κ° **λλ©μΈ**λ³λ‘ ν¨ν€μ§λ₯Ό κ΅¬λΆν΄ λλλ€. νμ¬λ `user` λλ©μΈκ³Ό `test` λλ©μΈμ΄ μλ€. **λλ©μΈ**μ΄λ κ²μκΈ, λκΈ, νμ, μ μ°, κ²°μ  λ± μννΈμ¨μ΄μ λν μκ΅¬μ¬ν­ νΉμ λ¬Έμ  μμ­μ΄λΌκ³  μκ°νλ©΄ λλ€.

μ΄ λλ©μΈλ€μ API ν΅μ μμ μ΄λ€ νλ‘μΈμ€λ‘ μ²λ¦¬λλκ°? API ν΅μ μ κΈ°λ³Έμ Request β Responseμ΄λ€. μ€νλ§ λΆνΈμμ **μ΄λ»κ² Requestλ₯Ό λ°μμ, μ΄λ»κ² μ²λ¦¬νκ³ , μ΄λ»κ² Response νλμ§**λ₯Ό μ€μ μ μΌλ‘ μ΄ν΄λ³΄μ. μ λ°μ μΈ API ν΅μ  νλ‘μΈμ€λ λ€μκ³Ό κ°λ€.

> **Request** β `XXXController.java`(=Router+Controller) β `Service` (CUD) / `Provider` (R) (=Business Logic) β `Dao` (DB) β **Response**

#### 1. Controller / `UserController.java`  / @RestController

> 1) API ν΅μ μ **Routing** μ²λ¦¬
> 2) Requestλ₯Ό λ€λ₯Έ κ³μΈ΅μ λκΈ°κ³  μ²λ¦¬λ κ²°κ³Ό κ°μ Response ν΄μ£Όλ λ‘μ§
>  + Requestμ **νμμ  Validation** μ²λ¦¬ (DBλ₯Ό κ±°μΉμ§ μκ³ λ κ²μ¬ν  μ μλ)

**1) `@Autowired`**

UserControllerμ μμ±μμ `@Autowired` μ΄λΈνμ΄μμ΄ λΆμ΄μλ€. μ΄λ **μμ‘΄μ± μ£Όμ**μ μν κ²μΌλ‘, `UserController`  λΏλ§ μλλΌ λ€μμ μ΄ν΄λ³Ό `UserService`, `UserProvider`μ μμ±μμλ κ°κ° λΆμ΄ μλ κ²μ νμΈν  μ μλ€. κ°λ¨ν μμ½νλ©΄ κ°μ²΄ μμ±μ μλμΌλ‘ ν΄μ£Όλ μ­ν μ΄λ€. μμΈν νλ‘μΈμ€λ κ΅¬κΈλ§μ ν΅ν΄ μ΄ν΄λ³΄μ.

λλ¨Έμ§ μ΄λΈνμ΄μλ€ μ­μ κ΅¬κΈλ§μ ν΅ν΄ μ΄ν΄νμ.

**2) `BaseResponse`**

Responseν  λ, κ³΅ν΅ λΆλΆμ λ¬Άκ³  λ€λ₯Έ λΆλΆμ μ λ€λ¦­μ ν΅ν΄ κ΅¬νν¨μΌλ‘μ¨ λ°λ³΅λλ μ½λλ₯Ό μ€μ¬μ€λ€. (`BaseResponse.java` μ½λ μ΄ν΄ λ³Ό κ². μ¬κΈ°μ μ°μ΄λ`BaseResponseStatus` λ `enum`μ ν΅ν΄ Status κ°μ κ΄λ¦¬νκ³  μλ€.)

**3) λ©μλ λ€μ΄λ°λ£°**

μ΄ ννλ¦Ώμμλ μ¬μ©λλ λ©μλ λͺλͺ κ·μΉμ λ€μκ³Ό κ°λ€.

> HTTP Method + ν΅μ¬ URI

- **GET** `/users` λ₯Ό μ²λ¦¬νλ λ©μλλͺ β getUsers
- **PATCH** `/users` λ₯Ό μ²λ¦¬νλ λ©μλλͺ βpatchUsers

ν­μ μ΄ κ·μΉμ λ°λΌμΌ νλ κ²μ μλμ§λ§, λ€μ΄λ°μ ν΅μΌμ± μκ² ν΄μ£Όλ κ² μ’λ€.

**4) Res, Req λ€μ΄λ°λ£°**

κ° λ©μλμμ μ¬μ©λλ Res, Req λͺ¨λΈμ λͺλͺ κ·μΉλ λ©μλ λͺκ³Ό λΉμ·νλ€.

> HTTP Method + ν΅μ¬ URI +**Res/Req**

**Patch** `/users/:userId` β PatchUserRes / PatchUserReq

μ΄ Res, Req λͺ¨λΈμ `(λλ©μΈλͺ) / models` ν΄λμ λ§λ€λ©΄ λλ€.

#### 2. Service μ Provider / `UserService.java` `UserProvider.java` / @Service

> 1) **λΉμ¦λμ€ λ‘μ§**μ λ€λ£¨λ κ³³ (DB μ κ·Ό[CRUD], DBμμ λ°μμ¨ κ² νμν)
>  + Requestμ **μλ―Έμ ** **Validation** μ²λ¦¬ (DBλ₯Ό κ±°μ³μΌ κ²μ¬ν  μ μλ)

`Service`μ `Provider`λ λΉμ¦λμ€ λ‘μ§μ λ€λ£¨λ κ³³μ΄λ€. **CRUD** μ€ **R(Read)** μ ν΄λΉνλ μ½λκ° κΈ΄ κ²½μ°κ° λ§κΈ° λλ¬Έμ **R(Read)** λ§ λ°λ‘ λΆλ¦¬ν΄ `Service`λ **CUD(Create, Update, Delete)** λ₯Ό, `Provider`λ **R(Read)** λ₯Ό λ€λ£¨λλ‘ νλ€. μ μ§ λ³΄μκ° μ©μ΄ν΄μ§λ€.

`Provider`
> **R(Read)** μ κ΄λ ¨λ κ³³μ΄λ€. DBμμ select ν΄μ μ»μ΄μ¨ κ°μ κ°κ³΅ν΄μ λ±μ΄μ€λ€.

`Service`
> **CUD(Create, Update, Delete)** μ κ΄λ ¨λ κ³³μ΄λ€. **CUD**μμ **R**μ΄ νμν κ²½μ°κ° μλλ°, κ·Έλ΄ λλ `Provider`μ κ΅¬μ±λμ΄ μλ κ²μ `Service`μμ μ¬μ©νλ©΄ λλ€.

** BaseException**

`BaseException`μ ν΅ν΄ `Service`λ `Provider`μμ `Controller`μ Exceptionμ λμ§λ€. λ§μ°¬κ°μ§λ‘ Status κ°μ `BaseResponseStatus` μ `enum`μ ν΅ν΄ κ΄λ¦¬νλ€.

#### 3. DAO / `UserDao.java`
JdbcTemplateμ μ¬μ©νμ¬ κ΅¬μ±λμ΄ μλ€. μμΈν λ΄μ©μ μ΄κ³³ [κ³΅μ λ¬Έμ](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html) μ ννλ¦Ώμ κΈ°λ³Έ μμ λ₯Ό μ°Έκ³ νμ.

## π Usage
### API λ§λ€κΈ° μμ 
λ‘μ»¬μμ DemoApplicationμ μ€νμν¨λ€. (λ‘μ»¬ μλ² κ΅¬λ μ)

[DB μ°κ²° μμ΄ TEST]
1. src > test > TestController.javaμ κ΅¬μ±λμ΄ μλ APIλ₯Ό νμ€νΈν΄λ³΄μ.
2. ν¬μ€νΈλ§¨μ ν΅ν΄ GET localhost:9000/test/logλ‘ νμ€νΈκ° μ λλμ§ νμΈνλ€.

[DB μ°κ²° μ΄ν TEST]
1. resources > application.ymlμμ λ³ΈμΈμ DB μ λ³΄λ₯Ό μλ ₯νλ€.
2. DBμ TESTλ₯Ό μν κ°λ¨ν νμ΄λΈμ νλ λ§λ λ€.
3. UserController.java, UserProvider.java, UserService.java, UserDao.javaλ₯Ό κ΅¬μ±νμ¬ ν΄λΉ νμ΄λΈμ κ°λ€μ λΆλ¬μ€λ λ‘μ§μ λ§λ λ€.
4. ν¬μ€νΈλ§¨μ ν΅ν΄ λ³ΈμΈμ΄ λ§λ  API νμ€νΈκ° μ λλμ§ νμΈνλ€.

### nohup
λ¬΄μ€λ¨ μλΉμ€λ₯Ό μν΄ nohupμ μ¬μ©νλ€.

### Error
μλ² Errorλ₯Ό λ§μ£Όνλ€λ©΄, μμΈμ νμν  μ μλ λ€μν λ°©λ²λ€μ ν΅ν΄ λ¬Έμ  μμΈμ μ°Ύμ.
- μ»΄νμΌ μλ¬ νμΈ
- log ν΄λ νμΈ
- κ·Έ μΈ λ°©λ²λ€
