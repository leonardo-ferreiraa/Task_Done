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
import java.util.ArrayList;
import java.util.List;

public class Task_DoneDAO {
    
    public void cadastrarAgenda(Agenda a) throws ClassNotFoundException, SQLException {
        //se conecta com a classe conexao.java
        Connection con = Conexao.getConexao();
        //uma um comando do SQL para inserir um dado
        PreparedStatement comando = con.prepareStatement("insert into TB_TAREFAS(TR_TITULO,TR_TAREFA,TR_CONCLUIDO,TR_ATIVO)values(?,?,FALSE,FALSE)");
        comando.setString(1, a.getTR_TITULO());
        comando.setString(2, a.getTR_TAREFA());       
        comando.execute();
        con.close();
    }
    public void cadastrarUsuario(Usuario User) throws ClassNotFoundException, SQLException {
        //se conecta com a classe conexao.java
        Connection con = Conexao.getConexao();
        //uma um comando do SQL para inserir um dado
        PreparedStatement comando = con.prepareStatement("insert into TB_USUARIOS(USU_NOME,USU_IDADE,USU_TELEFONE,USU_USUARIO,USU_SENHA)values(?,?,?,?,?)");
        comando.setString(1, User.getUSU_NOME());
        comando.setString(2, User.getUSU_IDADE()); 
        comando.setString(2, User.getUSU_TELEFONE());
        comando.setString(2, User.getUSU_USUARIO());
        comando.setString(2, User.getUSU_SENHA());
        comando.execute();
        con.close();
    }

    public void deletarAgenda(Agenda a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from  where TR_ID = ?");
        comando.setInt(1, a.getTR_ID());
        comando.execute();
        con.close();
    }
    
    public void deletarUsuario(Usuario User) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from  where USU_ID = ?");
        comando.setInt(1, User.getUSU_ID());
        comando.execute();
        con.close();
    }

    public void updateAgenda(Agenda a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("update TB_TAREFAS set TR_TITULO = ?, TR_TAREFA = ?, where TR_ID = ?");
        comando.setString(1, a.getTR_TITULO());
        comando.setString(2, a.getTR_TAREFA());
        comando.setInt(3, a.getTR_ID());
        comando.execute();
        con.close();
    }

    public void select(Agenda a) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from TB_TAREFAS");
        comando.execute();
        con.close();
    }
    
    /*caso seja 
    public Produto consultarById(Produto p) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from produtos where id= ?");
        comando.setInt(1, p.getId());
        ResultSet rs = comando.executeQuery();
        Produto prod = new Produto();
        if (rs.next()) {
            prod.setId(rs.getInt("id"));
            prod.setDescricao(rs.getString("descricao"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setQuantidade(rs.getInt("quantidade"));
            prod.setFornecedor(rs.getString("fornecedor"));
            prod.setSetor(rs.getInt("setor"));
            prod.setData(rs.getString("data_validade"));
            prod.setTipo(rs.getString("tipo"));
            
        }
        return prod;
    }*/

    public List<Agenda> consultarAgendaUser() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("select TR_TITULO, TR_TAREFA from TB_TAREFAS where TR_USU_ID = ?");
        ResultSet rs = comando.executeQuery();
        
        List<Agenda> lprod = new ArrayList<Agenda>();
        while (rs.next()) {
            Agenda prod = new Agenda();
            prod.setTR_TITULO(rs.getString("TR_TITULO"));
            prod.setTR_TAREFA(rs.getString ("TR_TAREFA"));
            lprod.add(prod);
            
        }
        return lprod;
        
    }
}
