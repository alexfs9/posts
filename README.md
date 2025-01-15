
# POSTS API REST

API Rest application that allows us to manage (create, read, update, delete) posts from a MySQL Database, to create a post we need to have previously created a user account, when we authenticate in the app we receive a JWT (Json Web Token) that contains the user information, using this authentication tool we'll have permissions to create posts and make comments.


## Features 📚

- Creation of user accounts
- Authentication with JWT
- CRUD of posts
- Creation of comments


## Technologies ⚒️

- Spring Boot
- Spring Data JPA
- Spring Validation
- Spring Security
- JWT
- Lombok
- Mapstruct
- MySQL
- Swagger
- Git
- Postman


## Installation 🚀

1. Clone the repository.

    ```
    git clone https://github.com/alexfs9/posts.git
    ```

2. Create the database in MySQL Workbench or phpMyAdmin.

    ```
    create dabatase posts;
    ```

3. Modify these application properties if necessary.

    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/posts
    spring.datasource.username=put_your_username_here
    spring.datasource.password=put_your_password_here
    ```

4. Generate your own SHA-256 private key on any website, or you can use [this website](https://tools.keycdn.com/sha256-online-generator), once you have the private key put it in the first property, for the second one you can create any string value like **AUTH-BACKEND**.

    ```
    jwt.private.key=put_your_private_key_here
    jwt.user.generator=put_your_user_generator_here
    ```

5. Once all the steps above are done you can run the project and start using the API.

## Testing the API 🧪

Once the project is running you can enter this [URL](http://localhost:8080/swagger-ui.html) to test the API.