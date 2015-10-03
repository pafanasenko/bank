package com.bank.domain.entities;

/**
 * Created by pafanasenko on 29.09.15.
 */
public abstract class DeletableEntity {
    private boolean isDeleted = false;

    public DeletableEntity() {}

    public boolean isDeleted () { return isDeleted; }
    public void delete () { isDeleted = true; }
}
