package Vistas;

import Modelo.Alumno;
import Modelo.Materia;
import Persistencia.AlumnoData;
import Persistencia.MateriaData;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class GP1ProyectoTransversal{

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        // Crear una instancia de AlumnoData y MateriaData
        AlumnoData alumnoData = new AlumnoData();
        MateriaData materiaData = new MateriaData();
        
            boolean salir = false;
                while (!salir) {
                    System.out.println("Menú principal:");
                    System.out.println("1. Menú de Alumnos");
                    System.out.println("2. Menú de Materias");
                    System.out.println("3. Salir");
                    System.out.print("Seleccione una opción: ");
                    String opcionStr = entrada.nextLine();
                    int opcion = Integer.parseInt(opcionStr);

                    switch (opcion) {
                        case 1:
                            menuAlumnos(entrada, alumnoData);
                            break;
                        case 2:
                            menuMaterias(entrada, materiaData);
                            break;
                        case 3:
                            salir = true;
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }

                System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
            }

            public static void menuAlumnos(Scanner entrada, AlumnoData alumnoData) {
                boolean salir = false;

                while (!salir) {
                    System.out.println("Menú de opciones Alumnos:");
                    System.out.println("1. Agregar alumno");
                    System.out.println("2. Listar alumnos");
                    System.out.println("3. Buscar alumno por DNI");
                    System.out.println("4. Dar de baja alumno");
                    System.out.println("5. Dar de alta alumno");
                    System.out.println("6. Modificar un alumno");
                    System.out.println("7. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");

            // Cambiamos a nextLine para evitar problemas
            String opcionStr = entrada.nextLine();
            int opcion = Integer.parseInt(opcionStr);  // Convertir a entero

            switch (opcion) {
                case 1:
                    try{

                    System.out.print("Ingrese el nombre del alumno: ");
                    String nombre = entrada.nextLine();

                    System.out.print("Ingrese el apellido del alumno: ");
                    String apellido = entrada.nextLine();

                    System.out.print("Ingrese el DNI del alumno: ");
                    String dniStr = entrada.nextLine();
                    int dni = Integer.parseInt(dniStr);

                    System.out.print("Ingrese la fecha de nacimiento (AAAA-MM-DD), Ejemplo: 2002-12-02: ");
                    String fecha = entrada.nextLine();
                    LocalDate fechaNacimiento = LocalDate.parse(fecha);

                    System.out.print("Ingrese el email del alumno: ");
                    String email = entrada.nextLine();

                    System.out.print("Ingrese el teléfono del alumno: ");
                    String telefonoStr = entrada.nextLine();
                    long telefono = Long.parseLong(telefonoStr);

                    Alumno alumno = new Alumno(nombre, apellido, dni, fechaNacimiento, email, telefono, true);
                    alumnoData.agregarAlumno(alumno);
                    System.out.println("Alumno agregado exitosamente.");
                    //Controles
                    } catch (NumberFormatException e) {
                    System.out.println("Error: Formato inválido en DNI o teléfono. Por favor, ingrese números válidos.");
                    } catch (DateTimeParseException e) {
                    System.out.println("Error: Formato inválido de fecha. Por favor, ingrese la fecha en formato AAAA-MM-DD.");
                    } 
                    break;

                case 2: 
                    // Listar alumnos
                    List<Alumno> alumnos = alumnoData.listarAlumno();
                    for (Alumno a : alumnos) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    // Buscar alumno por DNI
                    try{
                    System.out.print("Ingrese el DNI del alumno que desea buscar: ");
                    String buscarDniStr = entrada.nextLine();
                    int buscarDni = Integer.parseInt(buscarDniStr);
                    Alumno existe = alumnoData.buscarAlumno(buscarDni);
                    if (existe != null) {
                        System.out.println("Alumno encontrado: " + existe);
                    } else {
                        System.out.println("Alumno no encontrado");
                    }
                    }catch (NumberFormatException e){
                        System.out.println("Error: Formato inválido de DNI. Por favor, ingrese un número válido.");
                    }
                    break;

                case 4:
                    // Dar de baja alumno
                    try{
                    System.out.print("Ingrese el DNI del alumno que desea dar de baja: ");
                    String dniBajaStr = entrada.nextLine();
                    int dniBaja = Integer.parseInt(dniBajaStr);

                    Alumno alumnoBaja = alumnoData.buscarAlumno(dniBaja);
                    if (alumnoBaja != null) {
                        alumnoData.darDeBajaAlumno(dniBaja);
                        System.out.println("Alumno dado de baja.");
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    }catch(NumberFormatException e){
                        System.out.println("Error: Formato inválido de DNI. Por favor, ingrese un número válido.");
                    }
                    break;

                case 5:
                    // Dar de alta alumno
                    System.out.print("Ingrese el DNI del alumno que desea dar de alta: ");
                    String dniAltaStr = entrada.nextLine();
                    int dniAlta = Integer.parseInt(dniAltaStr);
                    Alumno alumnoAlta = alumnoData.buscarAlumno(dniAlta);
                    if (alumnoAlta != null) {
                        alumnoData.darDeAltaAlumno(dniAlta);
                        System.out.println("Alumno dado de alta.");
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese el DNI del alumno que desea modificar: ");
                    String dniModificarStr = entrada.nextLine();

                    try {
                        int dniModificar = Integer.parseInt(dniModificarStr);
                        Alumno alumnoModificar = alumnoData.buscarAlumno(dniModificar);

                        if (alumnoModificar != null) {
                            // Alumno encontrado, pedir nuevos datos
                            System.out.println("Alumno encontrado: " + alumnoModificar);

                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = entrada.nextLine();

                            System.out.print("Ingrese el nuevo apellido: ");
                            String nuevoApellido = entrada.nextLine();

                            System.out.print("Ingrese el nuevo DNI: ");
                            String nuevoDniStr = entrada.nextLine();
                            int nuevoDni = Integer.parseInt(nuevoDniStr);

                            System.out.print("Ingrese la nueva fecha de nacimiento (AAAA-MM-DD), Ejemplo: 2002-12-02: ");
                            String nuevaFechaNacimientoStr = entrada.nextLine();
                            LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr);

                            System.out.print("Ingrese el nuevo email: ");
                            String nuevoEmail = entrada.nextLine();

                            System.out.print("Ingrese el nuevo teléfono: ");
                            String nuevoTelefonoStr = entrada.nextLine();
                            long nuevoTelefono = Long.parseLong(nuevoTelefonoStr);

                            System.out.print("Ingrese el nuevo estado(0= inactivo, 1= activo): ");
                            int valor = entrada.nextInt();
                            boolean nuevoEstado = valor == 1; // Si valor es 1, sera true. Caso contrario sera falso


                            // Llamar al método para modificar al alumno
                            alumnoData.modificarAlumno(alumnoModificar.getId_alumno(), nuevoNombre, nuevoApellido, nuevoDni, nuevaFechaNacimiento, nuevoEmail, nuevoTelefono, nuevoEstado);
                            System.out.println("Alumno fue modificado");
                        } else {
                            System.out.println("Alumno no encontrado");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Asegúrese de ingresar números correctos para DNI y teléfono.");
                    } catch(DateTimeParseException e){
                        System.out.println("Error: Formato inválido de fecha. Por favor, ingrese la fecha en formato AAAA-MM-DD.");
                    }
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                }
                    if (!salir) {
                        System.out.println("¿Desea realizar otra acción? (si/no): ");
                        String respuesta = entrada.nextLine();
                        if (respuesta.equalsIgnoreCase("no")) {
                        salir = true;
                        System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                        }
                    }
                }
            }
             public static void menuMaterias(Scanner entrada, MateriaData materiaData) {
                boolean salir = false;

                while (!salir) {
                    System.out.println("Menú de opciones Materias:");
                    System.out.println("1. Agregar materia");
                    System.out.println("2. Listar materias");
                    System.out.println("3. Buscar materia por nombre");
                    System.out.println("4. Dar de baja materia");
                    System.out.println("5. Dar de alta materia");
                    System.out.println("6. Modificar una materia");
                    System.out.println("7. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");

                    String opcionStr = entrada.nextLine();
                    int opcion = Integer.parseInt(opcionStr);

            switch (opcion) {
                case 1:
                    try{
                     System.out.print("Ingrese el nombre de la materia: ");
                            String nombre_materia = entrada.nextLine();

                            System.out.print("Ingrese el año de cursada de la materia (Primero, Segundo, Tercero): ");
                            String year = entrada.nextLine();

                            System.out.print("Ingrese el estado (0 = inactivo, 1 = activo): ");
                            int estado = entrada.nextInt();
                            entrada.nextLine(); // Limpiar buffer después de nextInt()
                            boolean nuevoEstado = estado == 1;

                            Materia materia = new Materia(nombre_materia, year, nuevoEstado);
                            materiaData.agregarMateria(materia);
                            System.out.println("Materia agregada exitosamente.");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Formato inválido en el estado. Debe ingresar 0 o 1.");
                        }
                        break;


                case 2:
                   // Listar materias
                        List<Materia> materias = materiaData.listarMateria();
                        for (Materia m : materias) {
                            System.out.println(m);
                        }
                        break;

                case 3:
                    // Buscar materia por nombre
                        System.out.print("Ingrese el nombre de la materia que desea buscar: ");
                        String buscarNombre = entrada.nextLine();
                        Materia materiaEncontrada = materiaData.buscarMateria(buscarNombre);
                        if (materiaEncontrada != null) {
                            System.out.println("Materia encontrada: " + materiaEncontrada);
                        } else {
                            System.out.println("No se encontró una materia con ese nombre.");
                        }
                        break;


                case 4:
                    // Dar de baja materia
                        System.out.print("Ingrese el nombre de la materia que desea dar de baja: ");
                        String nombreBaja = entrada.nextLine();
                       /* materiaData.darDeBajaMateria(nombreBaja);*/
                        System.out.println("Materia dada de baja.");
                        break;


                case 5:
                    // Dar de alta materia
                        System.out.print("Ingrese el nombre de la materia que desea dar de alta: ");
                        String nombreAlta = entrada.nextLine();
                        //materiaData.darDeAltaMateria(nombreAlta);
                        System.out.println("Materia dada de alta.");
                        break;

                case 6:
                     // Modificar una materia
                        System.out.print("Ingrese el nombre de la materia que desea modificar: ");
                        String nombreModificar = entrada.nextLine();
                        Materia materiaModificar = materiaData.buscarMateria(nombreModificar);

                        if (materiaModificar != null) {
                            // Materia encontrada, pedir nuevos datos
                            System.out.println("Materia encontrada: " + materiaModificar);

                            System.out.print("Ingrese el nuevo nombre: ");
                            String nuevoNombre = entrada.nextLine();

                            System.out.print("Ingrese el nuevo año de cursada: ");
                            String nuevoYear = entrada.nextLine();

                            System.out.print("Ingrese el nuevo estado (0 = inactivo, 1 = activo): ");
                            int nuevoValor = entrada.nextInt();
                            entrada.nextLine(); // Limpiar el buffer
                            boolean nuevoEstado = nuevoValor == 1;

                            int id_materia = materiaModificar.getId_materia();

                            materiaData.modificarMateria( id_materia, nuevoNombre, nuevoYear, nuevoEstado);
                            System.out.println("Materia modificada exitosamente.");
                        } else {
                            System.out.println("Materia no encontrada.");
                        }
                        break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
            if (!salir) {
                System.out.println("¿Desea realizar otra acción? (si/no): ");
                String respuesta = entrada.nextLine();
                if (respuesta.equalsIgnoreCase("no")) {
                    salir = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                }
            }
        } 
    }
}
