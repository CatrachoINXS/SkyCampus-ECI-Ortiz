package com.skycampus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skycampus.model.dto.request.MisionRequestDTO;
import com.skycampus.model.dto.response.MisionResponseDTO;
import com.skycampus.service.MisionService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/misiones")
@RequiredArgsConstructor 
public class MisionController {

    private final MisionService misionService;
 
    @PostMapping
    public ResponseEntity<MisionResponseDTO> crearMision(@RequestBody MisionRequestDTO mision) {
        return ResponseEntity.status(HttpStatus.CREATED).body(misionService.crearMision(mision));
    }
}
