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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuário (Internos e Externos)")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Listar todos os usuários externos", description = "Retorna uma lista completa de todos os usuários externos cadastrados.")
	@ApiResponse(responseCode = "200", description = "Lista de usuários encontrada com sucesso.")
	@GetMapping("/usuario-externo")
	public List<UsuarioExterno> findAllUsuariosExternos(){
		return usuarioService.findAllUsuariosExternos();
	}
	
	@Operation(summary = "Buscar usuários externos por nome", description = "Retorna uma lista de usuários externos que correspondem ao nome fornecido.")
	@ApiResponse(responseCode = "200", description = "Usuários externos encontrados com sucesso.")
	@GetMapping("/usuario-externo/byNome/{nome}")
	public List<UsuarioExterno> findAllUsuarioExternoByNome(@PathVariable String nome){
		return usuarioService.findUsuarioExternoByNome(nome);
	}
	
	@Operation(summary = "Buscar usuário externos por email", description ="Retorna uma lista de usuários externos que correspondem ao email fornecido.")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Usuário externo encontrado com sucesso."),
	        @ApiResponse(responseCode = "404", description = "Usuário externo não encontrado para o e-mail fornecido.")
	})
	@GetMapping("/usuario-externo/byEmail/{email}")
	public UsuarioExterno findAllUsuarioExternoByEmail(@PathVariable String email){
		return usuarioService.findUsuarioExternoByEmail(email);
	}
	
	@Operation(summary = "Buscar usuário externo por CNPJ/CPF", description = "Retorna um usuário externo específico com base no seu CNPJ ou CPF.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário externo encontrado."),
        @ApiResponse(responseCode = "404", description = "Usuário externo não encontrado.")
    })
	@GetMapping("/usuario-externo/byCnpjCpf/{cnpjCpf}")
	public Optional<UsuarioExterno> findUsuarioExternoByCnpjCpf(@PathVariable String cnpjCpf){
		return usuarioService.findUsuarioExternoByCnpjCpf(cnpjCpf);
	}
	
	@Operation(summary = "Buscar usuários externos por tipo", description = "Retorna uma lista de usuários externos com base no tipo (CLIENTE ou FORNECEDOR).")
	@ApiResponse(responseCode = "200", description = "Lista de usuários externos por tipo retornada com sucesso.")
	@GetMapping("/usuario-externo/byTipo/{tipo}")
	public List<UsuarioExterno> findUsuarioExternoByTipo(@PathVariable UsuarioExterno.TipoExterno tipo){
		return usuarioService.findUsuarioExternoByTipo(tipo);
	}
	
	@Operation(summary = "Listar todos os vendedores", description = "Retorna uma lista de todos os usuários internos do tipo Vendedor.")
	@ApiResponse(responseCode = "200", description = "Lista de vendedores retornada com sucesso.")
	@GetMapping("/vendedores")
	public List<Vendedor> findAllVendedores(){
		return usuarioService.findAllVendedores();
	}
	
	@Operation(summary = "Listar todos os supervisores", description = "Retorna uma lista de todos os usuários internos do tipo Supervisor.")
	@ApiResponse(responseCode = "200", description = "Lista de supervisores retornada com sucesso.")
	@GetMapping("/supervisores")
	public List<Supervisor> findAllSupervisores(){
		return usuarioService.findAllSupervisores();
	}
	
	@Operation(summary = "Buscar usuário interno por e-mail", description = "Busca um usuário interno (Vendedor ou Supervisor) pelo e-mail.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
	@GetMapping("/byEmail/{email}")
	public Optional<Usuario> findByEmail(@PathVariable String email){
		return usuarioService.findByEmail(email);
	}
	
	@Operation(summary = "Buscar usuários internos por nome", description = "Retorna uma lista de usuários internos que correspondem ao nome fornecido.")
	@ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso.")
	@GetMapping("/byNome/{nome}")
	public List<Usuario> findByNome(@PathVariable String nome){
		return usuarioService.findByNome(nome);
	}
	
	@Operation(summary = "Criar um novo usuário Supervisor", description = "Cadastra um novo usuário interno com o perfil de Supervisor.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Supervisor criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos.")
    })
	@PostMapping("/supervisor")
	public void createUsuarioSupervisor(@RequestBody UsuarioCreateDTO usuarioDTO) {
		usuarioService.createUsuarioSupervisor(usuarioDTO);
	}
	
	@Operation(summary = "Criar um novo usuário Vendedor", description = "Cadastra um novo usuário interno com o perfil de Vendedor.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Vendedor criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos.")
    })
	@PostMapping("/vendedor")
	public void createUsuarioVendedor(@RequestBody UsuarioCreateDTO usuarioDTO) {
		usuarioService.createUsuarioVendedor(usuarioDTO);
	}
	
	@Operation(summary = "Criar um novo usuário externo", description = "Cadastra um novo usuário externo (Cliente ou Fornecedor).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário externo criado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos.")
    })
	@PostMapping("/usuario-externo")
	public void createUsuarioExterno(@RequestBody UsuarioExternoCreateDTO usuarioDTO) {
		usuarioService.createUsuarioExterno(usuarioDTO);
	}
}
