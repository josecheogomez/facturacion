/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.vendedorDao;
import sys.imp.vendedorDaoImp;
import sys.model.Vendedor;

/**
 *
 * @author José Gómez
 */
@ManagedBean
@ViewScoped
public class vendedorBean {

    private List<Vendedor> listaVendedor;
    private Vendedor vendedor;
    
    public vendedorBean() {
        vendedor = new Vendedor();
    }

    public List<Vendedor> getListaVendedor() {
        vendedorDao cDao= new vendedorDaoImp();
        listaVendedor=cDao.listarVendedor();
        return listaVendedor;
    }

    public void setListaVendedor(List<Vendedor> listaVendedor) {
        this.listaVendedor = listaVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    public void prepararNuevoVendedor()
    {
        vendedor=new Vendedor();
    }
    public void nuevoVendedor()
    {
        vendedorDao cDao=new vendedorDaoImp();
        cDao.newVendedor(vendedor);
    }
    public void modificarVendedor()
    {
        vendedorDao cDao=new vendedorDaoImp();
        cDao.updateVendedor(vendedor);
        vendedor=new Vendedor();
    }
    public void eliminarVendedor()
    {
        vendedorDao cDao=new vendedorDaoImp();
        cDao.deleteVendedor(vendedor);
        vendedor=new Vendedor();
    }
}
