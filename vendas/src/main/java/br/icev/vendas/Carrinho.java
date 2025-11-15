package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.math.RoundingMode;


public class Carrinho {
    private final Map<Produto, Integer> itens = new HashMap<>();

    public void adicionar(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade deve ser maior que zero");
        }
        int quantidadeAtual = this.itens.getOrDefault(produto, 0);
        int novaQuantidade = quantidadeAtual + quantidade;

        this.itens.put(produto, novaQuantidade);

    }
    public BigDecimal getSubtotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Produto, Integer> entry : this.itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            BigDecimal preco = produto.getPrecoUnitario();
            BigDecimal quantidadeBD = new BigDecimal(quantidade);
            BigDecimal subtotalItem = preco.multiply(quantidadeBD);
            total = total.add(subtotalItem);
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
    public BigDecimal getTotalCom(PoliticaDesconto politica) {
        BigDecimal subtotal = this.getSubtotal();
        BigDecimal totalComDesconto = politica.aplicar(subtotal);
        if (totalComDesconto.compareTo(BigDecimal.ZERO) < 0) {
            return new BigDecimal("0.00");
        }
        return totalComDesconto.setScale(2, RoundingMode.HALF_UP);
    }
    public int getTotalItens() {
        int total = 0;
        for (int quantidade : this.itens.values()){
            total = total + quantidade;
        }
        return total;
    }
}

