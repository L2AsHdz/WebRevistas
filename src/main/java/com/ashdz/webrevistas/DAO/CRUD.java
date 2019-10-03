package com.ashdz.webrevistas.DAO;

import java.util.List;

/**
 *
 * @author asael
 */
public interface CRUD<T> {
    public List<T> getListado();
    public void create(T t);
    public T getObject(Object t);
    public void update(T t);
    public void delete(int t);
}
