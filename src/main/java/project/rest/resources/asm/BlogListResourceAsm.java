package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.services.utils.BlogList;
import project.rest.mvc.BlogController;
import project.rest.resources.BlogListResource;

/**
 * Created by swen on 5/3/16.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList,BlogListResource> {
    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource  resource = new BlogListResource();
        resource.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return resource;
    }
}
