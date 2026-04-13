package ru.markussawgroupldone.entity.DTO;

import ru.markussawgroupldone.entity.Record;
import java.util.List;

public class RecordsContainerAndStatisticDto {

    private final List<Record> recordsForDto;
    private final int numberOfDoneRecords;
    private final int numberOfActiveRecords;


    public RecordsContainerAndStatisticDto(List<Record> recordsForDto, int numberOfDoneRecords, int numberOfActiveRecords) {
        this.recordsForDto = recordsForDto;
        this.numberOfDoneRecords = numberOfDoneRecords;
        this.numberOfActiveRecords = numberOfActiveRecords;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }

    public List<Record> getRecordsForDto() {
        return recordsForDto;
    }
}
