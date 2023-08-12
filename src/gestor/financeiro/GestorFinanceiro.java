/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestor.financeiro;

import gestor.financeiro.model.dao.UsuarioDao;
import gestor.financeiro.model.dto.UsuarioDto;


/**
 *
 * @author s
 */
public class GestorFinanceiro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        UsuarioDto usuario = new UsuarioDto();
        usuario.setEmail("sergiosantana@hotmail.com");
        usuario.setLogin("ssr");
        usuario.setSenha("12345");
        
        new UsuarioDao().cadastrar(usuario);
        
    }
    
}
