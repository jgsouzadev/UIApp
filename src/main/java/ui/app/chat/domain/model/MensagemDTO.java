package ui.app.chat.domain.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDTO {
	
	private String nomeEnviou;
	private Long idEnviou;
	private String mensagem;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataEnviou;
	private Long idBatepapo;
	
	public MensagemDTO(String nome, Long idEmissor, String mensagem, LocalDateTime data) {
		this.nomeEnviou = nome;
		this.idEnviou = idEmissor;
		this.mensagem = mensagem;
		this.dataEnviou = data;
	}
	
}
