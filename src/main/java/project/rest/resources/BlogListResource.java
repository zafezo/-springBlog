package project.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by swen on 5/3/16.
 */
public class BlogListResource extends ResourceSupport {
    List<BlogResource> blogs;

    public List<BlogResource> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResource> blogs) {
        this.blogs = blogs;
    }
}
