package project.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.core.models.entities.BlogEntry;
import project.core.repositories.BlogEntryRepo;
import project.core.services.BlogEntryService;

/**
 * Created by swen on 5/2/16.
 */
@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService{

    @Autowired
    private BlogEntryRepo blogEntryRepo;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return blogEntryRepo.findBlogEntry(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return blogEntryRepo.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        return blogEntryRepo.updateBlogEntry(id,data);
    }
}
