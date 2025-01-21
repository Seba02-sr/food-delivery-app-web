package com.mycompany.tpdsw.model.relacion;

import com.mycompany.tpdsw.model.ItemPedido;
import com.mycompany.tpdsw.model.Pedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "pedido_item_pedido")
public class PedidoItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_pedido_id", referencedColumnName = "id")
    private ItemPedido itemPedido;

}