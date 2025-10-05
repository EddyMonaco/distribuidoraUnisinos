package com.distribuicao.unisinos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuicao.unisinos.dto.UsuarioCreateDTO;
import com.distribuicao.unisinos.dto.UsuarioExternoCreateDTO;
import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.model.UsuarioExterno;
import com.distribuicao.unisinos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public List<Usuario> findByNome(String nome){
		return usuarioRepository.findByNome(nome);
	}
	
	public UsuarioExterno createUsuarioExterno(UsuarioExternoCreateDTO usuarioDTO) {
		UsuarioExterno usuario = new UsuarioExterno();
		usuario.setCnpjCpf(usuarioDTO.getCnpjCpf());
		usuario.setEnderecos(usuarioDTO.getEnderecos());
		usuario.setTipoExterno(usuarioDTO.getTipoExterno());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
	
	public Usuario createUsuario(UsuarioCreateDTO usuarioDTO) {
		UsuarioExterno usuario = new UsuarioExterno();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
}
