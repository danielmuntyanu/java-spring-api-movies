package dev.daniel.implementations;

import java.util.List;

public interface InterfaceGenericGetService<T> {
    
    public List<T> getEntities();
    public T getById(Long id);
    public List<T> getByName(String name);

}
