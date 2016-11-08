/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.productoDao;
import sys.imp.productoDaoImp;
import sys.model.Producto;

/**
 *
 * @author José Gómez
 */
@ManagedBean
@ViewScoped
public class productoBean {

    private List<Producto> listaProducto;
    private Producto producto;
    
    public productoBean() {
        producto = new Producto();
    }

    public List<Producto> getListaProducto() {
        productoDao cDao= new productoDaoImp();
        listaProducto=cDao.listarProducto();
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public void prepararNuevoProducto()
    {
        producto=new Producto();
    }
    public void nuevoProducto()
    {
        productoDao cDao=new productoDaoImp();
        cDao.newProducto(producto);
    }
    public void modificarProducto()
    {
        productoDao cDao=new productoDaoImp();
        cDao.updateProducto(producto);
        producto=new Producto();
    }
    public void eliminarProducto()
    {
        productoDao cDao=new productoDaoImp();
        cDao.deleteProducto(producto);
        producto=new Producto();
    }
}
