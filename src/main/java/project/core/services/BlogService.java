package project.core.services;

import project.core.models.entities.Blog;
import project.core.models.entities.BlogEntry;
import project.core.services.utils.BlogEntryList;
import project.core.services.utils.BlogList;

/**
 * Created by swen on 5/2/16.
 */
public interface BlogService {
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs();

    public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all associated blog entries

    public Blog findBlog(Long id);
}

