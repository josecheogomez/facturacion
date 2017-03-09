/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import org.hibernate.Session;
import sys.model.Factura;

/**
 *
 * @author José Gómez
 */
public interface facturaDao {
    
    //metodo para obtener ultimo nro factura
    public Factura obtenerUltimoRegistro(Session session)throws Exception;
    //averiguar si hay registro en en la tabla factura
    public Long obtenerTotalRegistrosEnFactura(Session session);
    
}
