package com.distribuicao.unisinos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuicao.unisinos.dto.UsuarioCreateDTO;
import com.distribuicao.unisinos.dto.UsuarioExternoCreateDTO;
import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> findAll(){
		return usuarioService.findAll();
	}
	
	@GetMapping("ByEmail")
	public Optional<Usuario> findByEmail(String email){
		return usuarioService.findByEmail(email);
	}
	
	@GetMapping("/ByNome")
	public List<Usuario> findByNome(String nome){
		return usuarioService.findByNome(nome);
	}
	
	@PostMapping
	public void createUsuario(@RequestBody UsuarioCreateDTO usuarioDTO) {
		usuarioService.createUsuario(usuarioDTO);
	}
	
	@PostMapping("/usuario-externo")
	public void createUsuarioExterno(@RequestBody UsuarioExternoCreateDTO usuarioDTO) {
		usuarioService.createUsuarioExterno(usuarioDTO);
	}
}
