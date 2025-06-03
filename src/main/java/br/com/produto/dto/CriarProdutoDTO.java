package br.com.produto.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CriarProdutoDTO{
    private String categoria;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer estoque;
    private Double preco;
   // private Double total;

}
