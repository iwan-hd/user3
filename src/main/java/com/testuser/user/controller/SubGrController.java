package com.testuser.user.controller;


import com.testuser.user.model.Gr;
import com.testuser.user.model.GrSub;
import com.testuser.user.service.SubGrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController                                //buat jadi API bisa di akses Postman
@RequestMapping("api/v1")                      //buat API folder api/v1/*
@CrossOrigin(origins="*",allowedHeaders = "*") //beda url bisa diakses atau port beda bisa diakses waktu beda server

public class SubGrController {

    @Autowired
    SubGrService subGrService;


    @GetMapping("/subgr")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object> getAllSubGr() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        List<GrSub> grsub1 = subGrService.findGrSub();


        HashMap<String, Object> map = new HashMap();
        if (grsub1 == null) {
            map.put("message", "error");

        } else {
            map.put("message", "success");
            map.put("status", 200);               //200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grsub1));
        }
        return map;
    }

    @GetMapping("/subgrjoin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object> getAllSubGrJoin() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        List<GrSub> grsub1 = subGrService.findGrSubJoint();


        HashMap<String, Object> map = new HashMap();
        if (grsub1 == null) {
            map.put("message", "error");

        } else {
            map.put("message", "success");
            map.put("status", 200);               //200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grsub1));
        }
        return map;
    }

    @GetMapping("/subgr/{Id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String,Object>  getGrSubById(@PathVariable Long Id ) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");

        GrSub grsub1 = subGrService.findById(Id);
        HashMap<String, Object> map = new HashMap<>();
        if (grsub1 == null) {
            map.put("message", "error");
        } else {
            map.put("message", "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grsub1));
        }
        return map;
    }


    @GetMapping("/listsubgr/{Id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String,Object>  getListGrSubById(@PathVariable int Id ) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");

        List<GrSub> grsub1 = subGrService.findByIds(Id);
        HashMap<String, Object> map = new HashMap<>();
        if (grsub1 == null) {
            map.put("message", "error");
        } else {
            map.put("message", "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grsub1));
        }
        return map;
    }

// menghapus 1 Row berdasar id dari subgr
    @DeleteMapping("/subgr/{Id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String , Object> deleteGrSub(@PathVariable Long Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        subGrService.delete(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }

    //menghapus berdasrkan GR id ...bisa banyak record grsub
    @DeleteMapping("/subgrid/{Id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String , Object> deleteByIdGr(@PathVariable int Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        subGrService.deleteByGrId(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }


    //menghapus banyak grsub berdasar grsub id misal 1,3,6,7...dsb kita pilih id dari grsub utk kita del berdasar grsub id
    @DeleteMapping("/delsubgrlist")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String , Object> deleteGrSubAll(@RequestBody List<GrSub> Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        subGrService.deleteAll(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }


    // post grsub 1 record
    @PostMapping("/subgr")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object> saveSubGr(@Valid @RequestBody GrSub grSub){
        HashMap<String, Object> map = new HashMap<>();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", "application/json");
            httpHeaders.add("Content-Type", "application/json");
            GrSub grSub1 = subGrService.insert(grSub);

            if (grSub1 == null) {
                map.put("message", "error");
            } else {
                map.put("message", "success");
                map.put("status", 200);//200=OK
                map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grSub1));
            }

        } catch (DataIntegrityViolationException e) {
            map.put("message",e.getMessage());
            map.put("status", 500);
        } catch (Exception f) {
            map.put("message",f.getMessage());
            map.put("status", 500);
        }

        return map;
    }


// post grsub banyak record
    @PostMapping("/subgrlist")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object> saveSubGrList(@Valid @RequestBody List<GrSub> grSub){
        HashMap<String, Object> map = new HashMap<>();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", "application/json");
            httpHeaders.add("Content-Type", "application/json");
            List<GrSub> grSub1 = subGrService.insertAll(grSub);

            if (grSub1 == null) {
                map.put("message", "error");
            } else {
                map.put("message", "success");
                map.put("status", 200);//200=OK
                map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grSub1));
            }

        } catch (DataIntegrityViolationException e) {
            map.put("message",e.getMessage());
            map.put("status", 500);
        } catch (Exception f) {
            map.put("message",f.getMessage());
            map.put("status", 500);
        }

        return map;
    }

    @PutMapping("/subgr")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object>  update(@Valid @RequestBody GrSub detailGr){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        Boolean grSub1 = subGrService.update(detailGr);
        HashMap<String , Object > map = new HashMap<>();
        if(grSub1 == false) {
            map.put("message" , "Data Tidak Ditemukan");
        } else {
            map.put("message" , "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grSub1));
        }

        return map;

    }

    @PutMapping("/subgrlist")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER_GR')")
    public HashMap<String, Object>  updateAll(@Valid @RequestBody List<GrSub> detailGr){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        Boolean grSub1 = subGrService.updateAll(detailGr);
        HashMap<String , Object > map = new HashMap<>();

        if(grSub1 == false) {
            map.put("message" , "Data Tidak Ditemukan");
        } else {
            map.put("message" , "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(grSub1));
        }

        return map;

    }


}
