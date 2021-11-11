package com.krlsedu.hwiserver.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dados", schema = "hwi_server", indexes = {@Index(name = "idx_consumo_data", columnList = "data")})
public class Dados implements Serializable {
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
	
}
