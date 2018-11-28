package com.example.service.service.userhistory;

import com.example.service.dao.GeneralDomain;
import com.example.service.dao.User;
import com.example.service.dao.UserHistory;
import com.example.service.repository.UserHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHistoryServiceImpl implements UserHistoryService {

    private UserHistoryRepository userHistoryRepository;

    public UserHistoryServiceImpl(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    @Override
    public UserHistory saveOrUpdate(UserHistory user) {
        return userHistoryRepository.save(user);
    }

    @Override
    public List<Date> getLastedUserHistory(User user, int numberOfRecord) {
        return userHistoryRepository.findTopNByUserNameOrderByDateCreatedDesc(user.getId(), numberOfRecord).stream()
                .map(GeneralDomain::getDateCreated)
                .collect(Collectors.toList());
    }
}
