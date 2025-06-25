package com.example.reservation_service.controller;

import com.example.reservation_service.controller.dto.CreateReservationDto;
import com.example.reservation_service.domain.dto.RestoranDto;
import com.example.reservation_service.domain.dto.RezervacijaDto;
import com.example.reservation_service.domain.dto.UpdateRestoranDto;
import com.example.reservation_service.service.RestoranServiceImpl;
import com.example.reservation_service.service.RezervacijaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @PostMapping
    public ResponseEntity<RezervacijaDto> dodajRezervaciju(@RequestBody CreateReservationDto rezervacijaDto) {
        RezervacijaDto novaRezervacija = rezervacijaService.dodajRezervaciju(rezervacijaDto);
        return ResponseEntity.ok(novaRezervacija);
    }

    @GetMapping("/slobodni-termini")
    public ResponseEntity<List<RezervacijaDto>> filtrirajSlobodneTermine(
            @RequestParam(required = false) String lokacija,
            @RequestParam(required = false) String tipKuhinje,
            @RequestParam(required = false) Integer brojOsoba,
            @RequestParam(required = false) LocalDate datum,
            @RequestParam(required = false) Integer vreme
    ) {
        List<RezervacijaDto> slobodniTermini = rezervacijaService.filtrirajSlobodneTermine(
                lokacija, tipKuhinje, brojOsoba, datum, vreme
        );
        return ResponseEntity.ok(slobodniTermini);
    }

    @GetMapping("/po-broju-mesta")
    public ResponseEntity<List<RezervacijaDto>> filtrirajPoBrojuMesta(
            @RequestParam Integer brojMesta,
            @RequestParam LocalDate datum,
            @RequestParam Integer vreme) {
        List<RezervacijaDto> slobodniTermini = rezervacijaService.filtrirajPoBrojuMesta(brojMesta, datum, vreme);
        return ResponseEntity.ok(slobodniTermini);
    }

    @PatchMapping("/otkazivanje-rezervacije-menadzer/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<?> otkazivanje (@PathVariable Long id,
                                          @RequestParam Boolean dostupno){

        rezervacijaService.otkazivanjeManager(id,dostupno);
        return ResponseEntity.ok("rezervacija je otkazana");
    }

    @PatchMapping("/zakazivanje-rezervacije-korisnik/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> zakazivanje (@PathVariable Long id){

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        rezervacijaService.zakazi(id,currentUsername);
        return ResponseEntity.ok("rezervacija je zakazana");
    }

    @PatchMapping("/otkazivanje-rezervacije-klijent/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> otkazivanjeKlijent (@PathVariable Long id){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        rezervacijaService.otkazivanjeKlijent(id,currentUsername);
        return ResponseEntity.ok("rezervacija je otkazana");
    }


}
