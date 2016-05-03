package project.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import project.core.models.entities.Blog;

/**
 * Created by swen on 5/3/16.
 */
public class BlogResource extends ResourceSupport {
    private String title;
    private Long rid;

    public Blog toBlog(){
        Blog blog = new Blog();
        blog.setTitle(title);
        return blog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
