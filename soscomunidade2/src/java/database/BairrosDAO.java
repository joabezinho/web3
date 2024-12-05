package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;
import model.Bairros;

public class BairrosDAO {
    private static Connection conn;
    
    public BairrosDAO() throws ClassNotFoundException, SQLException {
        conn = Conexao.getConn();
    }
    
    public ArrayList<Bairros> getAllTasks(Usuario u) throws SQLException {
        
        ArrayList<Bairros> list = new ArrayList();
        
        String query = "select * from Bairross where id_usuario = " + u.getId();
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        while( res.next() ) {
            Bairros task = new Bairros();
            
            task.setId( res.getInt("id") );
            task.setTitulo(res.getString("titulo") );
            task.setDescricao(res.getString("descricao") );
            task.setStatusBairros(res.getString("status_Bairros") );
            task.setUsuario( u );
            
            list.add(task);
        }
        
        prep.close();
        return list;
    }
    
    
    public void setNewTask(Bairros task, int id) throws SQLException {
        String query = "insert into Bairross(titulo, descricao, status_Bairros, id_usuario) "
                     + "values(?, ?, ?, ?)";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, task.getTitulo() );
        prep.setString(2, task.getDescricao() );
        prep.setString(3, task.getStatusBairros() );
        prep.setInt(4, id );
                
        prep.execute();
        prep.close();
    }
    
    
    public void deleteTask(int id) throws SQLException {
        String query = "delete from Bairross "
                     + "where id = " + id ;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.execute();
        prep.close();
    }
    
    
    public Bairros getOneTask(int id) throws SQLException {
        String query = "select * from Bairross where id = " + id;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        Bairros t = new Bairros();
        
        if( res.next() ) {
            t.setId(id);
            t.setTitulo(res.getString("titulo"));
            t.setDescricao(res.getString("descricao"));
            t.setStatusBairros(res.getString("status_Bairros"));
        }
        
        prep.close();
        return t;
    }
    
    
    public void updateTask(Bairros task) throws SQLException {
        String query = "update Bairross set titulo = ?, "
                     + "descricao = ?, status_Bairros = ? "
                     + "where id = ?";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, task.getTitulo() );
        prep.setString(2, task.getDescricao() );
        prep.setString(3, task.getStatusBairros() );
        prep.setInt(4, task.getId() );
        
        prep.execute();
        prep.close();
    }
}