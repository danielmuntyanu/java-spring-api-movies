package dev.daniel.implementations;

public interface InterfaceGenericEditService<T, S> {

    public S storeEntity(T dto);

}
