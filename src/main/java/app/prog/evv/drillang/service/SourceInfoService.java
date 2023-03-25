package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import org.springframework.data.domain.Page;

public interface SourceInfoService {

    SourceInfoDto findById(Long id);

    SourceInfoDto createSourceInfo(SourceInfoDto sourceInfoDto);

    SourceInfoDto updateSourceInfo(SourceInfoDto sourceInfoDto);

    void deleteSourceInfoById(Long id);

    Page<SourceInfoDto> searchSourceInfo(SourceInfoSearchRequest request);

}
