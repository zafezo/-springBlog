package project.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import project.core.models.entities.BlogEntry;
import project.rest.mvc.BlogController;
import project.rest.mvc.BlogEntryController;
import project.rest.resources.BlogEntryResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by swen on 5/3/16.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry,BlogEntryResource> {
    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource resource = new BlogEntryResource();
        resource.setContent(blogEntry.getContent());
        resource.setTitle(blogEntry.getTitle());
        resource.setRid(blogEntry.getId());
        resource.add(linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel());
        if(blogEntry.getBlog() != null){
            resource.add(linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog"));
        }
        return resource;
    }
}
