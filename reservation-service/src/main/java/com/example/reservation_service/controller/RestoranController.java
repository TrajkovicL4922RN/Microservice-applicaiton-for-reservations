package com.example.reservation_service.controller;

import com.example.reservation_service.controller.dto.CreateRestoranDto;
import com.example.reservation_service.domain.dto.RestoranDto;
import com.example.reservation_service.domain.dto.UpdateRestoranDto;
import com.example.reservation_service.service.RestoranServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/restorani")
public class RestoranController {

    private final RestoranServiceImpl restoranServiceImpl;

    public RestoranController(RestoranServiceImpl restoranServiceImpl) {
        this.restoranServiceImpl = restoranServiceImpl;
    }

    @PostMapping
    public ResponseEntity<RestoranDto> createRestoran(@RequestBody CreateRestoranDto createRestoranDto) {
        RestoranDto createdRestoran = restoranServiceImpl.saveRestoran(createRestoranDto);
        return new ResponseEntity<>(createdRestoran, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestoranDto> getRestoranById(@PathVariable Long id) {
        return restoranServiceImpl.findRestoranById(id)
                .map(restoranDto -> new ResponseEntity<>(restoranDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/update-restoran/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<RestoranDto> update(@PathVariable("id") Long id, @RequestBody @Valid UpdateRestoranDto updateRestoranDto){
        return new ResponseEntity<>(restoranServiceImpl.update(id,updateRestoranDto), HttpStatus.OK);
    }
}
