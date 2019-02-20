/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intermediario;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pedro
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    public boolean isOperator() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String role = (String) session.getAttribute("Role");

        if (role != null && role.equals("Operador")) {
            return true;
        } else {
            return false;
        }
    }
    
    

    public boolean isClient() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String role = (String) session.getAttribute("Role");

        if (role != null && role.equals("Cliente")) {
            return true;
        } else {
            return false;
        }
    }
    



    public boolean isLoggedIn(String tipo_role) {
       if(tipo_role.equals("Cliente")){
           return isClient();
       }
       else if(tipo_role.equals("Operador")){
           return isOperator();
       }
       else
           return false;
    }
     
         //caso nao esteja logado, é um visitante
//   public boolean isNotLoggedIn(){
//        return isVisitante() ;
//    }

}
