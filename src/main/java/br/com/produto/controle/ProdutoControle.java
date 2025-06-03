package br.com.produto.controle;

import br.com.produto.dto.AtualizarProdutoDTO;
import br.com.produto.dto.BuscarProdutosDTO;
import br.com.produto.dto.CriarProdutoDTO;
import br.com.produto.servico.ProdutoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoControle {

    private final ProdutoServico produtoServico;
    private final ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Endpoint responsável por cadastrar produtos.")
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<CriarProdutoDTO>criarProduto(@RequestBody CriarProdutoDTO criarProdutoDTO){
        var produto = produtoServico.CriarProduto(criarProdutoDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(produto, CriarProdutoDTO.class));
    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar produtos.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<BuscarProdutosDTO>>listarProdutos(){
        var listar= produtoServico.listarProdutos();
        return ResponseEntity.ok(listar);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar produto pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarProdutosDTO>buscarProduto(@PathVariable Long id){
        var buscar = produtoServico.buscarPorId(id);
        return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarProdutosDTO.class));
    }

    @PutMapping
    @Operation(summary = "Endpoint responsável por atualizar produtos.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarProdutoDTO>atualizarProdutos(@RequestBody AtualizarProdutoDTO atualizarProdutoDTO){
        var atualizar = produtoServico.atualizar(atualizarProdutoDTO);
        return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarProdutoDTO.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint responsável por deletar produto.")
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Void>excluirProduto(@PathVariable Long id){
        produtoServico.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/compra")
    @Operation(summary = "Endpoint responsável por cadastrar compra do produto.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<AtualizarProdutoDTO>realizarCompra(@RequestBody AtualizarProdutoDTO atualizarProdutoDTO){
       var compra = produtoServico.realizarCompra(atualizarProdutoDTO);
       return ResponseEntity.ok().body(modelMapper.map(compra, AtualizarProdutoDTO.class));
    }

    @GetMapping("/categoria")
    @Operation(summary = "Endpoint responsável por buscar o produto pela categoria.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<BuscarProdutosDTO>buscarProduto(@RequestParam("categoria")String categoria){
        var buscar = produtoServico.buscarPorCategoria(categoria);
        return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarProdutosDTO.class));
    }
}
