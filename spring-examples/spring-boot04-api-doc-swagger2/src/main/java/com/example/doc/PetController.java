package com.example.doc;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private List<Pet> petList = new ArrayList<>();

   @PostConstruct
   public void init(){
       petList.add(new Pet(1, "Test Pet", new Date()));
   }

    @PostMapping()
    @ApiOperation(value = "Yeni Pet Ekleme metodu", notes = "Bu metodu dikkatli kullan.")
    public ResponseEntity<Pet> kaydet(@RequestBody @ApiParam(value = "Hayvan") Pet pet){
        petList.add(pet);
        return ResponseEntity.ok(pet);
    }

    @GetMapping()
    @ApiOperation(value = "Pet Listesi metodu", notes = "Bu metod tümünü getirir.")
    public ResponseEntity<List<Pet>> tumunuListele(){
       return ResponseEntity.ok(petList);
    }

}
