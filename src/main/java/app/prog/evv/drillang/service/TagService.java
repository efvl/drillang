package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.tag.TagSearchRequest;
import app.prog.evv.drillang.dto.tag.Tag;

import java.util.List;

public interface TagService {

    Tag getById(Long id);

    void deleteTagById(Long id);

    Tag updateTag(Tag tag);

    Tag createTag(Tag tag);

    List<Tag> searchTags(TagSearchRequest searchRequest);

}
