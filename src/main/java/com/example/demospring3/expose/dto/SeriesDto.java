package com.example.demospring3.expose.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class SeriesDto {
    @Getter
    @Setter
    public static class Response{
        private UUID id;
        private String name;
    }

    @Getter
    @Setter
    public static class Request{
        private UUID id;
        private String name;
        private String createdBy;
        private String lastModifiedBy;
    }
}
