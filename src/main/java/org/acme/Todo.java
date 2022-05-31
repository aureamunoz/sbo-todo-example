package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Todo extends PanacheEntity {

    @Column(unique = true)
    public String title;

    public boolean completed;

    @Column(name = "ordering")
    public int order;

    public String url;


    public Todo() {
    }

    public Todo(String title, Boolean completed) {
        this.title = title;
    }

//    public static Multi<Todo> findNotCompleted() {
//        return stream("completed", false);
//    }
//
//    public static Multi<Todo> findCompleted() {
//        return stream("completed", true);
//    }
//
//    public static Uni<Long> deleteCompleted() {
//        return delete("completed", true);
//    }

}
