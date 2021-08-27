package ui.app.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ui.app.chat.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("SELECT en.id, en.nome FROM #{#entityName} en WHERE en.nome = :nome AND en.password = :senha")
	Usuario logarUsuario(String nome, String senha);
	
	Optional<Usuario> findByNome(String nome);
}
