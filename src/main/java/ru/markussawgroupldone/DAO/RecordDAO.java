package ru.markussawgroupldone.DAO;


import org.springframework.stereotype.Repository;
import ru.markussawgroupldone.entity.Record;
import ru.markussawgroupldone.entity.RecordStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordDAO {
    private final List<Record> records = new ArrayList<>(
            Arrays.asList(
                    new Record("Утром выпить Redbull", RecordStatus.ACTIVE),
                    new Record("Сходить в качалку", RecordStatus.ACTIVE),
                    new Record("Приготовить поесть", RecordStatus.ACTIVE),
                    new Record("Помыть машину", RecordStatus.ACTIVE)

            )
    );
    public List<Record> allRecords(){
        return new ArrayList<>(records);
    }

    public void saveRecord(Record record) {
        records.add(record);
    }


    public void updateRecordStatus(int id, RecordStatus newStatus) {
        for (Record item : records) {
            if (item.getId() == id) {
                item.setStatus(newStatus);
                break;
            }
        }
    }

    public void deleteExcessRecord(int id) {
        records.removeIf(item -> item.getId() == id);
    }
}
