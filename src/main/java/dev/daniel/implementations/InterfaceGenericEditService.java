package dev.daniel.implementations;

public interface InterfaceGenericEditService<T, S> {

    public S storeEntity(T dto);
    public S updateEntity(Long id, T dto);
    public void deleteEntity(Long id);

}
