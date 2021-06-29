
package com.br.dao;

import com.br.database.Conexao;
import com.br.model.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuariosDAO {
    
       
   
    
    //método inserir
    public boolean inserir(Usuarios usuarios)
    {
        String sql = "INSERT INTO tb_usuarios (login_usuario,"
                + " senha_usuario, perfil_usuario,"
                + " email_usuario) VALUES (?, ?, ?, ?)";
        
        Boolean retorno = false;
        
        try
        {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            stmt.setString(1, usuarios.getLogin_usuarios());
            stmt.setString(2, usuarios.getSenha_usuarios());
            stmt.setString(3, usuarios.getPerfil_usuarios());
            stmt.setString(4, usuarios.getEmail_usuarios());
            stmt.execute();
            stmt.close();
            retorno = true;
            
        }
        catch(SQLException ex)
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível inserir no banco: " + ex);
            return retorno = false;  
        }
        
        return retorno;
        
    }
    
    //método para alterar
    public boolean alterar(Usuarios usuarios)
    {
        String sql = "UPDATE tb_usuarios SET senha_usuario = ?,"
                + " perfil_usuario = ?, email_usuario = ? WHERE login_usuario = ?";
        
        Boolean retorno = false;
        
        try
        {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            
            stmt.setString(1, usuarios.getSenha_usuarios());
            stmt.setString(2, usuarios.getPerfil_usuarios());
            stmt.setString(3, usuarios.getEmail_usuarios());
            stmt.setString(4, usuarios.getLogin_usuarios());           
            stmt.execute();
            stmt.close();
            retorno = true; 
        }
        catch(SQLException ex)
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível alterar no banco: " + ex);
            return retorno = false;  
        }
        
        return retorno;
    }
    
    //método para remover
    public boolean remover(Usuarios usuarios)
    {
        String sql = "DELETE FROM tb_usuarios WHERE login_usuario = ?";
        
        Boolean retorno = false;
        
        try
        {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            stmt.setString(1, usuarios.getLogin_usuarios());
            
            stmt.close();
            retorno = true;
        }
        catch(SQLException ex)
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível remover do banco: " + ex);
            return retorno = false;  
        }
        
        return retorno;
    }
    
    //método para listar
    public List<Usuarios> lista()
    {
        String sql = "SELECT * FROM tb_usuarios";
        
        List<Usuarios> retorno = new ArrayList<>();
        
        try
        {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next())
            {
                Usuarios usuarios = new Usuarios();
                usuarios.setId_usuarios(resultado.getInt("id_usuarios"));
                usuarios.setLogin_usuarios(resultado.getString("login_usuario"));
                usuarios.setSenha_usuarios(resultado.getString("senha_usuario"));
                usuarios.setPerfil_usuarios(resultado.getString("perfil_usuario"));
                usuarios.setEmail_usuarios(resultado.getString("email_usuario"));
                
                //vamos adicionando a liss retorno
                retorno.add(usuarios);
            }        
                      
           
        }
        catch(SQLException ex)
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível listar do banco: " + ex);
              
        }
        
        return retorno;
        
    }
    
//    //método para listar usuarios e estado pela sigla do estado com inner join
//    public List<Usuarios> listaUsuariosEsatdos()
//    {
//        String sql = "SELECT usuarios.id_Usuarios, usuarios.nome_Usuarios, estados.sigla_Estado, estados.id_Estado"
//                + " FROM tb_usuarios AS usuarios INNER JOIN tb_estados AS estados ON "
//                + "usuarios.tb_estados_id_Estados = estados.id_Estado";
//        
//        List<Usuarios> retorno = new ArrayList<>();
//        
//        try
//        {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet resultado = stmt.executeQuery();
//            
//            while(resultado.next())
//            {
//                Usuarios usuarios = new Usuarios();
//                
//                usuarios.setId_usuarios(resultado.getInt("id_usuarios"));
//                usuarios.setLogin_usuarios(resultado.getString("login_usuarios"));
//                usuarios.setSenha_usuarios(resultado.getString("senha_usuarios"));
//                usuarios.setPerfil_usuaruios(resultado.getString("perfil_usuarios"));
//                usuarios.setEmail_usuarios(resultado.getString("email_usuarios"));
//                //vamos adicionando a liss retorno
//                retorno.add(usuarios);
//            }        
//                      
//           
//        }
//        catch(SQLException ex)
//        { 
//            JOptionPane.showMessageDialog(null, "Não foi possível listar do banco: " + ex);
//              
//        }
//        
//        return retorno;
//        
//    }
    
    //método para buscar
    public Usuarios buscarLogin(Usuarios usuarios)
    {
        String sql = "SELECT * FROM tb_usuarios WHERE login_usuario = ?";
        
        Usuarios retorno = new Usuarios();
        
        try
        {
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            stmt.setString(1, usuarios.getLogin_usuarios());
            
            
            ResultSet resultado = stmt.executeQuery();
            
            if(resultado.next())
            {
                retorno.setId_usuarios(resultado.getInt("id_usuarios"));
                retorno.setLogin_usuarios(resultado.getString("login_usuario"));
                retorno.setSenha_usuarios(resultado.getString("senha_usuario"));
                retorno.setPerfil_usuarios(resultado.getString("perfil_usuario"));
                retorno.setEmail_usuarios(resultado.getString("email_usuario"));
                
                return retorno;
            }
            
        }
        catch(SQLException ex)
        { 
            JOptionPane.showMessageDialog(null, "Não foi possível buscar do banco: " + ex);
             
        }
        
        return retorno;
    }
    
}
