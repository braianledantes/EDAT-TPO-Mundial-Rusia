import entidades.Ciudad;
import entidades.Equipo;
import entidades.Partido;
import estructuras.lineales.Lista;
import utilidades.ArchivosHelper;
import utilidades.DatosHelper;
import utilidades.Log;
import utilidades.TecladoIn;

import java.io.IOException;

public class MainTerminal {
    public static DatosHelper datosHelper;
    public static ArchivosHelper archivosHelper;
    public static Log log;

    public static void main(String[] args) {
        archivosHelper = ArchivosHelper.getInstance();
        log = new Log(false);
        log.inicioPrograma();
        try {
            datosHelper = archivosHelper.obtenerDatos();
        } catch (IOException e) {
            datosHelper = DatosHelper.getInstance();
        }
        showMainMenu();
        log.cierrePrograma();
    }

    public static void showMainMenu() {
        int option = 1;
        String sb = "----------------Main menu----------------\n" +
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
                    showABMCiudades();
                    break;
                case 2:
                    showABMEquipos();
                    break;
                case 3:
                    showAltaPartidos();
                    break;
                case 4:
                    showConsultarEquipos();
                    break;
                case 5:
                    showConsultarCiudades();
                    break;
                case 6:
                    showConsultarViajes();
                    break;
                case 7:
                    showTablaPosiciones();
                    break;
                case 8:
                    showSystem();
                    break;
                case 9:
                    saveSystem();
                    break;
                case 10:
                    exportSystem();
                    break;
                case 11:
                    importSystem();
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

    public static void showABMCiudades() {
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

    public static void altaCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine();
        System.out.println("Ingrese la superficie en KM^2:");
        double superficie = TecladoIn.readLineDouble();
        System.out.println("Ingrese la cantidad de habitantes:");
        int cantHab = TecladoIn.readLineInt();
        System.out.println("¿Es sede de algun partido? (true/false)");
        boolean sede = TecladoIn.readLineBoolean();

        if (datosHelper.altaCiudad(nombre, superficie, cantHab, sede))
            log.altaCiudad(nombre, superficie + "", cantHab + "", sede + "");
        else
            System.err.println("La ciudad " + nombre + " ya existe");
    }

    public static void bajaCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine();
        if (datosHelper.eliminarCiudad(nombre))
            log.bajaCiudad(nombre);
        else
            System.err.println("La ciudad " + nombre + " no existe");
    }

    public static void modificarCiudad() {
        System.out.println("Ingrese el nombre:");
        String nombre = TecladoIn.readLine();
        System.out.println("Ingrese la superficie en KM^2:");
        double superficie = TecladoIn.readLineDouble();
        System.out.println("Ingrese la cantidad de habitantes:");
        int cantHab = TecladoIn.readLineInt();
        System.out.println("¿Es sede de algun partido? (true/false)");
        boolean sede = TecladoIn.readLineBoolean();

        if (datosHelper.modificarCiudad(nombre, superficie, cantHab, sede))
            log.modificaCiudad(nombre, superficie + "", cantHab + "", sede + "");
        else
            System.err.println("La ciudad " + nombre + " no existe");
    }

    public static void showABMEquipos() {
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

    public static void altaEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine();
        System.out.println("Ingrese el nombre del director tecnico:");
        String dt = TecladoIn.readLine();
        System.out.println("Ingrese el grupo (A, B, C, D, E, F, G o H) :");
        char grupo = TecladoIn.readLineNonwhiteChar();

        try {
            boolean alta = datosHelper.altaEquipo(nombre, dt, grupo);
            if (!alta)
                System.err.println("El equipo " + nombre + " ya existe");

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void bajaEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine();
        if (!datosHelper.bajaEquipo(nombre))
            System.err.println("El equipo " + nombre + " no existe");
    }

    public static void modificarEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String nombre = TecladoIn.readLine();
        System.out.println("Ingrese el nombre del director tecnico:");
        String dt = TecladoIn.readLine();
        System.out.println("Ingrese el grupo (A, B, C, D, E, F, G o H) :");
        char grupo = TecladoIn.readLineNonwhiteChar();
        System.out.println("Ingrese el puntaje:");
        int puntaje = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles a favor:");
        int golesAFavor = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles en contra:");
        int golesEnContra = TecladoIn.readLineInt();

        try {
            boolean modifico = datosHelper.modificarEquipo(nombre, dt, grupo, puntaje, golesAFavor, golesEnContra);
            if (!modifico)
                System.err.println("El equipo " + nombre + " no existe");

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void showAltaPartidos() {
        System.out.println("Ingrese el nombre del equipo A:");
        String eA = TecladoIn.readLine();
        System.out.println("Ingrese el nombre del equipo B:");
        String eB = TecladoIn.readLine();
        System.out.println("Ingrese la ronda (GRUPO, OCTAVOS, CUARTOS, SEMIFINAL, FINAL):");
        String ronda = TecladoIn.readLine();
        System.out.println("Ingrese los goles del equipo A:");
        int golesA = TecladoIn.readLineInt();
        System.out.println("Ingrese los goles del equipo B:");
        int golesB = TecladoIn.readLineInt();

        try {
            if (datosHelper.altaDePartido(eA, eB, ronda, golesA, golesB))
                log.altaDePartido(eA, eB, ronda, golesA + "", golesB + "");
            else
                System.err.println("El partido ya existe");
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void showConsultarEquipos() {
        int option = 1;
        String sb = "----------------Consultar equipos----------------\n" +
                "1) Mostrar datos de equipo" + '\n' +
                "2) Mostrar rango de equipos por puntaje" + '\n' +
                "3) Modificar equipos con diferencia negativa de goles" + '\n' +
                "-1) Salir" + '\n';
        do {
            if (option >= 1 && option <= 3)
                System.out.println(sb);
            option = TecladoIn.readLineInt();
            switch (option) {
                case 1:
                    showEquipo();
                    break;
                case 2:
                    showRangoEquipos();
                    break;
                case 3:
                    showEquiposConDifGol();
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

    public static void showEquipo() {
        System.out.println("Ingrese el nombre del pais:");
        String pais = TecladoIn.readLine();
        Equipo equipo = datosHelper.obtenerEquipo(pais);
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
        } else {
            System.err.println("No existe equipo " + pais);
        }
    }

    public static void showRangoEquipos() {
        // TODO showRangoEquipos()
        System.out.println("Ingrese el nombre de un pais:");
        String equipoA = TecladoIn.readLine();
        System.out.println("Ingrese el nombre del otro pais:");
        String equipoB = TecladoIn.readLine();

        if (equipoB.compareTo(equipoA) < 0) { // por si estan alrevez
            String aux = equipoA;
            equipoA = equipoB;
            equipoB = aux;
        }
        Lista<Equipo> equipos = datosHelper.listarEquiposPorRango(equipoA, equipoB);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= equipos.longitud(); i++) {
            Equipo equipo = equipos.recuperar(i);
            sb.append(equipo).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void showEquiposConDifGol() {
        Lista<Equipo> equipos = datosHelper.listarEquiposConDifGolNeg();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= equipos.longitud(); i++) {
            Equipo equipo = equipos.recuperar(i);
            sb.append(equipo.getPais()).append(" -> ").append(equipo.diferenciaGoles()).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void showConsultarCiudades() {
        System.out.println("Ingrese el nombre de la ciudad:");
        String nombre = TecladoIn.readLine();
        Ciudad ciudad = datosHelper.getCiudad(nombre);
        if (ciudad != null)
            System.out.println(ciudad);
        else
            System.err.println("No existe " + nombre);
    }

    public static void showConsultarViajes() {
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
                    showCaminoConMenorDistancia();
                    break;
                case 2:
                    showCaminoConMenosCiudades();
                    break;
                case 3:
                    showCaminoPosibles();
                    break;
                case 4:
                    showCaminoMasCortoEntreCiudad();
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

    public static void showCaminoConMenorDistancia() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine();

        Lista<Ciudad> viaje = datosHelper.obtenerCaminoConMenorDistancia(origen, destino);
        StringBuilder sb = new StringBuilder("Camino: {");
        for (int i = 1; i <= viaje.longitud(); i++) {
            sb.append(viaje.recuperar(i).getNombre());
            if (i < viaje.longitud())
                sb.append(" -> ");
        }
        sb.append('}');
        System.out.println(sb.toString());
    }

    public static void showCaminoConMenosCiudades() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine();

        Lista<Ciudad> viaje = datosHelper.obtenerCaminoConMenosCiudades(origen, destino);
        StringBuilder sb = new StringBuilder("Camino: {");
        for (int i = 1; i <= viaje.longitud(); i++) {
            sb.append(viaje.recuperar(i).getNombre());
            if (i < viaje.longitud())
                sb.append(" -> ");
        }
        sb.append('}');
        System.out.println(sb.toString());
    }

    public static void showCaminoPosibles() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine();
        System.out.println("Ingrese la ciudad de destino:");
        String destino = TecladoIn.readLine();

        Lista<Lista<Ciudad>> viajes = datosHelper.obtenerCaminoPosibles(origen, destino);
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
    }

    public static void showCaminoMasCortoEntreCiudad() {
        System.out.println("Ingrese la ciudad de origen:");
        String origen = TecladoIn.readLine();
        System.out.println("Ingrese la ciudad de destino1:");
        String destino1 = TecladoIn.readLine();
        System.out.println("Ingrese la ciudad de destino2:");
        String destino2 = TecladoIn.readLine();

        Lista<Ciudad> viaje = datosHelper.obtenerCaminoMasCortoEntreCiudad(origen, destino1, destino2);
        StringBuilder sb = new StringBuilder("Camino: {");
        for (int i = 1; i <= viaje.longitud(); i++) {
            sb.append(viaje.recuperar(i).getNombre());
            if (i < viaje.longitud())
                sb.append(" -> ");
        }
        sb.append('}');
        System.out.println(sb.toString());
    }

    public static void showTablaPosiciones() {
        // TODO
        // Obtener y mostrar la tabla de posiciones de los equipos de un momento dado, almacenando
        // los datos de los equipos ordenados de mayor a menor puntaje (puede utilizar alguna
        // estructura de datos auxiliar que considere apropiada, asegurando la eficiencia)
    }

    public static void showSystem() {
        System.out.println(datosHelper);
        log.mostrarEstructuras(datosHelper);
    }

    public static void saveSystem() {
        try {
            archivosHelper.guardarDatos(datosHelper);
            log.guardarDatos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportSystem() {
        try {
            System.out.println("Indique la direccion absoluta donde se quiere exportar el archivo:");
            String file = TecladoIn.readLine();
            archivosHelper.exportarDatos(datosHelper, file);
            log.escribir("Se exportaron los datos a " + file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void importSystem() {
        try {
            System.out.println("Indique la direccion absoluta donde se encuentra el archivo a importar");
            String file = TecladoIn.readLine();
            archivosHelper.importarDatos(datosHelper, file);
            log.escribir("Se importaron los datos desde " + file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
