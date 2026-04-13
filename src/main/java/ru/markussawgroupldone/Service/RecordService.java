package ru.markussawgroupldone.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.markussawgroupldone.DAO.RecordDAO;
import ru.markussawgroupldone.entity.DTO.RecordsContainerAndStatisticDto;
import ru.markussawgroupldone.entity.Record;
import ru.markussawgroupldone.entity.RecordStatus;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {
    private final RecordDAO recordDAO;

    @Autowired
    public RecordService(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    public RecordsContainerAndStatisticDto allRecords(String filterMode) {
        List<Record> taperecords = recordDAO.allRecords();
        int numberOfDoneRecords = (int) taperecords.stream().filter(record -> record.getStatus()== RecordStatus.DONE).count();
        int numberOfActiveRecords = (int) taperecords.stream().filter(record -> record.getStatus()== RecordStatus.ACTIVE).count();

        if (filterMode == null || filterMode.isBlank()) {
            return new RecordsContainerAndStatisticDto(taperecords,numberOfActiveRecords,numberOfDoneRecords);
        }

        String filterModeWithUpperCase = filterMode.toUpperCase();

        List<String> allowedFilterModes = Arrays.stream(RecordStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (allowedFilterModes.contains(filterModeWithUpperCase)) {
            List<Record>filteredRecords = taperecords.stream()
                    .filter(record -> record.getStatus() == RecordStatus.valueOf(filterModeWithUpperCase) )
                    .collect(Collectors.toList());
            return new RecordsContainerAndStatisticDto(filteredRecords,numberOfDoneRecords,numberOfActiveRecords);
        } else {
            return new RecordsContainerAndStatisticDto(taperecords,numberOfActiveRecords,numberOfDoneRecords);
        }
    }

    public void saveRecord(String title) {
        if (title != null && !title.isBlank()) {
            recordDAO.saveRecord(new Record(title));
        }
    }
    public void updateRecordStatus(int id , RecordStatus newStatus){
        recordDAO.updateRecordStatus(id, newStatus);
    }


    public void deleteExcessRecord(int id ){
        recordDAO.deleteExcessRecord(id);
    }
}
