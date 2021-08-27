package ui.app.chat.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ui.app.chat.domain.model.Batepapo;
import ui.app.chat.domain.model.BatepapoDTO;
import ui.app.chat.domain.model.ChatDTO;
import ui.app.chat.domain.model.Mensagem;
import ui.app.chat.domain.model.MensagemDTO;
import ui.app.chat.domain.model.Usuario;
import ui.app.chat.domain.model.UsuarioDTO;
import ui.app.chat.repository.BatepapoRepository;
import ui.app.chat.repository.MensagemRepository;
import ui.app.chat.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UniqueController {

	private UsuarioRepository usuarioRepository;
	private BatepapoRepository batepapoRepository;
	private MensagemRepository mensagemRepository;

	@GetMapping
	public String helloWorld() {
		return "hello-world";
	}

	@PostMapping("/users/auth")
	public ResponseEntity<?> validarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		Optional<Usuario> userExiste = usuarioRepository.findByNome(usuarioDTO.getNome());
		if (!userExiste.isPresent())
			return ResponseEntity.notFound().build();
		Usuario usuario = usuarioRepository.logarUsuario(usuarioDTO.getNome(), usuarioDTO.getPassword());
		if (Objects.isNull(usuario))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		else
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}

	@PostMapping("/users/store")
	public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			return ResponseEntity.ok(usuarioRepository
					.save(Usuario.builder().nome(usuarioDTO.getNome()).password(usuarioDTO.getPassword()).build()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping("/chat/store")
	public ResponseEntity<?> iniciarConversa(@RequestBody BatepapoDTO batepapo) {
		Optional<Usuario> userExiste = usuarioRepository.findByNome(batepapo.getUsuarioRecebedor());
		Optional<Usuario> usuario = usuarioRepository.findById(batepapo.getUsuarioEmissor());
		if (!userExiste.isPresent() || !usuario.isPresent())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(batepapoRepository
					.save(Batepapo.builder().usuarioEmissor(usuario.get()).usuarioRecebedor(userExiste.get()).build()));

	}

	@PostMapping("/message/store")
	public ResponseEntity<?> enviarMensagem(@RequestBody MensagemDTO dto) {
		try {
			Optional<Batepapo> batepapo = batepapoRepository.findById(dto.getIdBatepapo());
			Optional<Usuario> usuario = usuarioRepository.findById(dto.getIdEnviou());
			if (!batepapo.isPresent() || !usuario.isPresent())
				throw new Exception("Não encontrado");

			mensagemRepository.save(Mensagem.builder().mensagem(dto.getMensagem()).batepapo(batepapo.get())
					.dataEnvio(LocalDateTime.now()).usuarioEmissor(usuario.get()).build());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/chat/all")
	public ResponseEntity<?> recuperarChats(Long idUsuario) {
		List<ChatDTO> lista = batepapoRepository.buscarChatPorEmissor(idUsuario);

		if (Objects.isNull(lista))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/message/all")
	public ResponseEntity<?> recuperarMensagens(Long idBatepapo) {
		List<MensagemDTO> lista = mensagemRepository.mensagemPorBatePapo(idBatepapo);

		if (Objects.isNull(lista))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return ResponseEntity.ok(lista);
	}

}
