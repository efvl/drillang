package app.prog.evv.drillang.service;

import app.prog.evv.drillang.dto.source.SourceInfoDto;
import app.prog.evv.drillang.dto.source.SourceInfoSearchRequest;
import app.prog.evv.drillang.entity.SourceInfoEntity;
import app.prog.evv.drillang.exception.entity.EntityNotFoundException;
import app.prog.evv.drillang.mapper.SourceInfoMapper;
import app.prog.evv.drillang.repository.SourceInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public SourceInfoDto findById(Long id) {
        SourceInfoEntity entity = sourceInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("source info not found (id=%d)", id)));
        return sourceInfoMapper.toDto(entity);
    }

    @Override
    public SourceInfoDto createSourceInfo(SourceInfoDto sourceInfoDto) {
        SourceInfoEntity created = sourceInfoRepository.save(sourceInfoMapper.toEntity(sourceInfoDto));
        return sourceInfoMapper.toDto(created);
    }

    @Override
    public SourceInfoDto updateSourceInfo(SourceInfoDto sourceInfoDto) {
        Optional<SourceInfoEntity> existing = sourceInfoRepository.findById(sourceInfoDto.getId());
        SourceInfoDto updated = new SourceInfoDto();
        if(existing.isPresent()){
            updated = sourceInfoMapper.toDto(sourceInfoRepository.save(sourceInfoMapper.toEntity(sourceInfoDto)));
        }
        return updated;
    }

    @Override
    public void deleteSourceInfoById(Long id) {
        sourceInfoRepository.deleteById(id);
    }

    @Override
    public Page<SourceInfoDto> searchSourceInfo(SourceInfoSearchRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getCurNumPage(), request.getSizeOfPage());
        return sourceInfoRepository.search(request, pageRequest).map(sourceInfoMapper::toDto);
    }
}
