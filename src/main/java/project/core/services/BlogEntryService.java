package project.core.services;

import project.core.models.entities.BlogEntry;

/**
 * Created by swen on 5/2/16.
 */
public interface BlogEntryService {
    public BlogEntry findBlogEntry(Long id);
    public BlogEntry deleteBlogEntry(Long id);
    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

}
