package utilidades;

import entidades.Ciudad;
import entidades.Equipo;
import entidades.Partido;
import entidades.Ronda;
import estructuras.grafo.Arco;
import estructuras.grafo.GrafoEtiquetado;
import estructuras.lineales.Lista;
import estructuras.lineales.ListaDinamica;
import estructuras.propositoEspecifico.ColaPrioridad;
import estructuras.propositoEspecifico.ColaPrioridadDinamica;
import estructuras.propositoEspecifico.TablaBusqueda;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * Esta clase funciona como una base de datos.
 */
public class DatosHelper implements Serializable {
    // mapa de las ciudades
    private GrafoEtiquetado<Ciudad> ciudades;
    // key pais del equipo
    private TablaBusqueda<String, Equipo> equipos;
    // key nombres de los equipos donde eq1 < eq2
    private HashMap<String, Partido> partidos;

    private static DatosHelper instance;

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

    public boolean altaEquipo(String pais, String directorTecnico, char grupo) throws NumberFormatException {
        return altaEquipo(pais, directorTecnico, grupo, 0, 0, 0);
    }

    public boolean altaEquipo(String pais, String directorTecnico, String grupo, String puntos, String golesAFavor, String golesEnContra) throws NumberFormatException {
        return altaEquipo(pais, directorTecnico, grupo.charAt(0), Integer.parseInt(puntos), Integer.parseInt(golesAFavor), Integer.parseInt(golesEnContra));
    }

    public synchronized boolean altaEquipo(String pais, String directorTecnico, char grupo, int puntos, int golesAFavor, int golesEnContra) throws NumberFormatException {
        boolean exito;
        if (grupo == 'A' || grupo == 'B' || grupo == 'C' || grupo == 'D' || grupo == 'E' || grupo == 'F' || grupo == 'G' || grupo == 'H') {
            pais = pais;
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

    public synchronized boolean altaDePartido(String equipoA, String equipoB, String ronda, String ciudad, String golesA, String golesB) throws NumberFormatException {
        int ga = Integer.parseInt(golesA);
        int gb = Integer.parseInt(golesB);

        return altaDePartido(equipoA, equipoB, ronda, ciudad, ga, gb);
    }

    public synchronized boolean altaDePartido(String equipoA, String equipoB, String ronda, String ciudad, int golesA, int golesB) throws NumberFormatException {
        boolean exito = false;
        Ronda r = Ronda.parseToRonda(ronda);
        Equipo eqA = equipos.obtenerDato(equipoA);
        Equipo eqB = equipos.obtenerDato(equipoB);
        Ciudad c = ciudades.obtenerVertice(new Ciudad(ciudad));

        if (eqA != null && eqB != null && c != null) {
            Partido partido = new Partido(eqA, eqB, r, c, golesA, golesB);
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
        Lista<Equipo> todosLosEquipos = equipos.listarDatosOrdenados();

        for (int i = 1; i <= todosLosEquipos.longitud(); i++) {
            Equipo e = todosLosEquipos.recuperar(i);
            if (e.diferenciaGoles() < 0)
                lista.insertar(e);
        }

        return lista;
    }

    public synchronized Lista<Equipo> listarEquiposPorRango(String desde, String hasta) {
        return equipos.listarRango(desde, hasta);
    }

    public synchronized ColaPrioridad<Equipo> obtenerEquiposPorPuntaje() {
        Lista<Equipo> listaEquipos = equipos.listarDatosOrdenados();
        ColaPrioridad<Equipo> colaEquiposPuntaje = new ColaPrioridadDinamica<>();
        for (int i = 1; i <= listaEquipos.longitud(); i++) {
            Equipo equipo = listaEquipos.recuperar(i);
            colaEquiposPuntaje.insertar(equipo, equipo.getPuntos());
        }
        return colaEquiposPuntaje;
    }

    public synchronized String obtenerTablaPosiciones() {
        ColaPrioridad<Equipo> equipos = obtenerEquiposPorPuntaje();
        StringBuilder sb = new StringBuilder("Tabala de posiciones:\n");
        while (!equipos.estaVacia()) {
            Equipo e = equipos.obtenerFrente();
            equipos.eliminarFrente();
            sb.append(e.getPuntos()).append(" -> ").append(e.getPais()).append('\n');
        }
        return sb.toString();
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
        return ciudades.caminoMasCorto(new Ciudad(ciudadOrigen),
                new Ciudad(ciudadDestino1),
                new Ciudad(ciudadDestino2));
    }

    public synchronized Lista<Ciudad> listarCiudades() {
        return ciudades.listarEnAnchura();
    }

    public synchronized Lista<Arco<Ciudad, Integer>> listarRutas() {
        return ciudades.listarArcos();
    }

    public synchronized Lista<Equipo> listarEquipos() {
        return equipos.listarDatosOrdenados();
    }

    public synchronized Collection<Partido> getPartidos() {
        return partidos.values();
    }

    @Override
    public synchronized String toString() {
        return "Sistema{" +
                "\nCiudades= " + ciudades +
                "\nEquipos= " + equipos +
                "\nPartidos= " + partidos +
                "}   " + partidos.size();
    }

    public void vaciar() {
        ciudades = new GrafoEtiquetado<>();
        partidos.clear();
        equipos.vaciar();
    }
}
