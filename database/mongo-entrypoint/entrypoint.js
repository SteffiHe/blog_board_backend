
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
    { name: "MongoDB", create_time: new Date() },
    { name: "PostgreSQL", create_time: new Date() },
    { name: "Database", create_time: new Date() }
])


db.createCollection('category');
db.category.createIndex({ name: 1 }, { unique: true, collation: { locale: "en", strength: 2 } })
db.category.insertMany([
    { name: "NoSQL", create_time: new Date() },
    { name: "SQL", create_time: new Date() }
])


//db.createCollection('article');
//db.article.insertOne(
//    { username: 'lucy', title: 'Hello', content: 'Hello World', create_time: new Date()}
//)


db.createCollection('article');
db.article.insertMany([
    {
        _id: "a1",
        title: "Why MongoDB?",
        content: "MongoDB is a flexible NoSQL database...",
        author: "1",
        category: {
            "name": "NoSQL"
        },
        tags: [
            { "name": "MongoDB" },
            { "name": "Database" }
        ],

        create_time: new Date()
    },
    {
        _id: "a2",
        title: "Advantage of PostgreSQL",
        content: "PostgreSQL is a powerful relational database...",
        author: "2",
        category: {
            "name": "SQL"
        },
        tags: [
            { "name": "PostgreSQL" },
            { "name": "Database" }
        ],
        create_time: new Date()
    }
])

print('Article update ########################################################');

// Step 1: Update the `category` field in articles
// Get all unique categories used in the articles collection
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

print('END #################################################################');