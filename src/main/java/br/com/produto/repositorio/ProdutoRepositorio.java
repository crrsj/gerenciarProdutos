package br.com.produto.repositorio;

import br.com.produto.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto,Long> {
    Produto findByCategoria(String categoria);
}
