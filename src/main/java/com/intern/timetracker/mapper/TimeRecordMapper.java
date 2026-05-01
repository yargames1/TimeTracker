package com.intern.timetracker.mapper;

import com.intern.timetracker.model.TimeRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimeRecordMapper {

    @Insert("""
        INSERT INTO time_record (employee_id, task_id, start_time, end_time, description)
        VALUES (#{employeeId}, #{taskId}, #{startTime}, #{endTime}, #{description})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(TimeRecord timeRecord);

    @Select("""
        SELECT id, employee_id, task_id, start_time, end_time, description
        FROM time_record
        WHERE employee_id = #{employeeId}
          AND start_time >= #{start}
          AND end_time <= #{end}
    """)
    List<TimeRecord> findByEmployeeIdAndPeriod(
            @Param("employeeId") Long employeeId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}