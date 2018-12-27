/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpdtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class AgenciaDTO {
    
    private String nomeAgencia;
    private int id;
    private List<CompanhiaDTO> companhias;
    private List<OperadorDTO> operadores;
 
    public AgenciaDTO(String name){
        this.nomeAgencia=name;
        this.id=1;
        this.companhias=new ArrayList<CompanhiaDTO>();
        this.operadores=new ArrayList<OperadorDTO>();
    }
    
    public AgenciaDTO(int id){
        this.id=id;
        this.companhias=new ArrayList<CompanhiaDTO>();
        this.operadores=new ArrayList<OperadorDTO>();
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public List<CompanhiaDTO> getCompanhias() {
        return companhias;
    }

    public List<OperadorDTO> getOperadores() {
        return operadores;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public void setCompanhias(List<CompanhiaDTO> companhias) {
        this.companhias = companhias;
    }

    public void setOperadores(List<OperadorDTO> operadores) {
        this.operadores = operadores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        
        String info_ag="";
        
        info_ag+=this.getNomeAgencia()+"\n";
        info_ag+="Companhias\n";
        for(CompanhiaDTO x : this.companhias){
            info_ag+=x.toString();
        }
        info_ag+="Operadores:\n";
        for(OperadorDTO x: this.operadores){
            info_ag+=x.toString();
        }
    
        return info_ag;
    }
}
    
