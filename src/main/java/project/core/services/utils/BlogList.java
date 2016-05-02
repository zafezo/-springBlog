package project.core.services.utils;

import project.core.models.entities.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public class BlogList {
    List<Blog> blogs = new ArrayList<>();


    public BlogList(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
