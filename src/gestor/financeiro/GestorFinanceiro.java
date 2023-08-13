/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestor.financeiro;

import gestor.financeiro.model.dao.CartaoCreditoDao;
import gestor.financeiro.model.dao.UsuarioDao;
import gestor.financeiro.model.dto.CartaoCreditoDto;
import gestor.financeiro.model.dto.UsuarioDto;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author s
 */
public class GestorFinanceiro {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        UsuarioDto usuario = new UsuarioDto();
        usuario.setEmail("sergio@hotmail.com");
        usuario.setLogin("sskings");
        usuario.setSenha("12345");
        
        new UsuarioDao().cadastrar(usuario);
        
        usuario = new UsuarioDao().pesquisar(usuario);
        
        CartaoCreditoDto cartao = new CartaoCreditoDto();
        cartao.setNumero(252525);
        cartao.setUsuario(usuario);
        cartao.setLimite(500.00);
        
        new CartaoCreditoDao().cadastrar(cartao, usuario);
        
        List<CartaoCreditoDto> cartoes = new CartaoCreditoDao().listar();
        String txt = cartoes.toString();
        JOptionPane.showMessageDialog(null, txt );
    }
    
}
