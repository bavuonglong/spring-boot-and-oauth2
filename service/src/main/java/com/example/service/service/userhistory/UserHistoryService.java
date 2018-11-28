package com.example.service.service.userhistory;


import com.example.service.dao.User;
import com.example.service.dao.UserHistory;

import java.util.Date;
import java.util.List;

public interface UserHistoryService {
    UserHistory saveOrUpdate(UserHistory user);

    List<Date> getLastedUserHistory(User user, int numberOfRecord);
}
