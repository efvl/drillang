package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.lessonTag.LessonTagDto;
import app.prog.evv.drillang.dto.lessonTag.LessonTagSearchRequest;

import java.util.List;

public interface LessonTagService {

    LessonTagDto getById(Long id);

    void deleteTagById(Long id);

    LessonTagDto updateTag(LessonTagDto lessonTag);

    LessonTagDto createTag(LessonTagDto lessonTag);

    List<LessonTagDto> searchTags(LessonTagSearchRequest searchRequest);

}
