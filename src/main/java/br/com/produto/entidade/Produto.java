package br.com.produto.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="tb_produtos")
@Data
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Integer estoque;
    private Double preco;
    private Double total;

}
