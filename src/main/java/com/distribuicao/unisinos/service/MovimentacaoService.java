package com.distribuicao.unisinos.service;

import com.distribuicao.unisinos.model.AreaEstoque;
import com.distribuicao.unisinos.model.MovimentacaoEstoque;
import com.distribuicao.unisinos.model.Produto;
import com.distribuicao.unisinos.model.Usuario;
import com.distribuicao.unisinos.repository.AreaEstoqueRepository;
import com.distribuicao.unisinos.repository.MovimentacaoRepository;
import com.distribuicao.unisinos.repository.ProdutoRepository;
import com.distribuicao.unisinos.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AreaEstoqueRepository areaEstoqueRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra uma entrada de produto no estoque: atualiza a quantidade total e
     * cria um registro de movimentação do tipo ENTRADA.
     */
    @Transactional
    public MovimentacaoEstoque registrarEntrada(Integer produtoId, String codigoArea, Integer usuarioId, int quantidade, String observacao) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        AreaEstoque area = areaEstoqueRepository.findByCodigoArea(codigoArea)
                .orElseThrow(() -> new RuntimeException("Área de estoque não encontrada: " + codigoArea));

       Usuario usuario = usuarioRepository.findById(usuarioId)
    		   .orElseThrow(() -> new RuntimeException("Usuario nao econtrado "));
    
        // Atualiza quantidade total
        produto.setQuantidadeTotal(produto.getQuantidadeTotal() + quantidade);
        produtoRepository.save(produto);

        MovimentacaoEstoque mov = new MovimentacaoEstoque();
        mov.setProduto(produto);
        mov.setAreaEstoque(area);
        mov.setUsuario(usuario);
        mov.setTipoMovimentacao(MovimentacaoEstoque.TipoMovimentacao.ENTRADA);
        mov.setQuantidade(quantidade);
        mov.setObservacao(observacao);

        return movimentacaoRepository.save(mov);
    }

    /**
     * Registra uma saída de produto do estoque: valida estoque disponível,
     * atualiza a quantidade total e cria um registro de movimentação do tipo SAIDA.
     */
    @Transactional
    public MovimentacaoEstoque registrarSaida(Integer produtoId, String codigoArea, Integer usuarioId, int quantidade, String observacao) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        AreaEstoque area = areaEstoqueRepository.findByCodigoArea(codigoArea)
                .orElseThrow(() -> new RuntimeException("Área de estoque não encontrada: " + codigoArea));

        if (produto.getQuantidadeTotal() < quantidade) {
            throw new RuntimeException("Estoque insuficiente. Disponível: " + produto.getQuantidadeTotal());
        }

        Usuario usuario = null;
        if (usuarioId != null) {
            usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        }

        // Atualiza quantidade total
        produto.setQuantidadeTotal(produto.getQuantidadeTotal() - quantidade);
        produtoRepository.save(produto);

        MovimentacaoEstoque mov = new MovimentacaoEstoque();
        mov.setProduto(produto);
        mov.setAreaEstoque(area);
        mov.setUsuario(usuario);
        mov.setTipoMovimentacao(MovimentacaoEstoque.TipoMovimentacao.SAIDA);
        mov.setQuantidade(quantidade);
        mov.setObservacao(observacao);

        return movimentacaoRepository.save(mov);
    }

    /**
     * Registra uma transferência entre duas áreas: cria uma saída na área de origem
     * e uma entrada na área de destino. A quantidade total do produto NÃO é alterada.
     * Retorna as duas movimentações (saída primeiro, depois entrada).
     */
    @Transactional
    public List<MovimentacaoEstoque> registrarTransferencia(Integer produtoId, String codigoAreaOrigem, String codigoAreaDestino, Integer usuarioId, int quantidade, String observacao) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (codigoAreaOrigem == null || codigoAreaDestino == null) {
            throw new IllegalArgumentException("Código das áreas de origem e destino são obrigatórios");
        }

        if (codigoAreaOrigem.equals(codigoAreaDestino)) {
            throw new IllegalArgumentException("Área de origem e destino devem ser diferentes");
        }

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        AreaEstoque origem = areaEstoqueRepository.findByCodigoArea(codigoAreaOrigem)
                .orElseThrow(() -> new RuntimeException("Área de estoque (origem) não encontrada: " + codigoAreaOrigem));

        AreaEstoque destino = areaEstoqueRepository.findByCodigoArea(codigoAreaDestino)
                .orElseThrow(() -> new RuntimeException("Área de estoque (destino) não encontrada: " + codigoAreaDestino));

        // Não é possível validar estoque por área sem modelo específico, apenas verifica estoque global
        if (produto.getQuantidadeTotal() < quantidade) {
            throw new RuntimeException("Estoque insuficiente para transferência. Disponível: " + produto.getQuantidadeTotal());
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        

        MovimentacaoEstoque saida = new MovimentacaoEstoque();
        saida.setProduto(produto);
        saida.setAreaEstoque(origem);
        saida.setUsuario(usuario);
        saida.setTipoMovimentacao(MovimentacaoEstoque.TipoMovimentacao.SAIDA);
        saida.setQuantidade(quantidade);
        saida.setObservacao("Transferência para " + codigoAreaDestino + (observacao != null ? ": " + observacao : ""));
        movimentacaoRepository.save(saida);

        // Cria entrada na área de destino
        MovimentacaoEstoque entrada = new MovimentacaoEstoque();
        entrada.setProduto(produto);
        entrada.setAreaEstoque(destino);
        entrada.setUsuario(usuario);
        entrada.setTipoMovimentacao(MovimentacaoEstoque.TipoMovimentacao.ENTRADA);
        entrada.setQuantidade(quantidade);
        entrada.setObservacao("Transferência de " + codigoAreaOrigem + (observacao != null ? ": " + observacao : ""));
        movimentacaoRepository.save(entrada);

        List<MovimentacaoEstoque> resultado = new ArrayList<>();
        resultado.add(saida);
        resultado.add(entrada);
        return resultado;
    }

    @Transactional
    public List<MovimentacaoEstoque> listarPorProduto(Integer produtoId) {
        List<MovimentacaoEstoque> movs = movimentacaoRepository.findByProdutoId(produtoId);
        // Initialize lazy associations while in transaction
        movs.forEach(m -> {
            if (m.getProduto() != null) {
                m.getProduto().getId();
                m.getProduto().getNome();
            }
            if (m.getAreaEstoque() != null) {
                m.getAreaEstoque().getCodigoArea();
            }
            if (m.getUsuario() != null) {
                m.getUsuario().getId();
            }
        });
        return movs;
    }

    @Transactional
    public List<MovimentacaoEstoque> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        List<MovimentacaoEstoque> movs = movimentacaoRepository.findByDataMovimentacaoBetween(inicio, fim);
        // Initialize lazy associations while in transaction
        movs.forEach(m -> {
            if (m.getProduto() != null) {
                m.getProduto().getId();
                m.getProduto().getNome();
            }
            if (m.getAreaEstoque() != null) {
                m.getAreaEstoque().getCodigoArea();
            }
            if (m.getUsuario() != null) {
                m.getUsuario().getId();
            }
        });
        return movs;
    }

}