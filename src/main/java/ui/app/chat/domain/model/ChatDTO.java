package ui.app.chat.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
	
	
	private String nomeRecebedor;
	private Long idRecebedor;
}
