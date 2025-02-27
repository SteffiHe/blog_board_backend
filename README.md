
# Data

## Postgres

### User 

```sql
DROP TABLE public.user;

CREATE TABLE public.user
(
    id SERIAL PRIMARY key NOT NULL,
    username VARCHAR(50) NOT NULL ,
    password VARCHAR(50) DEFAULT NULL,
    birthday Date  DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.user (USERNAME, PASSWORD, BIRTHDAY, EMAIL) VALUES
('lucy', '123', '2023-12-06', 'lucy@test.com'),
('thomas', '123', '2024-01-01', 'thomas@test.com');

select * from public.user;

```


# API Doc

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/api-docs




