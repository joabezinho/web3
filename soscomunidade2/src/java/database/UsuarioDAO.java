package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO {
    private static Connection conn;
    
    public UsuarioDAO() throws ClassNotFoundException, SQLException {
        conn = Conexao.getConn();
    }
    
    public ArrayList<Usuario> getAllUsers() throws SQLException {
        
        ArrayList<Usuario> list = new ArrayList();
        
        String query = "select * from usuarios;";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        while( res.next() ) {
            Usuario user = new Usuario();
            
            user.setId( res.getInt("id") );
            user.setNome(res.getString("nome") );
            user.setEmail(res.getString("email") );
            user.setNascimento(res.getString("nascimento") );
            user.setSenha(res.getString("senha") );
            user.setNoticias(res.getInt("noticias") == 1);
            
            list.add(user);
            System.out.println(user);
        }
        
        prep.close();
        return list;
    }
    
    
    public void setNewUser(Usuario user) throws SQLException {
        String query = "insert into usuarios(nome, email, nascimento, senha, noticias) "
                     + "values(?, ?, ?, sha1(?), ?)";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, user.getNome() );
        prep.setString(2, user.getEmail() );
        prep.setString(3, user.getNascimento() );
        prep.setString(4, user.getSenha() );
        prep.setInt(5, user.isNoticias() ? 1 : 0 );
        
        prep.execute();
        prep.close();
    }
    
    
    public void deleteUser(int id) throws SQLException {
        String query = "delete from usuarios "
                     + "where id = " + id ;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.execute();
        prep.close();
    }
    
    
    public Usuario getOneUser(int id) throws SQLException {
        String query = "select * from usuarios where id = " + id;
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        ResultSet res = prep.executeQuery();
        
        Usuario u = new Usuario();
        
        if( res.next() ) {
            u.setId(id);
            u.setNome(res.getString("nome"));
            u.setEmail(res.getString("email"));
            u.setNascimento(res.getString("nascimento"));
            u.setSenha(res.getString("senha"));
            u.setNoticias(res.getInt("noticias") == 1);
        }
        
        prep.close();
        return u;
    }
    
    public Usuario getOneUserByEmail(String email) throws SQLException {
        String query = "select * from usuarios where email = ?";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, email);
        
        ResultSet res = prep.executeQuery();
        
        Usuario u = new Usuario();
        
        if( res.next() ) {
            u.setId(res.getInt("id"));
            u.setNome(res.getString("nome"));
            u.setEmail(res.getString("email"));
            u.setNascimento(res.getString("nascimento"));
            u.setSenha(res.getString("senha"));
            u.setNoticias(res.getInt("noticias") == 1);
        }
        
        prep.close();
        return u;
    }
    
    
    public void updateUser(Usuario user) throws SQLException {
        String query = "update usuarios set nome = ?, "
                     + "email = ?, nascimento = ?, noticias = ? "
                     + "where id = ?";
        
        PreparedStatement prep = conn.prepareStatement(query);
        
        prep.setString(1, user.getNome() );
        prep.setString(2, user.getEmail() );
        prep.setString(3, user.getNascimento() );
        prep.setInt(4, user.isNoticias() ? 1 : 0 );
        prep.setInt(5, user.getId() );
        
        prep.execute();
        prep.close();
    }
}