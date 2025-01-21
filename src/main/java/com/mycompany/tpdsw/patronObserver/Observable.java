package com.mycompany.tpdsw.patronObserver;

public interface Observable<T> {
    public void addObserver(Observer<T> observer);

    public void notificarObservadores();

    public T get();

}
