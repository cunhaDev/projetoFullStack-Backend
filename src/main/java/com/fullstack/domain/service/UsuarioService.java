package com.fullstack.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.domain.exception.NegocioException;
import com.fullstack.domain.model.Usuario;
import com.fullstack.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario(Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já Existe um usuario com este Login!");
		}
		return usuarioRepository.save(usuario);
	}
	
	public void excluirUsuario(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}
}
