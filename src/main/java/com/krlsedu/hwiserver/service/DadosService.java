package com.krlsedu.hwiserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krlsedu.hwiserver.dto.DadosDTO;
import com.krlsedu.hwiserver.model.Dados;
import com.krlsedu.hwiserver.repository.DadosRepository;
import com.krlsedu.hwiserver.utils.ConversorEntidadeDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Log4j2
public class DadosService {
	
	private final DadosRepository dadosRepository;
	
	private final ConversorEntidadeDTO<Dados, DadosDTO> conversorEntidadeDTO;
	
	private final JdbcTemplate jdbcTemplate;
	
	public DadosService(DadosRepository dadosRepository, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.conversorEntidadeDTO = new ConversorEntidadeDTO<>(Dados.class, DadosDTO.class);
		this.dadosRepository = dadosRepository;
	}
	
	public Dados salva(DadosDTO dadosDTO) throws JsonProcessingException {
		
		log.info("Chegou: " + dadosDTO);
		var colNames = new ArrayList<String>();
		var colNamesCount = new HashMap<String, Integer>();
		var values = new HashMap<String, Object>();
		String[] cabecalho = dadosDTO.getCabecalho().split(",");
		String[] informacoes = dadosDTO.getInformacoes().split(",");
		for (int i = 0; i < cabecalho.length; i++) {
			String cab = cabecalho[i];
			cab = cab.replaceAll(" +", " ");
			cab = cab.trim().replaceAll("[^A-Za-z0-9]", "_");
			cab = cab.replaceAll("_+", "_").toLowerCase();
			
			if (cab.endsWith("_")) {
				cab = cab.substring(0, cab.length() - 1);
			}
			
			if (colNames.contains(cab)) {
				colNamesCount.putIfAbsent(cab, 0);
				colNamesCount.put(cab, colNamesCount.get(cab) + 1);
				cab = cab + "_" + colNamesCount.get(cab);
			}
			colNames.add(cab);
			values.put(cab, informacoes[i]);
		}
		var json = new ObjectMapper().writeValueAsString(values);
		dadosDTO.setData(new Date());
		var dados = dadosRepository.saveAndFlush(conversorEntidadeDTO.toEntity(dadosDTO));
		jdbcTemplate.update("update hwi_server.dados set dados = ?::jsonb where id = ?", json, dados.getId());
		return dados;
	}
	
	public List<DadosDTO> getDadosList() {
		return conversorEntidadeDTO.toDTO(dadosRepository.findAll());
	}
}