/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author alunos
 */
public class Agenda {
    
    private int TR_ID;
    private String TR_TITULO;
    private String TR_TAREFA;
    private boolean TR_CONCLUIDO;
    private boolean TR_ATIVO;
    private int TR_USU_ID;
    
    public int getTR_ID() {
        return TR_ID;
    }

    public void setId(int TR_ID) {
        this.TR_ID = TR_ID;
    }
    
    public String getTR_TITULO(){
        return TR_TITULO;
    }
    
    public void setTR_TITULO(String TR_TITULO){
        this.TR_TITULO = TR_TITULO;
    }
    public String getTR_TAREFA(){
        return TR_TAREFA;
    }
    
    public void setTR_TAREFA(String TR_TAREFA){
        this.TR_TAREFA = TR_TAREFA;
    }
    
    public boolean setTR_CONCLUIDO(){
        return TR_CONCLUIDO;
    }
    
    public void setTR_CONCLUIDO(boolean TR_CONCLUIDO){
        this.TR_CONCLUIDO = TR_CONCLUIDO;
    }
    
    public boolean setTR_ATIVO(){
        return TR_ATIVO;
    }
    
    public void setTR_USU_ID(int TR_USU_ID){
        this.TR_USU_ID = TR_USU_ID;
    }
}


