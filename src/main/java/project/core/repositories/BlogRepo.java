package project.core.repositories;

import project.core.models.entities.Blog;

import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public interface BlogRepo {
    public Blog createBlog(Blog data);
    public List<Blog> findAllBlogs();
    public Blog findBlog(Long id);
    public Blog findBlogByTitle(String title);
    public List<Blog> findBlogsByAccount(Long accountId);
}
