package repaso.estructuras.propositoespecifico;

import repaso.estructuras.lineales.Lista;

public interface Diccionario<C, D> {

    boolean insertar(C clave, D dato);

    boolean eliminar(C clave);

    boolean existeClave(C clave);

    D obtenerInformacion(C clave);

    Lista<C> listarClaves();

    Lista<D> listarDatos();

    boolean esVacio();
}
