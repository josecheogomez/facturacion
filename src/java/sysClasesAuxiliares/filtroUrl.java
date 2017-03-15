/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysClasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author José Gómez
 */
public class filtroUrl implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        
        FacesContext facesContext=event.getFacesContext();
        //caturamos el nombre de la pagina actual
        String currentPage=facesContext.getViewRoot().getViewId();
        //variables booleanas que verifican si es la pagina de login la que se capturo
        boolean isPageLogin=currentPage.lastIndexOf("login.xhtml") > -1 ? true:false;
        //variable de session
        HttpSession session= (HttpSession) facesContext.getExternalContext().getSession(true);
        //caturamos al usuario en un object
        Object usuario=session.getAttribute("usuario");
        //si no es la pagina de logeo y el usuario es nulo lo redirijimos a la pagina
        if(!isPageLogin && usuario==null)
        {
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/login.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        //esta linea debe estar comentada para evitar errores en la autenticacion
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
