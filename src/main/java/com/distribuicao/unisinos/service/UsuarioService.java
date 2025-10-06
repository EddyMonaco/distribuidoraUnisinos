package com.distribuicao.unisinos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuicao.unisinos.dto.UsuarioCreateDTO;
import com.distribuicao.unisinos.dto.UsuarioExternoCreateDTO;
import com.distribuicao.unisinos.model.Endereco;
import com.distribuicao.unisinos.model.Supervisor;
import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.model.UsuarioExterno;
import com.distribuicao.unisinos.model.Vendedor;
import com.distribuicao.unisinos.repository.SupervisorRepository;
import com.distribuicao.unisinos.repository.UsuarioExternoRepository;
import com.distribuicao.unisinos.repository.UsuarioRepository;
import com.distribuicao.unisinos.repository.VendedorRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@Autowired
	private UsuarioExternoRepository usuarioExternoRepository;
	
	public List<UsuarioExterno> findAllUsuariosExternos(){
		return usuarioExternoRepository.findAll();
	}
	
	public List<UsuarioExterno> findUsuarioExternoByNome(String nome){
		return usuarioExternoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public Optional<UsuarioExterno> findUsuarioExternoByEmail(String email){
		return usuarioExternoRepository.findUsuarioExternoByEmail(email);
	}
	
	public Optional<UsuarioExterno> findUsuarioExternoByCnpjCpf(String cnpjCpf) {
		return usuarioExternoRepository.findUsuarioExternoByCnpjCpf(cnpjCpf);
	}
	
	public List<UsuarioExterno> findUsuarioExternoByTipo(UsuarioExterno.TipoExterno tipo) {
		return usuarioExternoRepository.findUsuarioExternoByTipoExterno(tipo);
	}
	
	public List<Vendedor> findAllVendedores(){
		return vendedorRepository.findAll();
	}
	
	public List<Supervisor> findAllSupervisores(){
		return supervisorRepository.findAll();
	}
	
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public List<Usuario> findByNome(String nome){
		return usuarioRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public UsuarioExterno createUsuarioExterno(UsuarioExternoCreateDTO usuarioDTO) {
		UsuarioExterno usuario = new UsuarioExterno();
		usuario.setCnpjCpf(usuarioDTO.getCnpjCpf());
		usuario.setTipoExterno(usuarioDTO.getTipoExterno());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(true);
		
		List<Endereco> enderecos = usuarioDTO.getEnderecos().stream().map(e -> {
			Endereco endereco = new Endereco();
			endereco.setEstado(e.getEstado());
			endereco.setCidade(e.getCidade());
			endereco.setBairro(e.getBairro());
			endereco.setRua(e.getRua());
			endereco.setNumero(e.getNumero());
			endereco.setCep(e.getCep());
			endereco.setUsuarioExterno(usuario);
			
			return endereco;
		}).toList();
		
		usuario.setEnderecos(enderecos);
		return usuarioRepository.save(usuario);
	}
	
	public Vendedor createUsuarioVendedor(UsuarioCreateDTO usuarioDTO) {
		Vendedor usuario = new Vendedor();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
	
	public Supervisor createUsuarioSupervisor(UsuarioCreateDTO usuarioDTO) {
		Supervisor usuario = new Supervisor();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
}
