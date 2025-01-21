package com.mycompany.tpdsw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.tpdsw.model.Categoria;
import com.mycompany.tpdsw.model.TipoCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Método para buscar por nombre
    @Query("FROM Categoria c WHERE c.nombre = :nombre")
    Categoria findByNombre(@Param("nombre") String nombre);

    // Método para buscar por tipo de categoría
    @Query("FROM Categoria c WHERE c.tipo = :tipo")
    List<Categoria> findByTipoCategoria(@Param("tipo") TipoCategoria tipo);
}
