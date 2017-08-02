
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private String sessionId;
    private List<ItemCarrinho> itens;

    public Carrinho(String sessionId) {
        this.sessionId = sessionId;
        itens = new ArrayList<>();
    }

    public boolean addItem(ItemCarrinho novo){
        return itens.add(novo);
    }
    
    public float calcularTotal(){
        float total=0;
        for (int i=0; i<itens.size(); i++){
            total = total + itens.get(i).calcularSubTotal();
        }
        return total;
    }
    
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Carrinho{" + "sessionId=" + sessionId + ", itens=" + itens + '}';
    }
    
}
