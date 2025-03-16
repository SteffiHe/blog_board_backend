
# Database

MongoDB:Article, Tag, Category
Postgres: User

## Docker-Compose

```sh
docker-compose up -d
docker-compose down --remove-orphans --volumes 

# enter the  mongodb-container
docker exec -it mongodb-container mongosh -authenticationDatabase "blog" -u "user" -p "pass"
```

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



# API Doc

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/api-docs




