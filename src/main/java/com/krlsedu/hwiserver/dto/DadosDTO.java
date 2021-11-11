package com.krlsedu.hwiserver.dto;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DadosDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date data;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String cabecalho;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String informacoes;

//	private String dados;
}
