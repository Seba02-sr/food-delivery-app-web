package com.mycompany.tpdsw.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "item_menu")
public abstract class ItemMenu { // Items que hay en un restaurante/vendedor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @ManyToOne
    private Categoria categoria;

    @Builder.Default
    private Boolean activo = true;

    @Column(name = "fecha_eliminacion")
    @Builder.Default
    private LocalDate fechaEliminacion = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    public abstract Double peso();

    public abstract boolean esComida();

    public abstract boolean esBebida();

    public abstract boolean aptoVegano();

}
