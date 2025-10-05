package com.distribuicao.unisinos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuicao.unisinos.dto.ProdutoCreateDTO;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.repository.ProdutoRepository;

@Service
public class ProdutosService {

	@Autowired
    private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> findBySku(String sku) {
		return produtoRepository.findBySku(sku);
	}
	
	public List<Produto> findByNomeContainingIgnoreCase(String nome){
		return produtoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public Produto createProduto(ProdutoCreateDTO produtoDTO) {
		Produto produto = new Produto();
		produto.get
	}
}

