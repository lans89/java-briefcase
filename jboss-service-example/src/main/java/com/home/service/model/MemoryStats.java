package com.home.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoryStats {
    private Long heapSize;
    private Long heapMaxSize;
    private Long heapFreeSize;
}
