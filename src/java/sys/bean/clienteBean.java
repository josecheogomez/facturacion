/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sys.dao.clienteDao;
import sys.imp.clienteDaoImp;
import sys.model.Cliente;

/**
 *
 * @author José Gómez
 */
@ManagedBean
@ViewScoped
public class clienteBean {

    private List<Cliente> listaCliente;
    private Cliente cliente;
    public clienteBean() {
        cliente = new Cliente();
    }

    public List<Cliente> getListaCliente() {
        clienteDao cDao= new clienteDaoImp();
        listaCliente=cDao.listarCliente();
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void prepararNuevoCliente()
    {
        cliente=new Cliente();
    }
    public void nuevoCliente()
    {
        clienteDao cDao=new clienteDaoImp();
        cDao.newCliente(cliente);
    }
    public void modificarCliente()
    {
        clienteDao cDao=new clienteDaoImp();
        cDao.updateCliente(cliente);
        cliente=new Cliente();
    }
    public void eliminarCliente()
    {
        clienteDao cDao=new clienteDaoImp();
        cDao.deleteCliente(cliente);
        cliente=new Cliente();
    }
}
