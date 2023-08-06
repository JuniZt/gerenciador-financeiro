/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor.financeiro.model.dao;

import gestor.financeiro.model.dto.UsuarioDto;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s
 */
public class UsuarioDao {
    
    private final GerenciadorBD gerenciadorBd;
    
    public UsuarioDao(){
        
        this.gerenciadorBd = new GerenciadorBD();
    }
    
    public void cadastrar(UsuarioDto usuario) throws Exception {
        
        try(Connection conn = gerenciadorBd.conectar();
                PreparedStatement comando = conn.prepareStatement(
                "INSERT INTO usuario (email, usuario, senha) VALUES (?,?,?) "))
        {
            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getUsuario());
            comando.setString(3, usuario.getSenha());
            comando.executeUpdate();
        }
    }
    
    public List<UsuarioDto> listar() throws Exception  {

        List<UsuarioDto> listaDeClientes = new ArrayList<>();

        try (Connection conexao = gerenciadorBd.conectar(); 
             PreparedStatement comando = conexao.prepareStatement(
             "SELECT id, email, usuario, senha FROM usuario");
             ResultSet tabela = comando.executeQuery()
            ) 
        {
            while (tabela.next()) {
                UsuarioDto usuario = new UsuarioDto();

                usuario.setId(tabela.getLong("id"));
                usuario.setEmail(tabela.getString("email"));
                usuario.setUsuario(tabela.getString("usuario"));
                usuario.setSenha(tabela.getString("senha"));

                listaDeClientes.add(usuario);
            }
        }

        return listaDeClientes;
    }

    public UsuarioDto pesquisar(UsuarioDto usuario) throws Exception {

        try (Connection conexao = gerenciadorBd.conectar(); 
             PreparedStatement comando = conexao.prepareStatement(
             "SELECT email, usuario, senha FROM usuario WHERE id = ? OR WHERE email = ? OR WHERE usuario = ?")
            ) 
        {
            comando.setLong(1, usuario.getId());
            comando.setString(2, usuario.getEmail());
            comando.setString(3, usuario.getUsuario());
  
            ResultSet tabela = comando.executeQuery();

            boolean existeUsuario = false;
            
            if (tabela.next()) {
            
                usuario.setEmail(tabela.getString("email"));
                usuario.setUsuario(tabela.getString("usuario"));
                usuario.setSenha(tabela.getString("senha"));
                
                existeUsuario = true;
            }
            
            if(!existeUsuario)
            {
                usuario.setId(0L);
            }
        }
       
        return usuario;
    }

    public void alterar(UsuarioDto usuario) throws Exception {
        try (Connection conexao = gerenciadorBd.conectar(); 
             PreparedStatement comando = conexao.prepareStatement(
             "UPDATE usuario SET email = ?, usuario = ?, senha = ? WHERE id = ?")
            ) 
        {
            comando.setString(1, usuario.getEmail());
            comando.setString(2, usuario.getUsuario());
            comando.setString(3, usuario.getSenha());
            comando.setLong(4, usuario.getId());

            comando.executeUpdate();
        }
    }

    public void excluir(UsuarioDto usuario) throws Exception {
        try (Connection conexao = gerenciadorBd.conectar(); 
             PreparedStatement comando = conexao.prepareStatement(
             "DELETE FROM usuario WHERE id = ?")
            ) 
        {
            comando.setLong(1, usuario.getId());

            comando.executeUpdate();
        }
    }

}
