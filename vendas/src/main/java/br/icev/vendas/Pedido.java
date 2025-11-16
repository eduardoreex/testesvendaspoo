package br.icev.vendas;

import java.math.BigDecimal;
import java.util.Map;

public class Pedido {
    private final Map<String, Integer> itensPorCodigo;
    private final BigDecimal totalPago;
    private final String codigoAutorizacao;
    private final Status status;

    public enum Status {PAGO}

    public Pedido(Map<String, Integer> itensPorCodigo, BigDecimal totalPago,
                  String codigoAutorizacao, Status status) {
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
        this.totalPago = totalPago;
        this.itensPorCodigo = itensPorCodigo;
    }


    public BigDecimal getTotalPago() {
        return totalPago;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public Status getStatus() {
        return status;
    }

    public int getQuantidadeItem(String codigo) {
        return this.itensPorCodigo.getOrDefault(codigo, 0);
    }
}
