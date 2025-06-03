package br.com.produto.servico;

import br.com.produto.dto.AtualizarProdutoDTO;
import br.com.produto.dto.BuscarProdutosDTO;
import br.com.produto.dto.CriarProdutoDTO;
import br.com.produto.entidade.Produto;
import br.com.produto.repositorio.ProdutoRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServico {

    private final ModelMapper modelMapper;
    private final ProdutoRepositorio produtoRepositorio;

    public Produto CriarProduto(CriarProdutoDTO criarProdutoDTO){
      if(criarProdutoDTO.getEstoque() <=4 ){
        throw new IllegalArgumentException("Estoque menor que 5 não pode ser cadastrado em sistema");
      }
        return produtoRepositorio.save(modelMapper.map(criarProdutoDTO,Produto.class));
    }

    public Produto realizarCompra(AtualizarProdutoDTO atualizarProdutoDTO){
        if(atualizarProdutoDTO.getEstoque() < atualizarProdutoDTO.getQuantidade()){
            throw new RuntimeException("Estoque insuficiente");
        }
            atualizarProdutoDTO.setTotal(atualizarProdutoDTO.getQuantidade() * atualizarProdutoDTO.getPreco());
            return produtoRepositorio.save(modelMapper.map(atualizarProdutoDTO, Produto.class));

    }

    public List<BuscarProdutosDTO> listarProdutos(){
        return produtoRepositorio.findAll()
                .stream().map(listar ->
                 modelMapper.map(listar,BuscarProdutosDTO.class))
                .collect(Collectors.toList());
    }

    public Produto buscarPorId(Long id){
        Optional<Produto>buscar = produtoRepositorio.findById(id);
        return buscar.orElseThrow();
    }

    public Produto atualizar(AtualizarProdutoDTO atualizarProdutoDTO){
        return produtoRepositorio.save(modelMapper.map(atualizarProdutoDTO,Produto.class));
    }

    public void excluirProduto(Long id){
        produtoRepositorio.deleteById(id);
    }

    public Produto buscarPorCategoria(String categoria){
        var produto =  produtoRepositorio.findByCategoria(categoria);
        if(produto == null){
            throw new EntityNotFoundException("Produto não existe para essa categoria");
        }
        return produto;
    }

}

