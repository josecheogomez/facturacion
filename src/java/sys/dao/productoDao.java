/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Producto;

/**
 *
 * @author José Gómez
 */
public interface productoDao {
        public List<Producto> listarProducto();
        public void newProducto(Producto producto);
        public void updateProducto(Producto producto);
        public void deleteProducto(Producto producto);
}
