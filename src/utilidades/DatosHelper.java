package utilidades;

import entidades.Ciudad;
import entidades.Equipo;
import entidades.Partido;
import entidades.Ronda;
import estructuras.grafo.GrafoEtiquetado;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;
import estructuras.propositoEspecifico.TablaBusqueda;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Esta clase funciona como una base de datos que trabaja sobre un archivo con los objetos serializados.
 */
public class DatosHelper implements Serializable {
    private GrafoEtiquetado<Ciudad> ciudades;
    // key pais del equipo, TODO diccionario o tabla de Búsqueda implementada con un árbol AVL ordenados alfabeticamente
    private TablaBusqueda<String, Equipo> equipos;
    // key nombres de los equipos donde eq1 < eq2
    private HashMap<String, Partido> partidos;

    static DatosHelper instance;

    private DatosHelper() {
        ciudades = new GrafoEtiquetado<>();
        equipos = new TablaBusqueda<>();
        partidos = new HashMap<>();
    }

    public static DatosHelper getInstance() {
        if (instance == null)
            instance = new DatosHelper();
        return instance;
    }

    public boolean altaCiudad(String nombre, String superficie, String cantHabitantes, String sede) throws NumberFormatException {
        return altaCiudad(nombre, Double.parseDouble(superficie.replace(',', '.')), Integer.parseInt(cantHabitantes), Boolean.parseBoolean(sede));
    }

    public synchronized boolean altaCiudad(String nombre, double superficie, int cantHabitantes, boolean sede) {
        Ciudad c = new Ciudad(nombre, superficie, cantHabitantes, sede);
        return ciudades.insertarVertice(c);
    }

    public synchronized boolean eliminarCiudad(String nombre) {
        Ciudad c = new Ciudad(nombre);
        return ciudades.eliminarVertice(c);
    }

    public boolean modificarCiudad(String nombre, String superficie, String cantHabitantes, String sede) throws NumberFormatException {
        return modificarCiudad(nombre, Integer.parseInt(superficie), Integer.parseInt(cantHabitantes), Boolean.parseBoolean(sede));
    }

    public synchronized boolean modificarCiudad(String nombre, double superficie, int cantHabitantes, boolean sede) {
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

    public boolean altaEquipo(String pais, String directorTecnico, String grupo) throws NumberFormatException {
        return altaEquipo(pais, directorTecnico, grupo, "0", "0", "0");
    }

    public boolean altaEquipo(String pais, String directorTecnico, char grupo) throws NumberFormatException {
        return altaEquipo(pais, directorTecnico, grupo, 0, 0, 0);
    }

    public boolean altaEquipo(String pais, String directorTecnico, String grupo, String puntos, String golesAFavor, String golesEnContra) throws NumberFormatException {
        return altaEquipo(pais, directorTecnico, grupo.charAt(0), Integer.parseInt(puntos), Integer.parseInt(golesAFavor), Integer.parseInt(golesEnContra));
    }

    public synchronized boolean altaEquipo(String pais, String directorTecnico, char grupo, int puntos, int golesAFavor, int golesEnContra) throws NumberFormatException {
        boolean exito = false;
        if (grupo == 'A' || grupo == 'B' || grupo == 'C' || grupo == 'D' || grupo == 'E' || grupo == 'F' || grupo == 'G' || grupo == 'H') {
            Equipo equipo = new Equipo(pais, directorTecnico, grupo, puntos, golesAFavor, golesEnContra);
            exito = equipos.insertar(pais, equipo);
        } else {
            throw new NumberFormatException("variable grupo no valida: " + grupo);
        }
        return exito;
    }

    public synchronized boolean bajaEquipo(String pais) {
        return equipos.eliminar(pais);
    }

    public synchronized boolean modificarEquipo(String nombre, String directorTecnico, char grupo, int puntos, int golesAFavor, int golesEnContra) throws NumberFormatException {
        boolean modi = false;
        if (grupo == 'A' || grupo == 'B' || grupo == 'C' || grupo == 'D' || grupo == 'E' || grupo == 'F' || grupo == 'G' || grupo == 'H') {
            Equipo equipo = equipos.obtenerDato(nombre);
            if (equipo != null) {
                equipo.setDirectorTecnico(directorTecnico);
                equipo.setGrupo(grupo);
                equipo.setPuntos(puntos);
                equipo.setGolesAFavor(golesAFavor);
                equipo.setGolesEnContra(golesEnContra);
                modi = true;
            }
        } else {
            throw new NumberFormatException("variable grupo no valida: " + grupo);
        }
        return modi;
    }

    public boolean insertarRuta(String origen, String destino, String distancia) throws NumberFormatException {
        return insertarRuta(origen, destino, Integer.parseInt(distancia));
    }

    public synchronized boolean insertarRuta(String origen, String destino, int distancia) {
        return ciudades.insertarArco(new Ciudad(origen), new Ciudad(destino), distancia);
    }

    public synchronized boolean eliminarRuta(String origen, String destino) {
        return ciudades.eliminarArco(new Ciudad(origen), new Ciudad(destino));
    }

    public synchronized boolean altaDePartido(String equipoA, String equipoB, String ronda, String golesA, String golesB) throws NumberFormatException {
        boolean exito = false;
        Ronda r = Ronda.parseToRonda(ronda);
        int ga = Integer.parseInt(golesA);
        int gb = Integer.parseInt(golesB);

        return altaDePartido(equipoA, equipoB, ronda, ga, gb);
    }

    public synchronized boolean altaDePartido(String equipoA, String equipoB, String ronda, int golesA, int golesB) throws NumberFormatException {
        boolean exito = false;
        Ronda r = Ronda.parseToRonda(ronda);
        Equipo eqA = equipos.obtenerDato(equipoA);
        Equipo eqB = equipos.obtenerDato(equipoB);

        if (eqA != null && eqB != null) {
            Partido partido = new Partido(eqA, eqB, r, golesA, golesB);
            if (partidos.get(partido.getKey()) == null) { // si no existe lo crea
                exito = eqA.agragarPartido(partido) && eqB.agragarPartido(partido);
                partidos.put(partido.getKey(), partido);
            }
        }
        return exito;
    }

    public synchronized Equipo obtenerEquipo(String pais) {
        return equipos.obtenerDato(pais);
    }

    public synchronized Lista<Equipo> listarEquiposConDifGolNeg() {
        Lista<Equipo> lista = new ListaDinamica<>();
        Lista<Equipo> todosLosEquipos = equipos.listarDatos();

        for (int i = 1; i <= todosLosEquipos.longitud(); i++) {
            Equipo e = todosLosEquipos.recuperar(i);
            if (e.diferenciaGoles() < 0)
                lista.insertar(e);
        }

        return lista;
    }

    /*
     * Obtener la tabla de posiciones de los equipos de un momento dado, almacenando
     * los datos de los equipos ordenados de mayor a menor puntaje (puede utilizar alguna
     * estructura de datos auxiliar que considere apropiada, asegurando la eficiencia)
     */
    public synchronized Lista<Equipo> listarEquipos(String desde, String hasta) {
        // pasa del diccionario original a otro diccionario ordenado por puntaje y los lista
        TablaBusqueda<Integer, Equipo> equiposPuntaje = new TablaBusqueda<>();
        Lista<Equipo> listaEquipos = equipos.listarDatos();
        for (int i = 1; i <= listaEquipos.longitud(); i++) {
            Equipo equipo = listaEquipos.recuperar(i);
            equiposPuntaje.insertar(equipo.getPuntos(), equipo);
        }
        return equiposPuntaje.listarDatos();
    }

    public synchronized Lista<Ciudad> obtenerCaminoConMenorDistancia(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public synchronized Lista<Ciudad> obtenerCaminoConMenosCiudades(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminoConMenosVertices(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public synchronized Lista<Lista<Ciudad>> obtenerCaminoPosibles(String ciudadOrigen, String ciudadDestino) {
        return ciudades.caminosPosibles(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino));
    }

    public synchronized Lista<Ciudad> obtenerCaminoMasCortoEntreCiudad(String ciudadOrigen, String ciudadDestino1, String ciudadDestino2) {
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen), new Ciudad(ciudadDestino1), new Ciudad(ciudadDestino2));
    }

    @Override
    public synchronized String toString() {
        return "Sistema{" +
                "\nCiudades= " + ciudades +
                "\nEquipos= " + equipos +
                "\nPartidos= " + partidos +
                '}';
    }

    public void vaciar() {
        ciudades = new GrafoEtiquetado<>();
        partidos.clear();
        ;
        equipos.vaciar();
    }
}
