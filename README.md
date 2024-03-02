# Blog Backend Application

## Description
* This is a backend application for a personal blog website
* It allows twwo types of users: Admin and Regular
* Admins can create, update, and delete blog posts
* Regular users can only view blog posts and leave comments on them
* The users can also view the comments left by other users and update or delete their own comments

## Technologies
* ```Spring Boot``` for the backend
* ```Spring Data JPA``` for the database
* ```Spring Security``` for the authentication and authorization
* ```JWT``` for the token-based authentication
* ```PostgreSQL``` for the database
* ```Maven``` for the project management
* ```Docker``` for the database
* ```Spring MVC``` for the RESTful API

## API Endpoints
* ```/auth/register``` - POST - Register a new user
* ```/auth/login``` - POST - Login a user
* ```/account/info/{username}``` - GET - Get the user's account details
* ```/account/update/{username}``` - PUT - Update the user's account details
* ```/account/delete/{username}``` - DELETE - Delete the user's account
* ```/posts/create``` - POST - Create a new blog post
* ```/posts/update/{id}``` - PUT - Update a blog post
* ```/posts/delete/{id}``` - DELETE - Delete a blog post
* ```/posts/find/{id}``` - GET - View a blog post
* ```/posts/all``` - GET - View all blog posts
* ```/posts/categories``` - GET - View all blog post categories
* ```/posts/category/{category}``` - GET - View all blog posts in a category
* ```/comment/create/{postId}&&{username}``` - POST - Create a new comment
* ```/comment/update/{id}``` - PUT - Update a comment
* ```/comment/delete/{id}``` - DELETE - Delete a comment


## Notes
* The application has a built in front-end using React.js at the following link: https://github.com/gabriel-2802/React-Blog-Frontend
