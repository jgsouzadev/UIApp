package ui.app.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ui.app.chat.domain.model.Mensagem;
import ui.app.chat.domain.model.MensagemDTO;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	@Query("SELECT new ui.app.chat.domain.model.MensagemDTO(m.usuarioEmissor.nome,"
			+ " m.usuarioEmissor.id, m.mensagem, m.dataEnvio) FROM"
			+ " #{#entityName} m WHERE m.batepapo.id = :idBatePapo")
	List<MensagemDTO> mensagemPorBatePapo(Long idBatePapo);
}
