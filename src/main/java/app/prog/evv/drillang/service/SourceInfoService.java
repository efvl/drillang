package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.source.SourceInfo;
import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import org.springframework.data.domain.Page;

public interface SourceInfoService {

    SourceInfo findById(Long id);

    SourceInfo createSourceInfo(SourceInfo sourceInfo);

    SourceInfo updateSourceInfo(SourceInfo sourceInfo);

    void deleteSourceInfoById(Long id);

    Page<SourceInfo> searchSourceInfo(SourceInfoSearchRequest request);

}
