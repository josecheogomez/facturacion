/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Cliente;

/**
 *
 * @author José Gómez
 */
public interface clienteDao {
        public List<Cliente> listarCliente();
        public void newCliente(Cliente cliente);
        public void updateCliente(Cliente cliente);
        public void deleteCliente(Cliente cliente);
        //este metodo se utilizara en la factura
        public Cliente obtenerClientePorCodigo(Session session, Integer codCliente)throws Exception;
}
