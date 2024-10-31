package com.sondeos.javanotifychallenge.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BatchProcessor {
    @Value("${batch.size}")
    private int batchSize;  // Tamaño de cada lote

    public List<List<Map<String, String>>> partitionList(List<Map<String, String>> contacts) {
        List<List<Map<String, String>>> partitions = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i += batchSize) {
            partitions.add(contacts.subList(i, Math.min(i + batchSize, contacts.size())));
        }

        return partitions;
    }

}
