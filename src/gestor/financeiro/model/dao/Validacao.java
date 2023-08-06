/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestor.financeiro.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author s
 */
public class Validacao {
    
    private final GerenciadorBD gerenciadorBd;
    
    public Validacao(){
        
        this.gerenciadorBd = new GerenciadorBD();
    }
    
    public boolean validarUsuario(String usuario, String senha) throws Exception {
            Connection conn = gerenciadorBd.conectar();
        try {
  
            String sql = "SELECT id, email, usuario, senha FROM usuario WHERE usuario = ? AND senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, senha);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Se houver um registro correspondente, o usuário é válido
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "um erro aconteceu ao tentar validar.");
            return false;
        }
    }
    
    public boolean validarUsuarioPorEmail(String email, String senha) throws Exception {
            Connection conn = gerenciadorBd.conectar();
        try {
  
            String sql = "SELECT id, email, usuario, senha FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, senha);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Se houver um registro correspondente, o usuário é válido
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "um erro aconteceu ao tentar validar.");
            return false;
        }
    }
    
    
}
