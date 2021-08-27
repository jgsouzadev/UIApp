package ui.app.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ui.app.chat.domain.model.Batepapo;
import ui.app.chat.domain.model.ChatDTO;

public interface BatepapoRepository extends JpaRepository<Batepapo, Long>{
	
	@Query("SELECT new ui.app.chat.domain.model.ChatDTO(c.usuarioEmissor.id, c.usuarioRecebedor.nome) FROM #{#entityName}"
			+ " c WHERE c.usuarioEmissor.id = :idUsuario OR c.usuarioRecebedor.id = :idUsuario")
	List<ChatDTO> buscarChatPorEmissor(Long idUsuario);
	
}
