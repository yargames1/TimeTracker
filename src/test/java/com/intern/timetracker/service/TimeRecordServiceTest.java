package com.intern.timetracker.service;

import com.intern.timetracker.exception.NotFoundException;
import com.intern.timetracker.mapper.TaskMapper;
import com.intern.timetracker.mapper.TimeRecordMapper;
import com.intern.timetracker.model.Task;
import com.intern.timetracker.model.TimeRecord;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeRecordServiceTest {

    @Mock
    private TimeRecordMapper timeRecordMapper;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TimeRecordService timeRecordService;

    @Test
    void createTimeRecord_shouldSaveRecord() {
        Task task = new Task();
        task.setId(1L);

        when(taskMapper.findById(1L)).thenReturn(task);

        TimeRecord record = timeRecordService.createTimeRecord(
                1L,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "work"
        );

        verify(timeRecordMapper).insert(any(TimeRecord.class));
    }

    @Test
    void createTimeRecord_shouldThrowIfTaskNotFound() {
        when(taskMapper.findById(1L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> {
            timeRecordService.createTimeRecord(
                    1L,
                    1L,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    "work"
            );
        });
    }

    @Test
    void createTimeRecord_shouldThrowIfTimeInvalid() {
        Task task = new Task();
        when(taskMapper.findById(1L)).thenReturn(task);

        assertThrows(RuntimeException.class, () -> {
            timeRecordService.createTimeRecord(
                    1L,
                    1L,
                    LocalDateTime.now(),
                    LocalDateTime.now().minusHours(1),
                    "work"
            );
        });
    }

    @Test
    void getTimeRecords_shouldReturnList() {
        List<TimeRecord> records = List.of(new TimeRecord());

        when(timeRecordMapper.findByEmployeeIdAndPeriod(
                anyLong(), any(), any()
        )).thenReturn(records);

        List<TimeRecord> result = timeRecordService.getTimeRecords(
                1L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        assertEquals(1, result.size());
    }
}
