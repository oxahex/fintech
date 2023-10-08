# USER, PRODUCT API

## nginx 컨테이너
같은 네트워크(네트워크 이미지 이름: fintech) 내에 있는 user-api, product-api를 upstream으로 설정.
8085 포트 진입 시 아래 설정에 따라 각 라우트에 따라 프록시를 연결하도록 설정

```text
upstream user-api {
    server user-api:8081;
}

upstream product-api {
    server product-api:8080;
}

server {
    listen 8085;
    server_name localhost;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

    location /fintech/v1/user {
        proxy_pass http://user-api;
    }

    location /fintech/v1/product {
        proxy_pass http://product-api;
    }
}
```

각각의 swagger도 볼 수 있도록 처리하려 했으나,
api 모듈 하위에 user, product를 두고 user, product가 api라는 상위 모듈(여기에 web 관련 의존성 명시)에 의존하도록 처리하는 것이 좀 더 맞는 방향이었다는 생각이 듭니다.

그래서 현재 swagger는 nginx 포트인 8085이 아니라  localhost:8080, 8081 각 스프링부트 서버 포트에서 접근이 가능합니다.
이 부분은 리팩토링 해보려 합니다.


## DB 컨테이너
- fintech-mysql
- fintech-redis

따로 도커 네트워크 구성하지 않고, host 네트워크를 사용하도록 함.

## API 모듈 컨테이너
- user(port: 8081)
- product(port: 8080)

Docker fintech 네트워크 생성하고
하위에 user, product, nginx 이미지를 fintech 네트워크로 연결

## 생성 테이블

USR_INFO
```sql
create table USER_INFO(
    id bigint not null auto_increment primary key,
    usr_key varchar(32) not null unique,
    usr_reg_num varchar(50) not null,
    usr_nm varchar(20) not null,
    usr_icm_amt bigint default 0 not null
);
```

PRODUCT_INFO
```sql
create table PRODUCT_INFO(
    id bigint not null auto_increment primary key,
    org_cd varchar(5) not null,
    prod_cd varchar(3) not null,
    prod_nm varchar(100) not null,
    prod_min_intr double default 0.0 not null,
    prod_max_intr double default 0.0 not null
);
```

# 질문
## 1. @Encrypt 어노테이션이 붙은 경우 암호화, 복호화를 해주는 부분
@Encrypt 어노테이션이 붙은 경우 암호화, 복호화를 해주는 부분을 user 패키지 내에서 작업했는데, domain 패키지 내에 entity를 정의한 패키지에서 작업하는 것이 맞는지 궁금합니다.
entity의 경우 DB와 직접적인 연관성을 가지기 떄문에 가능하면 순수성을 유지하고,
기타 부가 작업은 api 기능을 하는 부분에서 해주는 게 맞는 것 같아 @Encrypt 어노테이션을 user 패키지 내 DTO 클래스에 붙였는데,
어떤 것이 좀 더 나은 방법인가요?

## 2. 테이블의 경우 초기 생성해두고 작업했는데, 맞게 작업한 것인지 잘 모르겠습니다.
상품 정보를 저장하고, 상품 정보에 기관 코드를 가지고 findAll 하도록 처리했는데, 왜 PRODUCT_LIST 테이블이 필요한지 잘 모르겠습니다.
과제 조건에 꼭 테이블을 그렇게 하지 않아도 된다고 되어 있긴 하지만, PRODUCT_LIST 테이블을 만드는 경우 이점이 있나요? 조회 속도가 더 빨라지는 것인지요? 이 부분은 어떤 부분을 더 공부하면 알 수 있을까요?

