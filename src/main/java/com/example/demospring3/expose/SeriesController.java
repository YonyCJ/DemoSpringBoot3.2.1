package com.example.demospring3.expose;

import com.example.demospring3.business.SeriesService;
import com.example.demospring3.expose.dto.SeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    @GetMapping
    public ResponseEntity<List<SeriesDto.Response>> listSeries(){
        return new ResponseEntity<>(seriesService.listSeries(), HttpStatus.OK);
    }
    @GetMapping("/{idSeries}")
    public ResponseEntity<SeriesDto.Response> findSeriesById(@PathVariable("idSeries") UUID idSeries){
        return new ResponseEntity<>(seriesService.findSeriesById(idSeries), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SeriesDto.Response> saveSeries(@RequestBody SeriesDto.Request request){
        return new ResponseEntity<>(seriesService.saveSeries(request), HttpStatus.OK);
    }
    @PutMapping("/{idSeries}")
    public ResponseEntity<SeriesDto.Response> updateSeries(@PathVariable("idSeries") UUID idSeries, @RequestBody  SeriesDto.Request request){
        return new ResponseEntity<>(seriesService.updateSeries(idSeries, request), HttpStatus.OK);
    }
    @DeleteMapping("/{idSeries}")
    public ResponseEntity<Void> deleteSeries(@PathVariable("idSeries") UUID idSeries){
        seriesService.deleteSeries(idSeries);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
