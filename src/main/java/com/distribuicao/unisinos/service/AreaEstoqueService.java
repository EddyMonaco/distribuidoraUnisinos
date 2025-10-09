package com.distribuicao.unisinos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribuicao.unisinos.dto.AreaEstoqueCreateDTO;
import com.distribuicao.unisinos.model.AreaEstoque;
import com.distribuicao.unisinos.repository.AreaEstoqueRepository;

@Service
public class AreaEstoqueService {
	@Autowired
	private AreaEstoqueRepository areaEstoqueRepository;
	
	public List<AreaEstoque> findAll(){
		return areaEstoqueRepository.findAll();
	}
	
	public Optional<AreaEstoque> findByCodigoArea(String codigoArea){
		return areaEstoqueRepository.findByCodigoArea(codigoArea);
	}
	
	public Optional<AreaEstoque> findById(Integer id){
		return areaEstoqueRepository.findById(id);
	}
	
	public AreaEstoque createAreaEstoque(AreaEstoqueCreateDTO areaEstoqueDTO) {
		AreaEstoque areaEstoque = new AreaEstoque();
		areaEstoque.setCodigoArea(areaEstoqueDTO.getCodigoArea());
		areaEstoque.setDescricao(areaEstoqueDTO.getDescricao());
		
		return areaEstoqueRepository.save(areaEstoque);
	}
}
