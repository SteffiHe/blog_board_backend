
# Data

## MongoDB

```sh
# Start mongodb
docker pull mongodb/mongodb-community-server:latest
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest

# Connect to mongodb
mongosh --port 27017

# Create database blog and collection article
use blog
db.createCollection('article')

# Create document in collection article
db.article.insertOne({ username: 'lucy', title: 'Hello', content: 'Hello World', create_time: new Date()})

# Query document in collection article
db.article.find().pretty()


```

## Postgres

### User 

```sql
DROP TABLE public.user;

CREATE TABLE public.user
(
    -- id SERIAL 
    id UUID DEFAULT gen_random_uuid() PRIMARY key NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) DEFAULT NULL,
    birthday Date  DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.user (USERNAME, PASSWORD, BIRTHDAY, EMAIL) VALUES
                                                                  ('lucy', '123', '2023-12-06', 'lucy@test.com'),
                                                                  ('thomas', '123', '2024-01-01', 'thomas@test.com');

select * from public.user order by username asc;
```


# API Doc

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/api-docs




