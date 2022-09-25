package com.example.springboot03datajpapostgresql.controller;

import com.example.springboot03datajpapostgresql.dto.KisiDto;
import com.example.springboot03datajpapostgresql.entity.Kisi;
import com.example.springboot03datajpapostgresql.service.KisiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kisi")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService kisiService;

    @PostMapping()
    public ResponseEntity<KisiDto> kaydet(@Valid @RequestBody KisiDto kisiDto){
        return ResponseEntity.ok(kisiService.save(kisiDto));
    }

    @GetMapping()
    public ResponseEntity<List<KisiDto>> tumunuListele(){
        return ResponseEntity.ok(kisiService.getAll());
    }

}
