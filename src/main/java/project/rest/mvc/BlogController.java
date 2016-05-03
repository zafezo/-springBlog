package project.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.core.models.entities.Blog;
import project.core.models.entities.BlogEntry;
import project.core.services.BlogService;
import project.core.services.exceptions.BlogNotFoundException;
import project.core.services.utils.BlogEntryList;
import project.core.services.utils.BlogList;
import project.rest.exceptions.NotFoundException;
import project.rest.resources.BlogEntryListResource;
import project.rest.resources.BlogEntryResource;
import project.rest.resources.BlogListResource;
import project.rest.resources.BlogResource;
import project.rest.resources.asm.BlogEntryListAsm;
import project.rest.resources.asm.BlogEntryResourceAsm;
import project.rest.resources.asm.BlogListResourceAsm;
import project.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

/**
 * Created by swen on 5/3/16.
 */
@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs() {
        BlogList blogList = blogService.findAllBlogs();
        return new ResponseEntity<BlogListResource>(new BlogListResourceAsm().toResource(blogList), HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    private ResponseEntity<BlogResource> getBlog(
            @PathVariable Long blogId
    ) {
        try {
            Blog blog = blogService.findBlog(blogId);
            return new ResponseEntity<BlogResource>(new BlogResourceAsm().toResource(blog), HttpStatus.OK);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping("/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
            @PathVariable Long blogId
    ) {
        try {
            BlogEntryList entryList = blogService.findAllBlogEntries(blogId);
            return new ResponseEntity<BlogEntryListResource>(new BlogEntryListAsm().toResource(entryList), HttpStatus.OK);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries",method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(
            @PathVariable Long blogId,
            @RequestBody BlogEntryResource sendedBlog
    ){
        try {
            BlogEntry  entry = blogService.createBlogEntry(blogId,sendedBlog.toBlogEntry());
            BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResource>(res,httpHeaders,HttpStatus.CREATED);
        }catch (BlogNotFoundException e){
            throw new NotFoundException(e);
        }
    }
}
