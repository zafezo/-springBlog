package project.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import project.core.models.entities.BlogEntry;

/**
 * Created by swen on 5/3/16.
 */
public class BlogEntryResource extends ResourceSupport {
    private String title;
    private String content;
    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public BlogEntry toBlogEntry(){
        BlogEntry entry = new BlogEntry();
        entry.setTitle(title);
        entry.setContent(content);
        return entry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
