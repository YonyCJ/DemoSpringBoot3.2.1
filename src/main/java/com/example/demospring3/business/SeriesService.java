package com.example.demospring3.business;

import com.example.demospring3.expose.dto.SeriesDto;

import java.util.List;
import java.util.UUID;

public interface SeriesService {
    List<SeriesDto.Response> listSeries();
    SeriesDto.Response findSeriesById(UUID idSeries);
    SeriesDto.Response saveSeries(SeriesDto.Request request);
    SeriesDto.Response updateSeries(UUID idSeries, SeriesDto.Request request);
    void deleteSeries (UUID idSeries);
}
