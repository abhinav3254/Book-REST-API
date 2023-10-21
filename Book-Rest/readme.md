# Project Name: Book Management Project

## Introduction

The **Book Management Project** is a comprehensive web application developed using **Spring Boot** that simplifies the management of a bookstore's operations. In this project, **JWT (JSON Web Token)** authentication is used for user authorization, and **MySQL** serves as the database to store essential data.

### Project Overview

In the ever-evolving world of literature, efficient management of books, authors, publishers, and user accounts is crucial for any bookstore or online book retailer. The **Book Management Project** addresses this need by providing a user-friendly platform that streamlines various bookstore operations. Whether you are an administrator overseeing book inventory or a customer looking for the latest reads, this project has you covered.

## Key Features

### 1. Book Catalog Management

- **Add Books**: Easily add new books to the catalog, including details such as title, author, publisher, price, and more.
- **Update Books**: Keep the catalog up-to-date by editing book information or removing obsolete entries.
- **Categorization**: Organize books by categories for efficient browsing.

### 2. Author and Publisher Management

- **Manage Authors**: Store and update author details, including names, birthdates, nationalities, and genders.
- **Publisher Information**: Maintain a database of publishers, including their names and additional information.

### 3. User Accounts

- **User Registration**: Customers can create accounts with their personal information, such as username, name, email, password, and more.
- **User Authentication**: Securely authenticate users using JWT tokens for access control.

### 4. Shopping Cart and Orders

- **Shopping Cart**: Customers can add and remove items from their shopping carts, allowing for hassle-free shopping.
- **Order Processing**: Admins can process customer orders, including payment handling.
- **Order History**: Keep track of past orders for both customers and administrators.

### 5. Search Functionality

- **Advanced Search**: Search for books based on criteria like title, author, category, ISBN, and more.
- **Suggested Books**: Provide book suggestions to users based on their preferences and browsing history.

### 6. User Roles and Security

- **Role-Based Access Control**: Implement role-based access control with user roles (admin and customer).
- **JWT Authentication**: Ensure secure authentication and authorization using JSON Web Tokens.

### 7. Inventory Management

- **Book Quantities**: Track the quantity of each book in inventory to manage stock efficiently.
- **Upcoming Books**: Highlight upcoming book releases for customers.

## Why It's Useful

The **Book Management Project** is incredibly useful for both bookstore administrators and customers:

- **Efficient Management**: Administrators can efficiently manage the bookstore's catalog, authors, publishers, and orders, all from a single application.
- **User-Friendly**: Customers can browse, search, and purchase books with ease, enhancing their overall shopping experience.
- **Security**: The project prioritizes security, with JWT authentication providing a secure way to access the system.
- **Scalability**: Built with Spring Boot, the application is highly scalable and can handle the growth of your bookstore's offerings.

With this project, you can take your bookstore's operations to the next level, offering customers a seamless shopping experience while optimizing your inventory management. Whether you're running a traditional bookstore or an online bookshop, the **Book Management Project** is your all-in-one solution.


-- For author table
```
INSERT INTO author (author_name, date_of_birth, nationality, gender) VALUES
('William Shakespeare', '1564-04-26 00:00:00', 'English', 'Male'),
('Jane Austen', '1775-12-16 00:00:00', 'English', 'Female'),
('Charles Dickens', '1812-02-07 00:00:00', 'English', 'Male'),
('Leo Tolstoy', '1828-09-09 00:00:00', 'Russian', 'Male'),
('Mark Twain', '1835-11-30 00:00:00', 'American', 'Male'),
('Agatha Christie', '1890-09-15 00:00:00', 'English', 'Female'),
('George Orwell', '1903-06-25 00:00:00', 'English', 'Male'),
('J.K. Rowling', '1965-07-31 00:00:00', 'English', 'Female'),
('Stephen King', '1947-09-21 00:00:00', 'American', 'Male'),
('Gabriel García Márquez', '1927-03-06 00:00:00', 'Colombian', 'Male'),
('Jane Eyre', '1816-04-21 00:00:00', 'English', 'Female'),
('Franz Kafka', '1883-07-03 00:00:00', 'Austrian', 'Male'),
('Maya Angelou', '1928-04-04 00:00:00', 'American', 'Female'),
('Ernest Hemingway', '1899-07-21 00:00:00', 'American', 'Male'),
('Virginia Woolf', '1882-01-25 00:00:00', 'English', 'Female'),
('Hermann Hesse', '1877-07-02 00:00:00', 'German', 'Male'),
('Harper Lee', '1926-04-28 00:00:00', 'American', 'Female'),
('J.R.R. Tolkien', '1892-01-03 00:00:00', 'English', 'Male'),
('Margaret Atwood', '1939-11-18 00:00:00', 'Canadian', 'Female'),
('F. Scott Fitzgerald', '1896-09-24 00:00:00', 'American', 'Male'),
('Louisa May Alcott', '1832-11-29 00:00:00', 'American', 'Female'),
('Aldous Huxley', '1894-07-26 00:00:00', 'English', 'Male'),
('Toni Morrison', '1931-02-18 00:00:00', 'American', 'Female'),
('Dostoevsky', '1821-11-11 00:00:00', 'Russian', 'Male'),
('Nora Roberts', '1950-10-10 00:00:00', 'American', 'Female');

```


for publishers

```

INSERT INTO publishers (publisher_name, country) VALUES
('Penguin Random House', 'United States'),
('HarperCollins', 'United States'),
('Simon & Schuster', 'United States'),
('Hachette Livre', 'France'),
('Macmillan Publishers', 'United Kingdom'),
('Pearson', 'United Kingdom'),
('Springer', 'Germany'),
('Oxford University Press', 'United Kingdom'),
('Cambridge University Press', 'United Kingdom'),
('Wiley', 'United States'),
('Scholastic', 'United States'),
('Penguin Books', 'United Kingdom'),
('Random House', 'United States'),
('HarperCollins Publishers', 'United Kingdom'),
('Penguin Group', 'United Kingdom'),
('Harvard University Press', 'United States'),
('Bloomsbury Publishing', 'United Kingdom'),
('Pan Macmillan', 'United Kingdom'),
('John Wiley & Sons', 'United States'),
('Houghton Mifflin Harcourt', 'United States'),
('Scholastic Corporation', 'United States'),
('Cambridge University Press', 'United States'),
('Taylor & Francis', 'United Kingdom'),
('Pearson Education', 'United States'),
('Oxford University Press', 'United States'),
('Harvard University Press', 'United Kingdom');

```


for categories

```


INSERT INTO Categories (category_name) VALUES
('Fiction'),
('Non-fiction'),
('Science'),
('Romance'),
('Mystery'),
('Fantasy'),
('Biography'),
('History'),
('Self-Help'),
('Cooking'),
('Travel'),
('Science Fiction'),
('Thriller'),
('Health & Wellness'),
('Business'),
('Children\'s'),
('Art & Photography'),
('Religion & Spirituality'),
('Politics'),
('Sports'),
('Music'),
('Education'),
('Nature'),
('Technology'),
('Humor');

```