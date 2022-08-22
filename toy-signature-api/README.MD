# toy-signature-api
toy-signature springboot기반 api서버  

## Overview
### skill set
- java 17
- springboot 2.7.2
- spring-data-jpa
- spring-security
- h2/mysql
- swagger 

### project structure
```
application/            -> presentation layer(api)
+--                     -> controller, dto, viewmodel
+--                     -> spring security

domain/                 -> business layer(domain)
+--                     -> entities and services
+--                     -> services request model
+--                     -> persistent interfaces

infrastructure/         -> technical details layer
+--                     -> external/internal dependency implements
+--                     -> exceptions handler
+--                     -> configration
```

### design principal
- 가능하면 `final`
- domain layer는 외부 의존성 제외
  - application layer의 인스턴스 등
  - lombok 등 외부 라이브러리 제외


### code coverage
_#todo jacoco coverage_

google code style
```
./gradlew spotlessJavaApply
```
