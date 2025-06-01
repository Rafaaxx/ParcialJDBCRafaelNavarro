package org.example.dao;

import java.util.List;

public interface VuelosDAO<T> {
    int crear(T clase);

    T buscarPorId(int id);

    List<T> listarTodos();

    boolean actualizar(T clase);

    boolean eliminar(int id);
}
