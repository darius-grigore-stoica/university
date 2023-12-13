package domain;

import java.io.Serializable;

public abstract class Entity implements Serializable{

    /* Setting ID to be protected
    * We don't want the child class to have a separate ID member
    * */
    protected int id;

    Entity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}