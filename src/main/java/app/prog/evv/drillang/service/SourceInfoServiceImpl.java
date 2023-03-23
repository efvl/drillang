package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.source.SourceInfo;
import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import app.prog.evv.drillang.dto.wordCard.WordCardDto;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import app.prog.evv.drillang.entity.WordCardEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.SourceInfoMapper;
import app.prog.evv.drillang.repository.SourceInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class SourceInfoServiceImpl implements SourceInfoService {

    private SourceInfoRepository sourceInfoRepository;
    private SourceInfoMapper sourceInfoMapper;

    public SourceInfoServiceImpl(SourceInfoRepository sourceInfoRepository, SourceInfoMapper sourceInfoMapper) {
        this.sourceInfoRepository = sourceInfoRepository;
        this.sourceInfoMapper = sourceInfoMapper;
    }

    @Override
    public SourceInfo findById(Long id) {
        SourceInfoEntity entity = sourceInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("source info not found (id=%d)", id)));
        return sourceInfoMapper.toDto(entity);
    }

    @Override
    public SourceInfo createSourceInfo(SourceInfo sourceInfo) {
        SourceInfoEntity created = sourceInfoRepository.save(sourceInfoMapper.toEntity(sourceInfo));
        return sourceInfoMapper.toDto(created);
    }

    @Override
    public SourceInfo updateSourceInfo(SourceInfo sourceInfo) {
        Optional<SourceInfoEntity> existing = sourceInfoRepository.findById(sourceInfo.getId());
        SourceInfo updated = new SourceInfo();
        if(existing.isPresent()){
            updated = sourceInfoMapper.toDto(sourceInfoRepository.save(sourceInfoMapper.toEntity(sourceInfo)));
        }
        return updated;
    }

    @Override
    public void deleteSourceInfoById(Long id) {
        sourceInfoRepository.deleteById(id);
    }

    @Override
    public Page<SourceInfo> searchSourceInfo(SourceInfoSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return sourceInfoRepository.search(request, pageRequest).map(sourceInfoMapper::toDto);
    }
}
