
# Data

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

## MongoDB

### Blog

```JSON
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
```

### Tag

```JSON
db.tag.insertMany([
  { _id: "tag_1", name: "MongoDB" },
  { _id: "tag_2", name: "PostgreSQL" }
])
```

### Category

```JSON
db.category.insertMany([
  { _id: "cat_1", name: "Datenbanken" },
  { _id: "cat_2", name: "NoSQL" },
  { _id: "cat_3", name: "SQL-Datenbanken" }
])
```

# API Doc

http://localhost:8080/swagger-ui/index.html

http://localhost:8080/api-docs




