/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author gustavo
 */
public class Tempo {
    
    int unidade;
    
    public Tempo(int val){
        this.unidade=val;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }
    
    @Override
    public String toString(){
        
        return "\nUnidade:\t"+this.getUnidade()+"\n";
        
    }
    
}
