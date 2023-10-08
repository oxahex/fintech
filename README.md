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