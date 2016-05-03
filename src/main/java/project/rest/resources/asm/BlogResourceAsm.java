package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.models.entities.Blog;
import project.rest.mvc.AccountController;
import project.rest.mvc.BlogController;
import project.rest.resources.BlogResource;

import static  org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
/**
 * Created by swen on 5/3/16.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog,BlogResource>{
    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource resource = new BlogResource();
        resource.setTitle(blog.getTitle());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("blog-entries").withRel("entries"));
        if (blog.getOwner() != null){
            resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        }
        return resource;
    }
}
