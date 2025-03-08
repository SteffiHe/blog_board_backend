
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

db.createCollection('blog');
db.blog.insertMany([
    {
        _id: "blog_124",
        title: "Warum MongoDB?",
        content: "MongoDB ist eine flexible NoSQL-Datenbank...",
        author_id: 43,
        category: { id: "cat_2", name: "NoSQL" },
        tags: [{ id: "tag_1", name: "MongoDB" }],
        created_at: new Date()
    },
    {
        _id: "blog_125",
        title: "Vorteile von PostgreSQL",
        content: "PostgreSQL ist eine leistungsstarke relationale DB...",
        author_id: 44,
        category: { id: "cat_3", name: "SQL-Datenbanken" },
        tags: [{ id: "tag_2", name: "PostgreSQL" }],
        created_at: new Date()
    }
])


db.createCollection('tag');
db.tag.insertMany([
    { _id: "tag_1", name: "MongoDB" },
    { _id: "tag_2", name: "PostgreSQL" }
])


db.createCollection('category');
db.category.insertMany([
    { _id: "cat_1", name: "Datenbanken" },
    { _id: "cat_2", name: "NoSQL" },
    { _id: "cat_3", name: "SQL-Datenbanken" }
])


db.createCollection('article');
db.article.insertOne(
    { username: 'lucy', title: 'Hello', content: 'Hello World', create_time: new Date()}
)

print('END #################################################################');

