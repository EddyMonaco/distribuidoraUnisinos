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

import com.distribuicao.unisinos.dto.ProdutoCreateDTO;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.service.ProdutosService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	@Autowired
    private ProdutosService produtosService;
	
	@GetMapping
	public List<Produto> findAll(){ 
		return produtosService.findAll();
	}
	
	@GetMapping("/{codigo}")
	public Optional<Produto> findBySku(@PathVariable String sku) {
		return produtosService.findBySku(sku);
	}
	
	@GetMapping("/{nome}")
	public List<Produto> findByNomeContainingIgnoreCase(@PathVariable String nome) {
		return produtosService.findByNomeContainingIgnoreCase(nome);
	}
	
	@PostMapping
	public void createProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO) {
		produtosService.createProduto(produtoCreateDTO);
	}
}