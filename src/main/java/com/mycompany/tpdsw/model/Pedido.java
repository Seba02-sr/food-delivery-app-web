package com.mycompany.tpdsw.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.tpdsw.model.relacion.PedidoItemPedido;
import com.mycompany.tpdsw.patronObserver.Observable;
import com.mycompany.tpdsw.patronObserver.Observer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "pedido")
public class Pedido implements Observable<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PedidoItemPedido> pedidoItemPedidos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @Builder.Default
    private Pago formaPago = null;

    @Transient
    @Builder.Default
    private List<Observer<Pedido>> observadores = new ArrayList<>();

    /**
     * Setea nuevo estado del pedido y notifica a los observadores sobre el cambio.
     * 
     * 
     * @param estado El nuevo estado a asignar
     */

    public void setEstado(Estado estado) {
        this.estado = estado;
        notificarObservadores();
    }

    /**
     * Devuelve una lista de items pedidos.
     * Extrae los objetos ItemPedido de la lista de PedidoItemPedido.
     * 
     * @return Lista de objetos ItemPedido asociados a PedidoItemPedido
     */
    public List<ItemPedido> getItems() {
        return pedidoItemPedidos.stream()
                .map(PedidoItemPedido::getItemPedido)
                .toList();
    }

    /**
     * Calcula el costo total del pedido, segun el precio y la cantidad
     * Aplica el recargo dependiendo la forma de pago.
     * 
     * @return El monto total una vez aplicado el recargo de la forma de pago.
     */

    public BigDecimal total() {
        BigDecimal total = totalSinRecargo();
        return formaPago.pagar(total);
    }

    /**
     * Calcula el costo total del pedico, sin aplicar el recargo
     * 
     * @return El monto total sin recargo.
     */
    public BigDecimal totalSinRecargo() {
        return pedidoItemPedidos.stream()
                .map(pedidoItem -> pedidoItem.getItemPedido().getItemMenu()
                        .getPrecio()
                        .multiply(new BigDecimal(pedidoItem.getItemPedido().getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Agrega un observador a la lista de observadores del pedido.
     * 
     * @param observer Es el observador que sera notificado
     */
    @Override
    public void addObserver(Observer<Pedido> observer) {
        this.observadores.add(observer);
    }

    /**
     * Notifica a todos los observadores sobre el cambio en el estado
     * Llama al metodo 'updateEstado' establecido en cada observador
     */
    @Override
    public void notificarObservadores() {

        for (Observer<Pedido> observer : observadores) {
            observer.updateEstado(this);
        }
    }

    /**
     * Devuelve el mismo pedido
     */
    @Override
    public Pedido get() {
        return this;
    }

    /**
     * Agrega un nuevo item pedido al pedido actual.
     * Crea una relación entre el pedido y el item pedido utilizando la clase
     * PedidoItemPedido.
     * 
     * @param itemPedido El item que se desea agregar al pedido.
     */
    public void agregarItemPedido(ItemPedido itemPedido) {
        PedidoItemPedido pedidoItemPedido = PedidoItemPedido.builder()
                .pedido(this)
                .itemPedido(itemPedido)
                .build();
        pedidoItemPedidos.add(pedidoItemPedido);
    }

    /**
     * Obtiene el vendedor asociado al primer item del pedido.
     * No hay posibilidad de realizar pedidos a varios vendedores en una misma
     * instancia.
     * 
     * @return El vendedor asociado al primer item del pedido, o null si no existe.
     */
    public Vendedor obtenerVendedor() {
        if (pedidoItemPedidos != null && !pedidoItemPedidos.isEmpty()) {
            PedidoItemPedido primerItemPedido = pedidoItemPedidos.get(0);
            ItemMenu itemMenu = primerItemPedido.getItemPedido().getItemMenu();
            if (itemMenu != null && itemMenu.getVendedor() != null) {
                return itemMenu.getVendedor();
            }
        }
        return null;
    }

    /**
     * Calcula la cantidad total de items en el pedido.
     * 
     * Suma las cantidades de todos los items pedidos en el pedido actual.
     * 
     * @return El total de items agregados al pedido.
     */
    public Integer cantidadItems() {
        return pedidoItemPedidos.stream()
                .mapToInt(pedidoItem -> pedidoItem.getItemPedido().getCantidad())
                .sum();
    }

}
