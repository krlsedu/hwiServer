package com.krlsedu.hwiserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krlsedu.hwiserver.dto.DadosDTO;
import com.krlsedu.hwiserver.model.Dados;
import com.krlsedu.hwiserver.service.DadosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class DadosController {
	
	private final DadosService tarifaService;
	
	@Autowired
	public DadosController(DadosService dadosService) {
		this.tarifaService = dadosService;
	}
	
	@PostMapping(value = "/dados", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Dados> create(@RequestBody final DadosDTO dadosDTO) throws JsonProcessingException {
		return new ResponseEntity<>(tarifaService.salva(dadosDTO), HttpStatus.OK);
	}
	
	@GetMapping(value = "/dados", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<DadosDTO>> get() {
		return new ResponseEntity<>(tarifaService.getDadosList(), HttpStatus.OK);
	}
}