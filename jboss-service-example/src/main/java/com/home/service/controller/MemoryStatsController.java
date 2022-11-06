package com.home.service.controller;

import com.home.service.enums.UnitSize;
import com.home.service.interfaces.MemoryService;
import com.home.service.model.GeneralResponse;
import com.home.service.model.Header;
import com.home.service.model.MemoryStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/memory")
public class MemoryStatsController {

    @Autowired
    private MemoryService memoryService;

    @GetMapping("/")
    public GeneralResponse getMemoryStats(@RequestParam String unit){
        var unitsearch = Arrays.stream(UnitSize.values()).filter(u -> u.name().equalsIgnoreCase(unit.trim())).findFirst();
        if(unitsearch.isEmpty()){
            return GeneralResponse.builder()
                    .header(Header.builder()
                            .code(1)
                            .description("Unit value isn't valid")
                            .build())
                    .response(MemoryStats.builder().build())
                    .build();
        }
        return memoryService.getMemoryStadistics(unitsearch.get());
    }

}
