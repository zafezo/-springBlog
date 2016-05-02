package project.core.repositories;

import project.core.models.entities.BlogEntry;

import java.util.List;

/**
 * Created by swen on 5/2/16.
 */
public interface BlogEntryRepo {
    public BlogEntry findBlogEntry(Long id);
    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

    public BlogEntry createBlogEntry(BlogEntry data);

    public List<BlogEntry> findByBlogId(Long blogId);
}
