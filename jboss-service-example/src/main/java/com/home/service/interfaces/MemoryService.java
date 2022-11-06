package com.home.service.interfaces;

import com.home.service.enums.UnitSize;
import com.home.service.model.GeneralResponse;

public interface MemoryService {
    public GeneralResponse getMemoryStadistics(UnitSize unitSize);
}
