package com.example.demospring3.business.impl;

import com.example.demospring3.business.SeriesService;
import com.example.demospring3.dao.entity.SeriesEntity;
import com.example.demospring3.dao.repository.SeriesRepository;
import com.example.demospring3.expose.dto.SeriesDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    @Override
    public List<SeriesDto.Response> listSeries() {
        List<SeriesDto.Response> responseList = new ArrayList<>();
        List<SeriesEntity> list = seriesRepository.findAllByIsActiveTrueAndIsDeletedFalse();
        for (SeriesEntity series: list){
            SeriesDto.Response dto = new SeriesDto.Response();
            dto.setId(series.getId());
            dto.setName(series.getName());
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public SeriesDto.Response findSeriesById(UUID idSeries) {
        SeriesDto.Response response = new SeriesDto.Response();

        Optional<SeriesEntity> seriesEntity = seriesRepository.findByIdAndIsActiveTrueAndIsDeletedFalse(idSeries);
        if (seriesEntity.isPresent()){
            response.setId(seriesEntity.get().getId());
            response.setName(seriesEntity.get().getName());
        }

        return response;
    }

    @Override
    public SeriesDto.Response saveSeries(SeriesDto.Request request) {
        SeriesDto.Response response = new SeriesDto.Response();

        SeriesEntity entity = new SeriesEntity();
        entity.setName(request.getName());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setIsDeleted(false);
        entity.setIsActive(true);
        SeriesEntity saveSeries = seriesRepository.save(entity);
        response.setId(saveSeries.getId());
        response.setName(saveSeries.getName());

        return response;
    }

    @Override
    public SeriesDto.Response updateSeries(UUID idSeries, SeriesDto.Request request) {
        SeriesDto.Response response = new SeriesDto.Response();

        request.setId(idSeries);
        Optional<SeriesEntity> getEntity = seriesRepository.findById(request.getId());
        if (getEntity.isPresent()){
            SeriesEntity entity = new SeriesEntity();
            entity.setName(request.getName());
            entity.setLastModifiedDate(LocalDateTime.now());
            SeriesEntity save = seriesRepository.save(entity);

            response.setId(save.getId());
            response.setName(save.getName());
            return response;
        }
        throw new EntityNotFoundException("Id no encontrado: " + idSeries);
    }

    @Override
    public void deleteSeries(UUID idSeries) {
        Optional<SeriesEntity> getEntity = seriesRepository.findByIdAndIsActiveTrueAndIsDeletedFalse(idSeries);
        if (getEntity.isPresent()) {
            getEntity.get().setIsActive(false);
            getEntity.get().setIsDeleted(true);
            seriesRepository.save(getEntity.get());
        }

    }
}
