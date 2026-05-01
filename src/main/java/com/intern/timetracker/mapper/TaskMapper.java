package com.intern.timetracker.mapper;

import com.intern.timetracker.model.Task;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TaskMapper {

    @Insert("""
        INSERT INTO task (title, description, status)
        VALUES (#{title}, #{description}, #{status})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Task task);

    @Select("""
        SELECT id, title, description, status
        FROM task
        WHERE id = #{id}
    """)
    Task findById(Long id);

    @Update("""
        UPDATE task
        SET status = #{status}
        WHERE id = #{id}
    """)
    int updateStatus(@Param("id") Long id,
                     @Param("status") String status);
}