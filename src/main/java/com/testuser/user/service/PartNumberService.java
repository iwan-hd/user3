package com.testuser.user.service;

import com.testuser.user.model.PartNumber;

import java.util.List;

public interface PartNumberService {
    List<PartNumber> findAllPartNumber();
    PartNumber findPartNumberById(Long Id) throws Exception;
    PartNumber savePartNumber(PartNumber partNumber);
    PartNumber updatePartNumber(Long Id, PartNumber partnumber);
    void deletePartnumber(Long Id);
}
