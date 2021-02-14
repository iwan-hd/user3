package com.testuser.user.service;

import com.testuser.user.model.Gr;
import com.testuser.user.model.PartNumber;

import java.util.List;

public interface GrService {
    List<Gr> findAllGr();
    Gr findGrById(Long Id) throws Exception;
    Gr saveGr(Gr gr);
    Gr updateGr(Long Id,Gr gr);
    void deleteGr(Long Id);
}
