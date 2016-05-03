package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.services.utils.BlogEntryList;
import project.rest.mvc.BlogController;
import project.rest.mvc.BlogEntryController;
import project.rest.resources.BlogEntryListResource;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by swen on 5/3/16.
 */
public class BlogEntryListAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {
    public BlogEntryListAsm() {
        super(BlogEntryController.class,BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList blogEntryList) {
        BlogEntryListResource resource = new BlogEntryListResource();
        resource.setEntries(new BlogEntryResourceAsm().toResources(blogEntryList.getEntries()));
        resource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(blogEntryList.getBlogId())).withSelfRel());
        return resource;
    }
}
