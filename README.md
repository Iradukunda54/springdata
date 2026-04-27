# Blogging Platform - Phase 3

A highly optimized Spring Boot application featuring Spring Data JPA integration, advanced query handling, transaction management, and caching.

## Technical Stack
- **Framework**: Spring Boot 3.2.4 (Spring Data JPA, Spring Cache, Validation, AOP)
- **Language**: Java 21
- **Database**: PostgreSQL
- **Documentation**: Springdoc OpenAPI (Swagger UI)
- **API**: REST & GraphQL

## New Phase 3 Features
- **Spring Data JPA Integration**: Simplified database access using repository abstraction.
- **Advanced Querying**:
    - Derived queries (e.g., `findByAuthorUsername`)
    - Custom JPQL for popular post retrieval.
    - Native SQL queries for complex join operations.
- **Pagination & Sorting**: Robust support for navigating large datasets in posts, comments, and reviews.
- **Transaction Management**: Guaranteed data consistency using `@Transactional` with appropriate propagation and rollback rules.
- **Spring Cache**: Significant read performance improvement using `@Cacheable` and `@CacheEvict` for frequently accessed data (posts, users, popular posts).
- **Performance Optimization**: Replaced manual sorting (QuickSort) with database-level sorting for better scalability.

## Setup Instructions

### 1. Database Configuration
Ensure PostgreSQL is running on `localhost:5432`.
The application expects a database named `Blogweb` (updated in `application-dev.yml`) with:
- **Username**: `postgres`
- **Password**: `Kevine@123`

### 2. Running the Application
Use Maven to run the application:
```bash
mvn spring-boot:run
```

### 3. API Documentation
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **GraphiQL**: [http://localhost:8080/graphiql](http://localhost:8080/graphiql)

## Testing New Features

### Pagination & Sorting
`GET /api/posts?page=0&size=5&sort=createdAt,desc`

### Popular Posts (Cached)
`GET /api/posts/popular?limit=5`

### Author's Posts
`GET /api/posts/author/{username}`

### Paginated Comments
`GET /api/posts/{postId}/comments?page=0&size=10`

