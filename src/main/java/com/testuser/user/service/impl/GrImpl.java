package com.testuser.user.service.impl;

import com.testuser.user.model.Gr;
import com.testuser.user.model.PartNumber;
import com.testuser.user.repository.GrRepository;
import com.testuser.user.service.GrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrImpl implements GrService {
    @Autowired
    GrRepository grRepository;

    @Autowired
    public List<Gr>findAllGr(){ return grRepository.findAll();}

    @Override
    public Gr findGrById(Long Id) throws Exception {
        Gr gr = grRepository.findById(Id).orElse(new Gr());
        return gr;
    }

    @Override
    public Gr saveGr(Gr gr) {
        return grRepository.save(gr);
    }

    @Override
    public Gr updateGr(Long Id, Gr gr) {
        Gr gr1 = grRepository.findById(Id).get();
        gr1.setGrCode(gr.getGrCode());
        gr1.setGrPeriode(gr.getGrPeriode());
        gr1.setTanggal(gr.getTanggal());
        gr1.setKunci(gr.getKunci());
        Gr updateGr = grRepository.save(gr1);
        return updateGr;
    }


    @Override
    public void deleteGr(Long Id) {
        Gr gr = grRepository.findById(Id).orElse(new Gr());
        grRepository.delete(gr);
    }
}
