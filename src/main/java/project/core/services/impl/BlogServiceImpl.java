package project.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.models.entities.Blog;
import project.core.models.entities.BlogEntry;
import project.core.repositories.BlogEntryRepo;
import project.core.repositories.BlogRepo;
import project.core.services.BlogService;
import project.core.services.exceptions.BlogExsistsException;
import project.core.services.exceptions.BlogNotFoundException;
import project.core.services.utils.BlogEntryList;
import project.core.services.utils.BlogList;

/**
 * Created by swen on 5/2/16.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogEntryRepo blogEntryRepo;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog  blog = blogRepo.findBlog(blogId);
        if(blog == null){
            throw new BlogNotFoundException();
        }
        BlogEntry entry = blogEntryRepo.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepo.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.findBlog(blogId);
        if(blog == null){
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId,blogEntryRepo.findByBlogId(blogId));
    }

    @Override
    public Blog findBlog(Long id) {
        return blogRepo.findBlog(id);
    }
}
