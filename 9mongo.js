// Use the 'library' database
use library

// Create a collection named 'books'
db.createCollection("books")

// Insert a single book document into 'books'
db.books.insertOne(
    {
        title: "Alias Grace",
        author: "Margaret Atwood",
        year: "1996"
    }
)

// Insert multiple book documents into 'books'
db.books.insertMany([
    {
        title: "Harry Potter and the Philosopher's Stone",
        author: "J.K Rowling",
        year: "1997"
    },
    {
        title: "Harry Potter and the Chamber of Secrets",
        author: "J.K Rowling",
        year: "1998"
    },
    {
        title: "Harry Potter and the Prisoner of Azkaban",
        author: "J.K Rowling",
        year: "1998"
    }
])

// Find all books published in 1998
db.books.find({ year: "1998" })

// Display only the titles of all books
db.books.find({}, { title: 1, _id: 0 })

// Find a specific book and update the year to the actual publication year (1999)
db.books.updateOne(
    { title: "Harry Potter and the Prisoner of Azkaban" },
    { $set: { year: "1999" } }
)

// Verify the update
db.books.find({ title: "Harry Potter and the Prisoner of Azkaban" })

// Remove() example: Remove all books by 'J.K Rowling' published in 1998
db.books.remove({ author: "J.K Rowling", year: "1998" })

// Save() example: Add a new book using save() (replaces existing document if the _id is the same)
db.books.save(
    {
        _id: ObjectId("507f1f77bcf86cd799439011"), // Replace with an actual ObjectId if you want
        title: "The Handmaid's Tale",
        author: "Margaret Atwood",
        year: "1985"
    }
)

// Logical Operators Example

// $and: Find books by 'J.K Rowling' published after 1997
db.books.find({
    $and: [
        { author: "J.K Rowling" },
        { year: { $gt: "1997" } }
    ]
})

// $or: Find books by either 'Margaret Atwood' or published before 1997
db.books.find({
    $or: [
        { author: "Margaret Atwood" },
        { year: { $lt: "1997" } }
    ]
})

// $not: Find books where the year is NOT '1997'
db.books.find({
    year: { $not: { $eq: "1997" } }
})

// $nor: Find books that are neither by 'J.K Rowling' nor published in 1996
db.books.find({
    $nor: [
        { author: "J.K Rowling" },
        { year: "1996" }
    ]
})

// $in: Find books published in either 1996, 1997, or 1999
db.books.find({
    year: { $in: ["1996", "1997", "1999"] }
})

// $nin: Find books not published in 1996, 1997, or 1999
db.books.find({
    year: { $nin: ["1996", "1997", "1999"] }
})

db.books.find({
    $or: [
        {
            $and: [
                { author: "J.K Rowling" },
                { year: { $in: ["1997", "1999"] } }
            ]
        },
        {
            $and: [
                { author: "Margaret Atwood" },
                { year: { $gt: "1995" } }
            ]
        }
    ]
})

