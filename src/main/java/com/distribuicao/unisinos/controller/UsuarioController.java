package com.distribuicao.unisinos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.distribuicao.unisinos.dto.UsuarioCreateDTO;
import com.distribuicao.unisinos.dto.UsuarioExternoCreateDTO;
import com.distribuicao.unisinos.model.Supervisor;
import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.model.UsuarioExterno;
import com.distribuicao.unisinos.model.Vendedor;
import com.distribuicao.unisinos.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuario-externo")
	public List<UsuarioExterno> findAllUsuariosExternos(){
		return usuarioService.findAllUsuariosExternos();
	}
	
	@GetMapping("/usuario-externo/byNome/{nome}")
	public List<UsuarioExterno> findAllUsuarioExternoByNome(@PathVariable String nome){
		return usuarioService.findUsuarioExternoByNome(nome);
	}
	
	@GetMapping("/usuario-externo/byEmail/{email}")
	public Optional<UsuarioExterno> findAllUsuarioExternoByEmail(@PathVariable String email){
		return usuarioService.findUsuarioExternoByEmail(email);
	}
	
	@GetMapping("/usuario-externo/byCnpjCpf/{cnpjCpf}")
	public Optional<UsuarioExterno> findUsuarioExternoByCnpjCpf(@PathVariable String cnpjCpf){
		return usuarioService.findUsuarioExternoByCnpjCpf(cnpjCpf);
	}
	
	@GetMapping("/usuario-externo/byTipo/{tipo}")
	public List<UsuarioExterno> findUsuarioExternoByTipo(@PathVariable UsuarioExterno.TipoExterno tipo){
		return usuarioService.findUsuarioExternoByTipo(tipo);
	}
	
	@GetMapping("/vendedores")
	public List<Vendedor> findAllVendedores(){
		return usuarioService.findAllVendedores();
	}
	
	@GetMapping("/supervisores")
	public List<Supervisor> findAllSupervisores(){
		return usuarioService.findAllSupervisores();
	}
	
	@GetMapping("/byEmail/{email}")
	public Optional<Usuario> findByEmail(@PathVariable String email){
		return usuarioService.findByEmail(email);
	}
	
	@GetMapping("/byNome/{nome}")
	public List<Usuario> findByNome(@PathVariable String nome){
		return usuarioService.findByNome(nome);
	}
	
	@PostMapping("/supervisor")
	public void createUsuarioSupervisor(@RequestBody UsuarioCreateDTO usuarioDTO) {
		usuarioService.createUsuarioSupervisor(usuarioDTO);
	}
	
	@PostMapping("/vendedor")
	public void createUsuarioVendedor(@RequestBody UsuarioCreateDTO usuarioDTO) {
		usuarioService.createUsuarioVendedor(usuarioDTO);
	}
	
	@PostMapping("/usuario-externo")
	public void createUsuarioExterno(@RequestBody UsuarioExternoCreateDTO usuarioDTO) {
		usuarioService.createUsuarioExterno(usuarioDTO);
	}
}
