package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntriesMap = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        timeEntry.setId(timeEntriesMap.size() + 1);
        timeEntriesMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id){
       return timeEntriesMap.get(id);
    }

    @Override
    public List<TimeEntry> list(){
        return new ArrayList<>(timeEntriesMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry){
        timeEntry.setId(id);
        timeEntriesMap.replace(id, timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(Long id){
        timeEntriesMap.remove(id);
    }
}
