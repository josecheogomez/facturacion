/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import sys.model.Usuario;

/**
 *
 * @author José Gómez
 */
public interface usuarioDao {
    public Usuario obtenerDatosPorUsuario(Usuario usuario);
    public Usuario login(Usuario usuario);
}
