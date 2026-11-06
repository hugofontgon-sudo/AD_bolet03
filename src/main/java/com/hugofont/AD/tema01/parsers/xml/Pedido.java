package com.hugofont.AD.tema01.parsers.xml;

import java.util.List;

public class Pedido {
    private final String id;
    private final List<ItemPedido> items;
    private final double total;

    public Pedido(String id, List<ItemPedido> items, double total) {
        this.id = id;
        this.items = items;
        this.total = total;
    }

    public static Pedido[] parsearPedidos(String s) {

    }

    public String getId() {
        return id;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    // Método para recalcular el total sumando (cantidad * precioUnitario) de sus items
    public double calcularTotalReal() {
        return items.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    // Validación para comprobar si coincide con el elemento <total> del XML
    public boolean validarTotal() {
        return Math.abs(calcularTotalReal() - total) < 0.001;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}