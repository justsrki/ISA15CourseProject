package beans.dao;

import model.dao.Invitation;

import java.io.Serializable;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
public interface AbstractLocal<T> {

    void create(T entity);

    void edit(T entity);

    void remove(T entity);

    T find(Integer id);

    List<T> findAll();

    List<T> findWithLimit(int limit);

    List<T> findRange(int[] range);

    int count();
}
