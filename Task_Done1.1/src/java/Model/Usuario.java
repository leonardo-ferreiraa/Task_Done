/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alunos
 */
public class Usuario {
    
   private int USU_ID;
   private String USU_NOME;
   private int USU_IDADE;
   private String USU_TELEFONE;
   private String USU_USUARIO;
   private String USU_SENHA;

    public int getUSU_ID() {
        return USU_ID;
    }

    public void setUSU_ID(int USU_ID) {
        this.USU_ID = USU_ID;
    }

    public String getUSU_NOME() {
        return USU_NOME;
    }

    public void setUSU_NOME(String USU_NOME) {
        this.USU_NOME = USU_NOME;
    }

    public int getUSU_IDADE() {
        return USU_IDADE;
    }

    public void setUSU_IDADE(int USU_IDADE) {
        this.USU_IDADE = USU_IDADE;
    }

    public String getUSU_TELEFONE() {
        return USU_TELEFONE;
    }

    public void setUSU_TELEFONE(String USU_TELEFONE) {
        this.USU_TELEFONE = USU_TELEFONE;
    }
    
    public String getUSU_USUARIO() {
        return USU_USUARIO;
    }

    public void setUSU_USUARIO(String USU_USUARIO) {
        this.USU_USUARIO = USU_USUARIO;
    }
    
    public String getUSU_SENHA(){
        return USU_SENHA;
    }
    
    public void setUSU_SENHA(String USU_SENHA){
        this.USU_SENHA = USU_SENHA;
    }
    public boolean autenticar() throws ClassNotFoundException, SQLException {
        boolean aux = false;

        //Carregar Driver  e criar conexao
        Connection con = Conexao.getConexao();

        //contruir string sql bem formada e vinculada com a conexao
      
        String sql = "select USU_ID, USU_USUARIO, USU_SENHA "
                + "from TB_USUARIOS where USU_USUARIO = ? and USU_SENHA = ?";
        PreparedStatement comando = con.prepareStatement(sql);
        comando.setString(1, USU_USUARIO);
        comando.setString(2, USU_SENHA);

        System.out.println("SQL " + sql);

        //executar e tratar resultados
        ResultSet resultado = comando.executeQuery();
        if (resultado.next()) {
            setUSU_ID(resultado.getInt("USU_ID"));
            setUSU_USUARIO(resultado.getString("USU_USUARIO"));
            setUSU_SENHA(resultado.getString("USU_SENHA"));
            aux = true;
        }
        //Fecha conexao
        con.close();

        return aux;
    }
}
