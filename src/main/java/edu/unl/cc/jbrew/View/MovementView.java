package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Movements.Movement;
import edu.unl.cc.jbrew.Domain.Movements.ProductMovement;

public class MovementView {

    public void showMovement(Movement movement) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               DATOS DEL MOVIMIENTO                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID del Movimiento: " + movement.getIdMovement());
        System.out.println("║  Tipo de Movimiento: " + movement.getMovementType());
        System.out.println("║  Estado: " + movement.getStatus());
        System.out.println("║  Fecha: " + movement.getDate());
        System.out.println("║  Descripción: " + movement.getDescription());
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║                   PRODUCTOS DEL MOVIMIENTO                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        for (ProductMovement productMovement : movement.getProductMovementList()) {
            System.out.println("║  Producto: " + productMovement.getProduct().getName());
            System.out.println("║  Cantidad: " + productMovement.getQuantity());
            System.out.println("║  Precio Unitario: $" + productMovement.getUnitPrice());
            System.out.println("║  Subtotal: $" + productMovement.getSubtotal());
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
