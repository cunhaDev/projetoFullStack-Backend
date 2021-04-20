package com.fullstack.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.domain.model.Usuario;
import com.fullstack.domain.repository.UsuarioRepository;
import com.fullstack.domain.service.UsuarioService;

@RestController
@RequestMapping({"/api"})
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@Valid @RequestBody Usuario usuario) {
		return usuarioService.salvarUsuario(usuario);
	}

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/buscar")
	public List<Usuario> buscar(@RequestBody Usuario usuario) {
		return usuarioRepository.findByNomeContaining(usuario.getNome());
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable Long usuarioId, @RequestBody Usuario usuario) {

		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}

		usuario.setId(usuarioId);
		usuario = usuarioService.salvarUsuario(usuario);

		return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> excluir(@PathVariable Long usuarioId) {
		if (!usuarioRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		usuarioService.excluirUsuario(usuarioId);

		return ResponseEntity.noContent().build();
	}

}
