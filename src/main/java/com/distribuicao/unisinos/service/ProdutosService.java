package com.distribuicao.unisinos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.distribuicao.unisinos.dto.ProdutoCreateDTO;
import com.distribuicao.unisinos.dto.ProdutoUpdateDTO;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.model.UsuarioExterno;
import com.distribuicao.unisinos.repository.ProdutoRepository;
import com.distribuicao.unisinos.repository.UsuarioExternoRepository;

@Service
public class ProdutosService {

	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private UsuarioExternoRepository usuarioExternoRepository;
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> findById(Integer id) {
		return produtoRepository.findById(id);
	}
	
	public Optional<Produto> findBySku(String sku) {
		return produtoRepository.findBySku(sku);
	}
	
	public List<Produto> findByNomeContainingIgnoreCase(String nome){
		return produtoRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	public Produto createProduto(ProdutoCreateDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setSku(produtoDTO.getSku());
		produto.setQuantidadeTotal(produtoDTO.getQuantidadeTotal());
		produto.setPrecoCusto(produtoDTO.getPrecoCusto());
		produto.setPrecoVenda(produtoDTO.getPrecoVenda());
		
		if(produtoDTO.getFornecedorId()!= null) {
			UsuarioExterno fornecedor = usuarioExternoRepository.findById(produtoDTO.getFornecedorId())
					.orElseThrow(()-> new RuntimeException("Fornecedor não encontrado"));
			produto.setFornecedor(fornecedor);	
		}
		
		return produtoRepository.save(produto);
	}
	
	public void deleteProdutoById(Integer id) {
		produtoRepository.deleteById(id);
	}
	
	public Optional<Produto> updateProdutoById(Integer id, ProdutoUpdateDTO produtoUpdateDTO) {
		if(produtoUpdateDTO.getNome() == null || produtoUpdateDTO.getNome().isBlank() ||
			    produtoUpdateDTO.getSku() == null || produtoUpdateDTO.getSku().isBlank() ||
			    produtoUpdateDTO.getPrecoCusto() == null ||
			    produtoUpdateDTO.getPrecoVenda() == null ||
			    produtoUpdateDTO.getQuantidadeTotal() == null) {
			throw new IllegalArgumentException("Todos os campos devem ser preenchidos corretamente.");
		}
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Produto não encontrado"));
		
		produto.setNome(produtoUpdateDTO.getNome());
		produto.setSku(produtoUpdateDTO.getSku());
		produto.setQuantidadeTotal(produtoUpdateDTO.getQuantidadeTotal());
		produto.setPrecoCusto(produtoUpdateDTO.getPrecoCusto());
		produto.setPrecoVenda(produtoUpdateDTO.getPrecoVenda());
		
		if(produtoUpdateDTO.getFornecedorId()!= null) {
			UsuarioExterno fornecedor = usuarioExternoRepository.findById(produtoUpdateDTO.getFornecedorId())
					.orElseThrow(()-> new RuntimeException("Fornecedor não encontrado"));
			produto.setFornecedor(fornecedor);	
		}
		
		Produto produtoAtualizado = produtoRepository.save(produto);
		return Optional.of(produtoAtualizado);
	}
}

