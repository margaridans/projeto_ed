/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Bernardino
 */
public class Amizade {
    private Integer id_amizade;
    private Pessoa user1;
    private Pessoa user2;

    public Amizade(Pessoa user1, Pessoa user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Amizade() {
        
    }
    
    
    public Integer getId_amizade() {
        return id_amizade;
    }

    public void setId_amizade(Integer id_amizade) {
        this.id_amizade = id_amizade;
    }

    public Pessoa getUser1() {
        return user1;
    }

    public void setUser1(Pessoa user1) {
        this.user1 = user1;
    }

    public Pessoa getUser2() {
        return user2;
    }

    public void setUser2(Pessoa user2) {
        this.user2 = user2;
    }
    
    
    
}
