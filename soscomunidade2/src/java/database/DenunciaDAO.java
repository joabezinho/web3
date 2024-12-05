package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;
import model.Denuncia;

public class DenunciaDAO {
    private static Connection conn;
    
    public DenunciaDAO() throws ClassNotFoundException, SQLException {
        conn = Conexao.getConn();
    }
    
    public ArrayList<Denuncia> getAllDenuncias(Usuario u) throws SQLException {
        
        ArrayList<Denuncia> list = new ArrayList();
        
        String query = "select * from denuncias where id_usuario = " + u.getId();
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        while( res.next() ) {
            Denuncia d = new Denuncia();
            
            d.setId( res.getInt("id") );
            d.setDenuncia(res.getString("denuncia") );
            d.setDescricao(res.getString("descricao") );
            d.setStatusDenuncia(res.getString("status_denuncia") );
            d.setUsuario( u );
            
            list.add(d);
        }
        
        prep.close();
        return list;
    }
    
    
    public void setNewDenuncia(Denuncia d, int id) throws SQLException {
        String query = "insert into denuncias(denuncia, descricao, status_denuncia, id_usuario) "
                     + "values(?, ?, ?, ?)";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, d.getDenuncia());
        prep.setString(2, d.getDescricao() );
        prep.setString(3, d.getStatusDenuncia() );
        prep.setInt(4, id );
                
        prep.execute();
        prep.close();
    }
    
    
    public void deleteDenuncia(int id) throws SQLException {
        String query = "delete from denuncias "
                     + "where id = " + id ;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.execute();
        prep.close();
    }
    
    
    public Denuncia getOneDenuncia(int id) throws SQLException {
        String query = "select * from denuncias where id = " + id;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        Denuncia t = new Denuncia();
        
        if( res.next() ) {
            t.setId(id);
            t.setDenuncia(res.getString("denuncia"));
            t.setDescricao(res.getString("descricao"));
            t.setStatusDenuncia(res.getString("status_Denuncia"));
        }
        
        prep.close();
        return t;
    }
    
    
    public void updateDenuncia(Denuncia d) throws SQLException {
        String query = "update denuncias set denuncia = ?, "
                     + "descricao = ?, status_Denuncia = ? "
                     + "where id = ?";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, d.getDenuncia());
        prep.setString(2, d.getDescricao() );
        prep.setString(3, d.getStatusDenuncia() );
        prep.setInt(4, d.getId() );
        
        prep.execute();
        prep.close();
    }
}