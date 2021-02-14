package com.testuser.user.controller;

import com.testuser.user.model.PartNumber;
import com.testuser.user.service.PartNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController                                //buat jadi API bisa di akses Postman
@RequestMapping("api/v1")                      //buat API folder api/v1/*
@CrossOrigin(origins="*",allowedHeaders = "*") //beda url bisa diakses atau port beda bisa diakses waktu beda server

public class PartNumberController {
    @Autowired
    PartNumberService partNumberService;

    @GetMapping("/partnumbers")
    public HashMap<String,Object> getAllPartNumber(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( "Accept","application/json");
        httpHeaders.add("Content-Type", "application/json");
        List<PartNumber> partNumber1 = partNumberService.findAllPartNumber();

        HashMap<String,Object> map = new HashMap();
        if(partNumber1 == null){
            map.put("message","error");

        } else {
            map.put("message","success");
            map.put("status", 200);               //200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(partNumber1));
        }
        return map;
    }

    // find by id PartNumber
    @GetMapping("/partnumber/{Id}")
    public HashMap<String,Object>  getPartNumberById(@PathVariable Long Id ) throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        PartNumber partnumber1 = partNumberService.findPartNumberById(Id);
        HashMap<String , Object > map = new HashMap<>();
        if( partnumber1 == null){
            map.put("message", "error");
        } else {
            map.put("message" ,"success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(partnumber1));
        }
        return map;

    }

//    //cara pertama saveOrUpadte MASIH GAGAL DI UPDATE
//    @PostMapping("/partnumber")
//    public ResponseEntity<PartNumber> saveOrUpdatePartNumber(@Valid @RequestBody PartNumber partNumber) {
//            return new ResponseEntity<>(partNumberService.saveOrUpdatePartNumber(partNumber), HttpStatus.OK);
//    }

    // Cara Ke dua: data baru di Save saja
    @PostMapping("/partnumber")
    public HashMap<String, Object> savePartNumber(@Valid @RequestBody PartNumber partnumber){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        PartNumber partnumber1 = partNumberService.savePartNumber(partnumber);
        HashMap<String , Object > map = new HashMap<>();
        if( partnumber1 == null){
            map.put("message", "error");
        } else {
            map.put("mesage" ,"success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(partnumber1));
        }
        return map;
    }


    @PutMapping("/partnumber/{Id}")
    public HashMap<String, Object>  updatePartNumber(@PathVariable Long Id, @Valid @RequestBody PartNumber detailPartNumber){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        PartNumber partNumber1 = partNumberService.updatePartNumber(Id,detailPartNumber);
        HashMap<String , Object > map = new HashMap<>();
        if(partNumber1 == null) {
            map.put("message" , "Data Tidak Ditemukan");
        } else {
            map.put("message" , "success");
            map.put("status", 200);//200=OK
            map.put("datatabel", ResponseEntity.ok().headers(httpHeaders).body(partNumber1));
        }

        return map;

    }


    @DeleteMapping("/partnumber/{Id}")
    public HashMap<String , Object> deletePartNumber(@PathVariable Long Id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-Type", "application/json");
        partNumberService.deletePartnumber(Id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message" , "success");
        map.put("status", 200);//200=OK
        map.put("datatabel"  , "deleted successfully");
        return map;

    }


}
