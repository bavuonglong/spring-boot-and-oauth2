package com.example.service.repository;

import com.example.service.dao.UserHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserHistoryRepository extends CrudRepository<UserHistory, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP ?2 * FROM USER_HISTORY us WHERE us.user_id = ?1 ORDER BY us.date_created DESC")
    List<UserHistory> findTopNByUserNameOrderByDateCreatedDesc(int userId, int numberOfRecord);
}