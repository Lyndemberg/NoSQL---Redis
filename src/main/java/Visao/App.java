
package Visao;

import Controle.ProdutoDao;
import Modelo.Carrinho;
import Modelo.ItemCarrinho;
import Modelo.Produto;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        
        try {
            ProdutoDao dao = new ProdutoDao();    
            
            Gson gson = new Gson();
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            
            //BUSCANDO PRODUTOS NO BANCO
            Produto p1 = dao.read(2);
            Produto p2 = dao.read(5);
            Produto p3 = dao.read(10);
            
            Carrinho c = new Carrinho("c2");
            ItemCarrinho item1 = new ItemCarrinho(p1,4);
            ItemCarrinho item2 = new ItemCarrinho(p2,2);
            ItemCarrinho item3 = new ItemCarrinho(p3,7);
            
            c.addItem(item1);
            c.addItem(item2);
            c.addItem(item3);
            
            String jsonCarrinho = gson.toJson(c);
            jedis.setex(c.getSessionId(),50,jsonCarrinho);
            
            
            //BUSCANDO NO REDIS E EXIBINDO NA APLICAÇÃO
            Carrinho cc = gson.fromJson(jedis.get("c2"), Carrinho.class);
            System.out.println(cc);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
