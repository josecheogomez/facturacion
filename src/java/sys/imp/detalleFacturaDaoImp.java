/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import org.hibernate.Session;
import sys.dao.detalleFacturaDao;
import sys.model.Detallefactura;

/**
 *
 * @author José Gómez
 */
public class detalleFacturaDaoImp implements detalleFacturaDao{

    @Override
    public boolean guardarVentaDetalleFactura(Session session, Detallefactura detallefactura) throws Exception {
         //To change body of generated methods, choose Tools | Templates.
    session.save(detallefactura);
    return true;
    }
    
}
