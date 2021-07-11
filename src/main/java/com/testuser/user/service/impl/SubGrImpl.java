package com.testuser.user.service.impl;

import com.testuser.user.model.GrSub;
import com.testuser.user.repository.SubGrRepository;
import com.testuser.user.service.SubGrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubGrImpl implements SubGrService {

    @Autowired
    SubGrRepository subGrRepository;

    @Override
    public List<GrSub> findGrSub() {
        return (List<GrSub>)subGrRepository.findAll();
    }

    @Override
    public GrSub findById(long id) {
        Optional<GrSub> result = subGrRepository.findById(id);
        if (result.isPresent()){
            return  result.get();
        } else {
            return null;
        }
    }
    // find 1 record

    @Override
    public List<GrSub> findByIds(int ids) {
        return subGrRepository.findAllByIdGr(ids);
    }
    //find banyak record

    @Override
    public GrSub insert(GrSub p) {
        return subGrRepository.save(p);
    }
    //simpan 1 record

    @Override
    public List<GrSub> insertAll(List<GrSub> p) {
        return (List<GrSub>)subGrRepository.saveAll(p);
    }

    @Override
    public List<GrSub> findGrSubJoint() {
        return (List<GrSub>)subGrRepository.findAllByJoinTable();
    }
    //simpan banyak record

    @Override
    public boolean delete(Long id) {
        try {
            subGrRepository.deleteById(id);
            return true;
        }   catch(Exception e){
            System.out.println((e.getMessage()));
            return false;
        }

    }
    //delete utk 1 Row

    @Override
    public boolean deleteAll(List<GrSub> ids) {
        try {
            subGrRepository.deleteAll(ids);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
    //delete banyak Row



    @Override
    public boolean update(GrSub p) {
        try {
            subGrRepository.save(p);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAll(List<GrSub> p) {
        try {
            subGrRepository.saveAll(p);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByGrId(int id) {
        try {
            subGrRepository.deleteAllByIdGr(id);
            return true;
        }   catch(Exception e){
            System.out.println((e.getMessage()));
            return false;
        }

    }
}
