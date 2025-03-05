var db = connect("mongodb://admin:pass@mongodb-container:27017/admin");


db.createUser(
    {
        user: "user",
        pwd: "pass",
        roles: [ { role: "readWrite", db: "blog"} ],
        passwordDigestor: "server",
    }
)

db = db.getSiblingDB('blog'); // we can not use "use" statement here to switch db

// Create the 'article' collection if it doesn't exist
db.createCollection('article');

// Insert a document into the 'article' collection
db.article.insertOne({
    username: 'lucy',
    title: 'Hello',
    content: 'Hello World',
    create_time: new Date()
});

