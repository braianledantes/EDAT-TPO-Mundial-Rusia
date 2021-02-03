import modelos.Ciudad;
import modelos.Equipo;
import modelos.Partido;
import estructuras.lineales.Lista;
import estructuras.propositoEspecifico.ColaPrioridad;
import utilidades.DataHelper;
import utilidades.FilesHelper;
import utilidades.Logger;
import utilidades.TecladoIn;

import java.io.IOException;

public class VistaTerminal {

    public DataHelper dataHelper;
    public FilesHelper filesHelper;
    public Logger logger;

    public VistaTerminal(DataHelper dataHelper, FilesHelper filesHelper, Logger logger) {
        this.dataHelper = dataHelper;
        this.filesHelper = filesHelper;
        this.logger = logger;
    }

    public void iniciar() {
        mostrarMenuPricipal();
    }

    public void mostrarMenuPricipal() {
        int option = 1;
        String sb = "----------------Menu Principal----------------\n" +
                "1) ABM ciudades" + '\n' +
                "2) ABM equipos" + '\n' +
                "3) Alta de partidos" + '\n' +
                "4) Consultar equipos" + '\n' +
                "5) Consultar ciudades" + '\n' +
                "6) Consultar viajes" + '\n' +
                "7) Mostrar tabla de posiciones" + '\n' +
                "8) Mostrar sistema" + '\n' +
                "9) Guardar" + '\n' +
                "10) Exportar" + '\n' +
                "11) Importar" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 11)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    mostrarMenuABMCiudades();
                    break;
                case 2:
                    mostrarMenuABMEquipos();
                    break;
                case 3:
                    mostrarAltaPartidos();
                    break;
                case 4:
                    mostrarMenuConsultarEquipos();
                    break;
                case 5:
                    mostrarConsultarCiudades();
                    break;
                case 6:
                    mostrarMenuConsultarViajes();
                    break;
                case 7:
                    mostrarTablaPosiciones();
                    break;
                case 8:
                    mostrarSistema();
                    break;
                case 9:
                    guardarSistema();
                    break;
                case 10:
                    exportarSistema();
                    break;
                case 11:
                    ImportarSistema();
                    break;
                case -1:
                    System.out.println("Chau");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (option != -1);
    }

    public void mostrarMenuABMCiudades() {
        int option = 1;
        String sb = "----------------ABM ciudades----------------\n" +
                "1) Alta ciudad" + '\n' +
                "2) Baja ciudad" + '\n' +
                "3) Modificar ciudad" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 3)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    altaCiudad();
                    break;
                case 2:
                    bajaCiudad();
                    break;
                case 3:
                    modificarCiudad();
                    break;
                case -1:
                    System.out.println("salio");
                    break;
                default:
                    System.err.println("Opcion invalida");
                    break;
            }
        } while (option != -1);
    }

    public void altaCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la superficie en KM^2:");
        double superficie = TecladoIn.readLineDouble();
        System.out.println("Ingrese la cantidad de habitantes:");
        int cantHab = TecladoIn.readLineInt();
        System.out.println("¿Es sede de algun partido? (true/false)");
        boolean sede = TecladoIn.readLineBoolean();

        if (dataHelper.altaCiudad(nombre, superficie, cantHab, sede))
            logger.createCity(nombre, superficie + "", cantHab + "", sede + "");
        else
            System.err.println("La ciudad " + nombre + " ya existe");
    }

    public void bajaCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine().toUpperCase();
        if (dataHelper.eliminarCiudad(nombre))
            logger.deleteCity(nombre);
        else
            System.err.println("La ciudad " + nombre + " no existe");
    }

    public void modificarCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la superficie en KM^2:");
        double superficie = TecladoIn.readLineDouble();
        System.out.println("Ingrese la cantidad de habitantes:");
        int cantHab = TecladoIn.readLineInt();
        System.out.println("¿Es sede de algun partido? (true/false)");
        boolean sede = TecladoIn.readLineBoolean();

        if (dataHelper.modificarCiudad(nombre, superficie, cantHab, sede))
            logger.modifyCity(nombre, superficie + "", cantHab + "", sede + "");
        else
            System.err.println("La ciudad " + nombre + " no existe");
    }

    public void mostrarMenuABMEquipos() {
        int option = 1;
        String sb = "----------------ABM equipos----------------\n" +
                "1) Alta equipo" + '\n' +
                "2) Baja equipo" + '\n' +
                "3) Modificar equipo" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 3)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    altaEquipo();
                    break;
                case 2:
                    bajaEquipo();
                    break;
                case 3:
                    modificarEquipo();
                    break;
                case -1:
                    System.out.println("salio");
                    break;
                default:
                    System.err.println("Opcion invalida");
                    break;
            }
        } while (option != -1);
    }

    public void altaEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre del director tecnico:");
        String dt = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el grupo (A, B, C, D, E, F, G o H) :");
        char grupo = Character.toUpperCase(TecladoIn.readLineNonwhiteChar());

        try {
            boolean alta = dataHelper.altaEquipo(nombre, dt, grupo);
            if (alta)
                logger.createTeam(nombre, dt, grupo + "");
            else
                System.err.println("El equipo " + nombre + " ya existe");

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void bajaEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine().toUpperCase();
        if (!dataHelper.bajaEquipo(nombre))
            System.err.println("El equipo " + nombre + " no existe");
        else
            logger.daleteTeam(nombre);
    }

    public void modificarEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre del director tecnico:");
        String dt = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el grupo (A, B, C, D, E, F, G o H) :");
        char grupo = Character.toUpperCase(TecladoIn.readLineNonwhiteChar());
        System.out.println("Ingrese el puntaje:");
        int puntaje = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles a favor:");
        int golesAFavor = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles en contra:");
        int golesEnContra = TecladoIn.readLineInt();

        try {
            boolean modifico = dataHelper.modificarEquipo(nombre, dt, grupo, puntaje, golesAFavor, golesEnContra);
            if (!modifico)
                System.err.println("El equipo " + nombre + " no existe");
            else
                logger.modifyTeam(nombre, dt, grupo, puntaje, golesAFavor, golesEnContra);

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarAltaPartidos() {
        System.out.println("Ingrese el nombre del equipo A:");
        String eA = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre del equipo B:");
        String eB = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ronda (GRUPO, OCTAVOS, CUARTOS, SEMIFINAL, FINAL):");
        String ronda = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad del evento:");
        String ciudad = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese los goles del equipo A:");
        int golesA = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles del equipo B:");
        int golesB = TecladoIn.readLineInt();

        try {
            if (dataHelper.altaDePartido(eA, eB, ronda, ciudad, golesA, golesB))
                logger.altaDePartido(eA, eB, ronda, ciudad, golesA + "", golesB + "");
            else
                System.err.println("El partido ya existe o la ciudad no existe");
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarMenuConsultarEquipos() {
        int option = 1;
        String sb = "----------------Consultar equipos----------------\n" +
                "1) Mostrar datos de equipo" + '\n' +
                "2) Mostrar rango de equipos ordenados alfabeticamente" + '\n' +
                "3) Modificar equipos con diferencia negativa de goles" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 3)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    mostrarEquipo();
                    break;
                case 2:
                    mostrarRangoEquipos();
                    break;
                case 3:
                    mostrarEquiposConDifGol();
                    break;
                case -1:
                    System.out.println("salio");
                    break;
                default:
                    System.err.println("Opcion invalida");
                    break;
            }
        } while (option != -1);
    }

    public void mostrarEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String pais = TecladoIn.readLine().toUpperCase();
        Equipo equipo = dataHelper.obtenerEquipo(pais);
        if (equipo != null) {
            StringBuilder sb = new StringBuilder("Equipo: ").append(equipo.getPais()).append('\n');
            sb.append("puntos: ").append(equipo.getPuntos()).append('\n');
            sb.append("grupo: ").append(equipo.getGrupo()).append('\n');
            sb.append("goles a favor: ").append(equipo.getGolesAFavor()).append('\n');
            sb.append("goles en contra: ").append(equipo.getGolesEnContra()).append('\n');
            sb.append("diferencia de goles: ").append(equipo.diferenciaGoles()).append('\n');
            sb.append("partidos:\n");
            Lista<Partido> partidos = equipo.getPartidosJugados();
            for (int i = 1; i <= partidos.longitud(); i++) {
                Partido partido = partidos.recuperar(i);
                sb.append('-').append(partido.getRonda()).append(' ');
                sb.append(partido.getEquipoA().getPais()).append('/').append(partido.getEquipoB().getPais()).append(' ');
                sb.append('(').append(partido.getGolesEquipoA()).append('/').append(partido.getGolesEquipoB()).append(')');
                sb.append('\n');
            }
            System.out.println(sb.toString());
            logger.showTeam(pais);
        } else {
            System.err.println("No existe equipo " + pais);
        }
    }

    public void mostrarRangoEquipos() {
        System.out.println("Ingrese el nombre de un pais:");
        String equipoA = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre del otro pais:");
        String equipoB = TecladoIn.readLine().toUpperCase();

        if (equipoB.compareTo(equipoA) < 0) { // por si estan al reves
            String aux = equipoA;
            equipoA = equipoB;
            equipoB = aux;
        }
        Lista<Equipo> equipos = dataHelper.listarEquiposPorRango(equipoA, equipoB);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= equipos.longitud(); i++) {
            Equipo equipo = equipos.recuperar(i);
            sb.append(equipo).append('\n');
        }
        System.out.println(sb.toString());
        logger.mostrarEquiposPorRango(equipoA, equipoB);
    }

    public void mostrarEquiposConDifGol() {
        Lista<Equipo> equipos = dataHelper.listarEquiposConDifGolNeg();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= equipos.longitud(); i++) {
            Equipo equipo = equipos.recuperar(i);
            sb.append(equipo.getPais()).append(" -> ").append(equipo.diferenciaGoles()).append('\n');
        }
        System.out.println(sb.toString());
        logger.mostrarEquiposConDifGol();
    }

    public void mostrarConsultarCiudades() {
        System.out.println("Ingrese el nombre de la ciudad:");
        String nombre = TecladoIn.readLine().toUpperCase();
        Ciudad ciudad = dataHelper.getCiudad(nombre);
        if (ciudad != null) {
            System.out.println(ciudad);
            logger.mostrarCiudad(nombre);
        } else
            System.err.println("No existe " + nombre);
    }

    public void mostrarMenuConsultarViajes() {
        int option = 1;
        String sb = "----------------Consultar viajes----------------\n" +
                "1) Obtener el camino que llegue de A a B de menor distancia en km" + '\n' +
                "2) Obtener el camino que llegue de A a B pasando por la mínima cantidad de ciudades" + '\n' +
                "3) Obtener todos los caminos posibles para llegar de A a B" + '\n' +
                "4) Obtener  El camino más corto para llegar de A a B que pase por otra ciudad C" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 4)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    mostrarCaminoConMenorDistancia();
                    break;
                case 2:
                    mostrarCaminoConMenosCiudades();
                    break;
                case 3:
                    mostrarCaminoPosibles();
                    break;
                case 4:
                    mostrarCaminoMasCortoEntreCiudad();
                    break;
                case -1:
                    System.out.println("salio");
                    break;
                default:
                    System.err.println("Opcion invalida");
                    break;
            }
        } while (option != -1);
    }

    public void mostrarCaminoConMenorDistancia() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine().toUpperCase();

        Lista<Ciudad> viaje = dataHelper.obtenerCaminoConMenorDistancia(origen, destino);
        if (!viaje.estaVacia()) {
            StringBuilder sb = new StringBuilder("Camino: {");
            for (int i = 1; i <= viaje.longitud(); i++) {
                sb.append(viaje.recuperar(i).getNombre());
                if (i < viaje.longitud())
                    sb.append(" -> ");
            }
            sb.append('}');
            System.out.println(sb.toString());
            logger.mostrarCaminoConMenorDistancia(origen, destino);
        }
    }

    public void mostrarCaminoConMenosCiudades() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine().toUpperCase();

        Lista<Ciudad> viaje = dataHelper.obtenerCaminoConMenosCiudades(origen, destino);
        if (!viaje.estaVacia()) {
            StringBuilder sb = new StringBuilder("Camino: {");
            for (int i = 1; i <= viaje.longitud(); i++) {
                sb.append(viaje.recuperar(i).getNombre());
                if (i < viaje.longitud())
                    sb.append(" -> ");
            }
            sb.append('}');
            System.out.println(sb.toString());
            logger.mostrarCaminoConMenosCiudades(origen, destino);
        }
    }

    public void mostrarCaminoPosibles() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine().toUpperCase();

        Lista<Lista<Ciudad>> viajes = dataHelper.obtenerCaminoPosibles(origen, destino);
        if (!viajes.estaVacia()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= viajes.longitud(); i++) {
                Lista<Ciudad> viaje = viajes.recuperar(i);
                sb.append("Camino").append(i).append(": {");
                for (int j = 1; j <= viaje.longitud(); j++) {
                    sb.append(viaje.recuperar(j).getNombre());
                    if (j < viaje.longitud())
                        sb.append(" -> ");
                }
                sb.append('}');
            }
            sb.append('}');
            System.out.println(sb.toString());
            logger.mostrarCaminoPosibles(origen, destino);
        }
    }

    public void mostrarCaminoMasCortoEntreCiudad() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad de destino1:");
        String destino1 = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la ciudad de destino2:");
        String destino2 = TecladoIn.readLine().toUpperCase();

        Lista<Ciudad> viaje = dataHelper.obtenerCaminoMasCortoEntreCiudad(origen, destino1, destino2);
        if (!viaje.estaVacia()) {

            StringBuilder sb = new StringBuilder("Camino: {");
            for (int i = 1; i <= viaje.longitud(); i++) {
                sb.append(viaje.recuperar(i).getNombre());
                if (i < viaje.longitud()) {
                    sb.append(" -> ");
                }
            }
            sb.append('}');
            System.out.println(sb.toString());
            logger.mostrarCaminoMasCortoEntreCiudad(origen, destino1, destino2);
        }
    }

    public void mostrarTablaPosiciones() {
        ColaPrioridad<Equipo> equipos = dataHelper.obtenerEquiposPorPuntaje();
        StringBuilder sb = new StringBuilder("Posicion | Puntos -> Equipo\n");
        mostrarTablaPosiciones(equipos, sb, 0);
        System.out.println(sb.toString());
        logger.mostrarTablaPosiciones();
    }

    private int mostrarTablaPosiciones(ColaPrioridad<Equipo> cola, StringBuilder sb, int pos) {
        int posicion = pos;
        if (!cola.estaVacia()) {
            Equipo e = cola.obtenerFrente();
            cola.eliminarFrente();
            posicion = mostrarTablaPosiciones(cola, sb, posicion) - 1;
            sb.append('\t').append(-posicion).append(" | ").append(e.getPuntos()).append(" -> ").append(e.getPais()).append('\n');
        }
        return posicion;
    }

    public void mostrarSistema() {
        System.out.println(dataHelper);
        logger.showStructures(dataHelper);
    }

    public void guardarSistema() {
        try {
            filesHelper.saveData(dataHelper);
            logger.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportarSistema() {
        try {
            System.out.println("Indique la direccion donde se quiere exportar el archivo:");
            String file = TecladoIn.readLine().toUpperCase();
            filesHelper.exportData(dataHelper, file);
            logger.write("Se exportaron los datos a " + file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void ImportarSistema() {
        try {
            System.out.println("Indique la direccion donde se encuentra el archivo a importar");
            String fileName = TecladoIn.readLine();
            filesHelper.importData(dataHelper, fileName);
            logger.importData(fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
