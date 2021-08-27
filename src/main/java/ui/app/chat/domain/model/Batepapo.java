package ui.app.chat.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Batepapo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_Emissor")
	private Usuario usuarioEmissor;
	
	@ManyToOne
	@JoinColumn(name = "Usuario_Recebedor")
	private Usuario usuarioRecebedor;
	
	@OneToMany
	private List<Mensagem> mensagens;
}
