package com.testuser.user.service;

import com.testuser.user.model.GrSub;

import java.util.List;

public interface SubGrService {

    List<GrSub> findGrSub();  //method buat tampilkan grsub data
    GrSub findById(long id);
    List<GrSub> findByIds(int id);

    GrSub insert(GrSub p);
    List<GrSub> insertAll(List<GrSub> p);

    List<GrSub> findGrSubJoint();  //Joint GR,GrSub,part_number

    boolean delete(Long id);
    boolean deleteByGrId(int id);
    boolean deleteAll(List<GrSub> ids);

    boolean update(GrSub p);
    boolean updateAll(List<GrSub> p);
}
