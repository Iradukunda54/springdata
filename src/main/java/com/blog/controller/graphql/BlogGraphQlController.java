package com.blog.controller.graphql;

import com.blog.dto.CommentDTO;
import com.blog.dto.PostDTO;
import com.blog.dto.UserDTO;
import com.blog.service.PostService;
import com.blog.service.UserService;
import com.blog.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class BlogGraphQlController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    public BlogGraphQlController(UserService userService, PostService postService, CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @QueryMapping
    public List<UserDTO> users() {

        return userService.getAllUsers();
    }

    @QueryMapping
    public UserDTO user(@Argument Long id) {

        return userService.getUserById(id);
    }

    @QueryMapping
    public List<PostDTO> posts() {
        // Return first page of posts for simplicity in GraphQL query mapping without pageable
        return postService.getAllPosts(org.springframework.data.domain.PageRequest.of(0, 50)).getContent();
    }

    @QueryMapping
    public PostDTO post(@Argument Long id) {
        return postService.getPostById(id);
    }

    @QueryMapping
    public List<PostDTO> searchPosts(@Argument String keyword) {
        return postService.searchPosts(keyword);
    }

    @MutationMapping
    public UserDTO createUser(@Argument String username, @Argument String email, @Argument String role) {
        UserDTO dto = new UserDTO();
        dto.setUsername(username);
        dto.setEmail(email);
        dto.setRole(role);
        dto.setPassword("default123"); // Default password for simplicity
        return userService.createUser(dto);
    }

    @MutationMapping
    public PostDTO createPost(@Argument String title, @Argument String content, @Argument Long authorId) {
        PostDTO dto = new PostDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setAuthorId(authorId);
        return postService.createPost(dto);
    }

    @MutationMapping
    public CommentDTO addComment(@Argument String content, @Argument Long postId, @Argument Long authorId) {
        CommentDTO dto = new CommentDTO();
        dto.setContent(content);
        dto.setPostId(postId);
        dto.setAuthorId(authorId);
        return commentService.addComment(dto);
    }
}
