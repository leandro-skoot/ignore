/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.ws;


import com.br.dao.UsuariosDAO;
import com.br.model.Usuarios;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leandro
 */
@Path("produtos")
public class ProdutosWS {
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutosWS
     */
    public ProdutosWS() {
    }

    /**
     * Retrieves representation of an instance of com.br.ws.ProdutosWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getJson() {
        
        return "teste web service";
    }
    
    //trabalhar a tabela usuario
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usuarios/get")
    public String getUsuarioWS()
    {
        Usuarios u = new Usuarios();
        
        u.setLogin_usuarios("Leandro");
        u.setSenha_usuarios("1234");
        u.setPerfil_usuarios("Administrador");
        u.setEmail_usuarios("email@email.com.br");
        
        //convertendo o objeto u da Classe Usuarios para Json
        //Gson é uma biblioteca do Google
        Gson g = new Gson();
        
        return g.toJson(u);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usuarios/list")
    public String getlistarUsuarios()
    {
        
       
        List<Usuarios> listUsuarios;       
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        listUsuarios = usuariosDAO.lista();
        
        //convertendo o objeto u da Classe Usuarios para Json
        //Gson é uma biblioteca do Google
        Gson g = new Gson();
        
        return g.toJson(listUsuarios);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usuarios/listlogin/{login}")
    public String getlistarUsuariosLogin(@PathParam("login") String login)
    {
        Usuarios u = new Usuarios();
        u.setLogin_usuarios(login);      
             
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        u = usuariosDAO.buscarLogin(u);
                
        //convertendo o objeto u da Classe Usuarios para Json
        //Gson é uma biblioteca do Google
        Gson g = new Gson();
        
        return g.toJson(u);
    }

    /**
     * PUT method for updating or creating an instance of ProdutosWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuarios/alterarusuario")
    public boolean alterarUsuarioPUT(String conteudo) {
        
        Gson g = new Gson();
        
        Usuarios u = new Usuarios();
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        u = g.fromJson(conteudo, Usuarios.class);
        
        return usuariosDAO.alterar(u);
               
    }
    
    @DELETE
    @Path("usuarios/excluirlogin/{login}")
    public boolean excluirLogin(@PathParam("login") String login){
        
        Usuarios u = new Usuarios();
        
        u.setLogin_usuarios(login);
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        u = usuariosDAO.buscarLogin(u);
        
        return usuariosDAO.remover(u);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuarios/inserirusuario")
    public boolean inserirUsuarioPOST(String conteudo){
        
        Gson g = new Gson();
        
        Usuarios u = new Usuarios();
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        
        u = (Usuarios) g.fromJson(conteudo, Usuarios.class);
        
        return usuariosDAO.inserir(u);
    }
}
