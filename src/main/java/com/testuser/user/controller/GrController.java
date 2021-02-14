package com.testuser.user.controller;

import com.testuser.user.model.Gr;
import com.testuser.user.model.PartNumber;
import com.testuser.user.service.GrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController                                //buat jadi API bisa di akses Postman
@RequestMapping("api/v1")                      //buat API folder api/v1/*
@CrossOrigin(origins="*",allowedHeaders = "*") //beda url bisa diakses atau port beda bisa diakses waktu beda server

public class GrController {
    @Autowired
    GrService grService;

    @GetMapping("/gr")
    public HashMap<String, Object> getAllGr() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        List<Gr> gr1 = grService.findAllGr();


        HashMap<String, Object> map = new HashMap();
        if (gr1 == null) {
            map.put("message", "error");

        } else {
            map.put("message", "success");
            map.put("status", 200);               //200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(gr1));
        }
        return map;
    }

    @GetMapping("/gr/{Id}")
        public HashMap<String,Object>  getGrById(@PathVariable Long Id ) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        Gr gr1 = grService.findGrById(Id);
        HashMap<String , Object > map = new HashMap<>();
        if( gr1 == null){
            map.put("message", "error");
        } else {
            map.put("message" ,"success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(gr1));
        }
        return map;

    }

    @PostMapping("/gr")
    public HashMap<String, Object> savePartNumber(@Valid @RequestBody Gr gr){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        Gr gr1 = grService.saveGr(gr);
        HashMap<String , Object > map = new HashMap<>();
        if( gr1 == null){
            map.put("message", "error");
        } else {
            map.put("mesage" ,"success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(gr1));
        }
        return map;
    }

    @PutMapping("/gr/{Id}")
    public HashMap<String, Object>  updateGr(@PathVariable Long Id, @Valid @RequestBody Gr detailGr){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        Gr gr1 = grService.updateGr(Id,detailGr);
        HashMap<String , Object > map = new HashMap<>();
        if(gr1 == null) {
            map.put("message" , "Data Tidak Ditemukan");
        } else {
            map.put("message" , "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(gr1));
        }

        return map;

    }

    @DeleteMapping("/gr/{Id}")
    public HashMap<String , Object> deleteGr(@PathVariable Long Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        grService.deleteGr(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }


}

