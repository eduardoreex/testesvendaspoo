package br.icev.vendas;

import br.icev.vendas.excecoes.QuantidadeInvalidaException;
import br.icev.vendas.excecoes.SemEstoqueException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

public class Estoque {
    private final Map<String, Integer> produtos = new HashMap<>();
    public void adicionarEstoque(String codigo, int quantidade) throws QuantidadeInvalidaException {

        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("quantidade deve ser maior que zero");
        }

        this.produtos.put(codigo, quantidade);

    }

    public int getDisponivel(String codigo) {
        return this.produtos.getOrDefault(codigo,0);
    }

    public void reservar(String codigo, int quantidade)
            throws SemEstoqueException, QuantidadeInvalidaException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException("Quantidade da reserva deve ser maior que zero");
        }
            int estoqueAtual = getDisponivel(codigo);
            if (quantidade > estoqueAtual) {
                throw new SemEstoqueException("Estoque insuficiente");
            }
            int novaQuantidade =  estoqueAtual-quantidade;

            this.produtos.put(codigo, novaQuantidade);
        }
    }

