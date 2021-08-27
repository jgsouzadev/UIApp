package ui.app.chat.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Usuario usuarioEmissor;
	
	@Column
	private String mensagem;
	
	@Column
	private LocalDateTime dataEnvio = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "ID_BATEPAPO")
	private Batepapo batepapo;
	
	
}
