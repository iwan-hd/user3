package com.testuser.user.service.impl;

import com.testuser.user.model.PartNumber;
import com.testuser.user.repository.PartNumberRepository;
import com.testuser.user.service.PartNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartNumberImpl implements PartNumberService {
    @Autowired
    PartNumberRepository partNumberRepository;
    //1. Cari All PN
    @Override
    public List<PartNumber> findAllPartNumber() {
        return partNumberRepository.findAll();
    }

    //2. Cari PN by Id..kalo nga ketemu tampilkan semua
    @Override
    public PartNumber findPartNumberById(Long Id) throws Exception{
        PartNumber partNumber = partNumberRepository.findById(Id).orElse(new PartNumber());
        return partNumber;
    }


    //2. SAVE PN baru mslh kurang di cek thd duplikat
    @Override
    public PartNumber savePartNumber(PartNumber partNumber) {
        return partNumberRepository.save(partNumber);
    }


    //3. cari PN, berdasar Id, di save
    @Override
    public PartNumber updatePartNumber(Long Id, PartNumber partnumber) {
        PartNumber partNumber1 = partNumberRepository.findById(Id).get();
        partNumber1.setPartCode(partnumber.getPartCode());
        partNumber1.setPartName(partnumber.getPartName());
        PartNumber updatePartNumber = partNumberRepository.save(partNumber1);
        return updatePartNumber;
    }

    //4. DEL PN
    @Override
    public void deletePartnumber(Long Id) {
        PartNumber partNumber = partNumberRepository.findById(Id).orElse(new PartNumber());
        partNumberRepository.delete(partNumber);
    }


}
