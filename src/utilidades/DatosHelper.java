package utilidades;

import entidades.Ciudad;
import entidades.Equipo;
import entidades.Partido;
import entidades.Ronda;
import estructuras.grafo.GrafoEtiquetado;
import estructuras.lineales.Lista;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Esta clase funciona como una base de datos que trabaja sobre un archivo con los objetos serializados.
 */
public class DatosHelper {
    private GrafoEtiquetado<Ciudad> ciudades;
    // key pais del equipo
    private Hashtable<String, Equipo> equipos;
    // key nombres de los equipos donde eq1 < eq2
    private HashMap<String, Partido> partidos;

    static DatosHelper instance;

    private DatosHelper() {
        ciudades = new GrafoEtiquetado<>();
        equipos = new Hashtable<>();
        partidos = new HashMap<>();
    }

    public static DatosHelper getInstance() {
        if (instance == null)
            instance = new DatosHelper();
        return instance;
    }

    public boolean altaCiudad(String nombre, double superficie, int cantHabitantes, boolean sede) {
        Ciudad c = new Ciudad(nombre, superficie, cantHabitantes, sede);
        return ciudades.insertarVertice(c);
    }

    public boolean eliminarCiudad(String nombre) {
        Ciudad c = new Ciudad(nombre);
        return ciudades.eliminarVertice(c);
    }

    public boolean modificarCiudad(String nombre, double superficie, int cantHabitantes, boolean sede) {
        boolean modi = false;
        Ciudad ciudad = getCiudad(nombre);
        if (ciudad != null) {
            ciudad.setSuperfice(superficie);
            ciudad.setCantHabitantes(cantHabitantes);
            ciudad.setSede(sede);
            modi = true;
        }
        return modi;
    }

    public Ciudad getCiudad(String nombre) {
        return ciudades.obtenerVertice(new Ciudad(nombre));
    }

    public boolean altaEquipo(String pais, String directorTecnico) {
        boolean exito = false;
        Equipo equipo = new Equipo(pais, directorTecnico);
        if (equipos.get(pais) == null) {
            equipos.put(pais, equipo);
            exito = true;
        }
        return exito;
    }

    public boolean bajaEquipo(String pais) {
        return equipos.remove(pais) != null;
    }

    public boolean modificarEquipo(String nombre, String directorTecnico, int puntos, int golesAFavor, int golesEnContra) {
        Equipo equipo = equipos.get(nombre);
        boolean modi = false;
        if (equipo != null) {
            equipo.setDirectorTecnico(directorTecnico);
            equipo.setPuntos(puntos);
            equipo.setGolesAFavol(golesAFavor);
            equipo.setGolesEnContra(golesEnContra);
            modi = true;
        }
        return modi;
    }

    public boolean insertarRuta(String origen, String destino, int distancia) {
        return ciudades.insertarArco(new Ciudad(origen), new Ciudad(destino), distancia);
    }

    public boolean eliminarRuta(String origen, String destino) {
        return ciudades.eliminarArco(new Ciudad(origen), new Ciudad(destino));
    }

    public boolean altaDePartido(String equipoA, String equipoB, Ronda ronda, int golesA, int golesB) {
        boolean exito = false;
        Equipo eqA = equipos.get(equipoA);
        Equipo eqB = equipos.get(equipoB);
        if (eqA != null && eqB != null) {
            Partido partido = new Partido(eqA, eqB, ronda, golesA, golesB);
            String key = equipoA + equipoB;
            if (eqB.compareTo(eqA) < 0) {
                key = equipoB + equipoA;
            }
            partidos.put(key, partido);
            exito = true;
        }
        return exito;
    }

    public Lista<Ciudad> obtenerCaminoConMenorDistancia(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public Lista<Ciudad> obtenerCaminoConMenosCiudades(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public Lista<Ciudad> obtenerCaminoPosibles(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public Lista<Ciudad> obtenerCaminoMasCortoEntreCiudad(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }
}
