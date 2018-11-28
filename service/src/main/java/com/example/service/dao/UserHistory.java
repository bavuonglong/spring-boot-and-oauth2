package com.example.service.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends GeneralDomain {

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;
}
