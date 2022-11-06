package com.home.service.interfaces.impl;

import com.home.service.enums.UnitSize;
import com.home.service.interfaces.MemoryService;
import com.home.service.model.GeneralResponse;
import com.home.service.model.Header;
import com.home.service.model.MemoryStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class MemoryServiceImpl implements MemoryService {
    @Override
    public GeneralResponse getMemoryStadistics(UnitSize unitSize) {
        log.info("service received: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy-hh:mm:ss.SSS")));
        log.info("unit received: {}", unitSize);
        var scalar = define(unitSize);
        return GeneralResponse.builder()
                .header(Header.builder()
                        .code(0)
                        .description("Successful").build())
                .response(MemoryStats.builder()
                        .heapFreeSize(Runtime.getRuntime().freeMemory()/scalar)
                        .heapMaxSize(Runtime.getRuntime().maxMemory()/scalar)
                        .heapSize(Runtime.getRuntime().totalMemory()/scalar).build())
                .build();
    }

    private Long define(UnitSize unitSize) {
        Long base = 1024L;
        Long unique = 1L;
        return switch (unitSize){
            case B -> unique;
            case KB -> base;
            case MB -> base*base;
            case GB -> base*base*base;
        };
    }
}
