package com.mycompany.tpdsw.model;

import com.mycompany.tpdsw.model.relacion.PedidoItemPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    private ItemMenu itemMenu;

    @ManyToOne
    @JoinColumn(name = "pedido_item_pedido_id")
    @Builder.Default
    private PedidoItemPedido pedidoItemPedido = null;
}
