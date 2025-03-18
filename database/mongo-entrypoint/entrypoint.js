
//var db = connect("mongodb://mongodb-container:27017/admin");

// printout the current database name
print('Start #################################################################');

db = db.getSiblingDB('blog')

// the user is stored in the blog database.
db.createUser({
    user: "user",
    pwd: "pass",
    roles: [{role: "readWrite", db: "blog"}]
});

printjson(db.getUsers());

db.createCollection('tag');
db.tag.createIndex({ name: 1 }, { unique: true, collation: { locale: "en", strength: 2 } })
db.tag.insertMany([
    { name: "Relational DB", create_time: new Date() },
    { name: "Document DB", create_time: new Date() },
    { name: "Time-Series DB", create_time: new Date() },
    { name: "Object Storage", create_time: new Date() },
    { name: "Key-Value Store", create_time: new Date() },
    { name: "High Concurrency", create_time: new Date() },
    { name: "Replication Set", create_time: new Date() }
])


db.createCollection('category');
db.category.createIndex({ name: 1 }, { unique: true, collation: { locale: "en", strength: 2 } })
db.category.insertMany([
    { name: "NoSQL", create_time: new Date() },
    { name: "SQL", create_time: new Date() },
    { name: "Other", create_time: new Date() }
])

db.createCollection('rate');
db.rate.createIndex({ name: 1 }, { unique: true, collation: { locale: "en", strength: 2 } })
db.rate.insertMany([
    { name: "High", create_time: new Date() },
    { name: "Medium", create_time: new Date() },
    { name: "Low", create_time: new Date() }
])


//db.createCollection('article');
//db.article.insertOne(
//    { username: 'lucy', title: 'Hello', content: 'Hello World', create_time: new Date()}
//)


db.createCollection('article');
db.article.insertMany([
    {
        _id: "1",
        title: "Why MongoDB?",
        content: "MongoDB is a flexible NoSQL database...",
        authorId: 1,
        category: {
            "name": "NoSQL"
        },
        rate: {
            "name": "Low"
        },
        tags: [
            { "name": "Document DB" },
            { "name": "Replication Set" }
        ],

        create_time: new Date()
    },
    {
        _id: "2",
        title: "Advantage of PostgreSQL",
        content: "PostgreSQL is a powerful relational database...",
        authorId: 2,
        category: {
            "name": "SQL"
        },
        rate: {
            "name": "Medium"
        },
        tags: [
            { "name": "High concurrency" },
            { "name": "Relational DB" }
        ],
        create_time: new Date()
    },
    {
        _id: "3",
        title: "Why ElasticSearch?",
        content: "ElasticSearch is a powerful NoSQL database...",
        authorId: 1 ,
        category: {
            "name": "NoSQL"
        },
        rate: {
            "name": "High"
        },
        tags: [
            { "name": "Document DB" }
        ],
        create_time: new Date()
    },
    {
        _id: "4",
        title: "Prometheus",
        content: "Prometheus is a powerful time-series database designed for monitoring and alerting. Originally developed at SoundCloud, it has since become a widely adopted open-source project under the Cloud Native Computing Foundation (CNCF).The Key Features of Prometheus contains Time-Series Data Storage, Multi-Dimensional Data Model and Powerful Query Language (PromQL)",
        authorId: 2 ,
        category: {
            "name": "Other"
        },
        rate: {
            "name": "Low"
        },
        tags: [
            { "name": "Time-Series DB" }
        ],
        create_time: new Date()
    }

])

print('Article update ########################################################');

// Step 1: Update the `category` field in articles
let categories = db.article.distinct("category.name");

categories.forEach(function(categoryName) {
    let category = db.category.findOne({ name: categoryName });

    if (category) {
        db.article.updateMany(
            { "category.name": categoryName },
            { $set: { "category": category } }
        );
    }
});

// Step 2: Update the `tags` field in articles
let tags = db.article.distinct("tags.name");

tags.forEach(function(tagName) {
    let tag = db.tag.findOne({ name: tagName });

    if (tag) {
        db.article.updateMany(
            { "tags.name": tagName },
            { $set: { "tags.$": tag } } // Correct way to update the matching tag in an array
        );
    }
});

// Step 3: Update the `rate` field in articles
let rates = db.article.distinct("rate.name");

rates.forEach(function(rateName) {
    let rate = db.rate.findOne({ name: rateName });

    if (rate) {
        db.article.updateMany(
            { "rate.name": rateName },
            { $set: { "rate": rate } }
        );
    }
});

print('END #################################################################');