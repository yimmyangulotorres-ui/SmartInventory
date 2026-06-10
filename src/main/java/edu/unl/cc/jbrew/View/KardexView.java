package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Kardex.Kardex;

public class KardexView {

    public void showKardexEntry(Kardex kardex) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ENTRADA DE KARDEX                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID de Kardex: " + kardex.getIdKardex());
        System.out.println("║  Producto: " + kardex.getProduct().getName());
        System.out.println("║  Fecha: " + kardex.getDate());
        System.out.println("║  Tipo de Movimiento: " + kardex.getMovementType());
        System.out.println("║  Cantidad: " + kardex.getQuantity());
        System.out.println("║  Saldo: " + kardex.getBalance());
        System.out.println("║  Descripción: " + kardex.getDescription());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
