import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteService {

    Scanner in = new Scanner(System.in);
    int opcion;

    public void insertarEstudiantes(Connection conn) throws SQLException {

        System.out.println("\nDigite nombre del estudiante: ");
        String nombre = in.nextLine();
        System.out.println("Digite apellido del estudiante: ");
        String apellido = in.nextLine();
        System.out.println("Digite el correo del estudiante: ");
        String correo = in.nextLine();
        System.out.println("Digite la edad del estudiante: ");
        int edad = in.nextInt();
        in.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("Digite el estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
        String estado_civil = in.nextLine();
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
        var stm = conn.prepareStatement(sql);
        stm.setString(1, nombre);
        stm.setString(2, apellido);
        stm.setString(3, correo);
        stm.setInt(4, edad);
        stm.setString(5, estado_civil);
        try {
            int rs = stm.executeUpdate();
            if (rs > 0) {
                System.out.println("Registro insertado de forma correcta");
            } else {
                System.out.println("Fallo en la inserción");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }
    }

    public void actualizarEstudiante(Connection conn, int id) throws SQLException {

        do {
            String sql = "SELECT * FROM estudiantes WHERE id=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (!rs.next()) {
                System.out.println("No se encontró un estudiante con el ID proporcionado.");
                return;
            }
            System.out.println("----------ESTUDIANTE-----------");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Apellido: " + rs.getString("apellido"));
            System.out.println("Correo: " + rs.getString("correo"));
            System.out.println("Edad: " + rs.getInt("edad"));
            System.out.println("Estado Civil: " + rs.getString("estado_civil"));
            System.out.println("-------------------------------");

            System.out.println("\n--- ACTUALIZAR DATOS ---");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Correo");
            System.out.println("4. Edad");
            System.out.println("5. Estado Civil");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            opcion = in.nextInt();
            in.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nDigite el nuevo nombre: ");
                    String nuevoNombre = in.nextLine();
                    String sqlNombre = "UPDATE estudiantes SET nombre=? WHERE id=?";
                    PreparedStatement stmNombre = conn.prepareStatement(sqlNombre);
                    stmNombre.setString(1, nuevoNombre);
                    stmNombre.setInt(2, id);
                    int rsNombre = stmNombre.executeUpdate();
                    if (rsNombre > 0) {
                        System.out.println("Nombre actualizado de forma correcta");
                    } else {
                        System.out.println("Fallo en la actualizacion del nombre");
                    }
                    break;
                case 2:
                    System.out.println("\nDigite el nuevo apellido: ");
                    String nuevoApellido = in.nextLine();
                    String sqlApellido = "UPDATE estudiantes SET apellido=? WHERE id=?";
                    PreparedStatement stmApellido = conn.prepareStatement(sqlApellido);
                    stmApellido.setString(1, nuevoApellido);
                    stmApellido.setInt(2, id);
                    int rsApellido = stmApellido.executeUpdate();
                    if (rsApellido > 0) {
                        System.out.println("Apellido actualizado de forma correcta");
                    } else {
                        System.out.println("Fallo en la actualizacion del apellido");
                    }
                    break;
                case 3:
                    System.out.println("\nDigite el nuevo correo: ");
                    String nuevoCorreo = in.nextLine();
                    String sqlCorreo = "UPDATE estudiantes SET correo=? WHERE id=?";
                    PreparedStatement stmCorreo = conn.prepareStatement(sqlCorreo);
                    stmCorreo.setString(1, nuevoCorreo);
                    stmCorreo.setInt(2, id);
                    int rsCorreo = stmCorreo.executeUpdate();
                    if (rsCorreo > 0) {
                        System.out.println("Correo actualizado de forma correcta");
                    } else {
                        System.out.println("Fallo en la actualizacion del correo");
                    }
                    break;
                case 4:
                    System.out.println("\nDigite la nueva edad: ");
                    int nuevaEdad = in.nextInt();
                    in.nextLine();
                    String sqlEdad = "UPDATE estudiantes SET edad=? WHERE id=?";
                    PreparedStatement stmEdad = conn.prepareStatement(sqlEdad);
                    stmEdad.setInt(1, nuevaEdad);
                    stmEdad.setInt(2, id);
                    int rsEdad = stmEdad.executeUpdate();
                    if (rsEdad > 0) {
                        System.out.println("Edad actualizada de forma correcta");
                    } else {
                        System.out.println("Fallo en la actualizacion de la edad");
                    }
                    break;
                case 5:
                    System.out.println(
                            "\nDigite el nuevo estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                    String nuevoEstadoCivil = in.nextLine();
                    String sqlEstadoCivil = "UPDATE estudiantes SET estado_civil=? WHERE id=?";
                    PreparedStatement stmEstadoCivil = conn.prepareStatement(sqlEstadoCivil);
                    stmEstadoCivil.setString(1, nuevoEstadoCivil);
                    stmEstadoCivil.setInt(2, id);
                    int rsEstadoCivil = stmEstadoCivil.executeUpdate();
                    if (rsEstadoCivil > 0) {
                        System.out.println("Estado civil actualizado de forma correcta");
                    } else {
                        System.out.println("Fallo en la actualizacion del estado civil");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del menú de actualización...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 6);
    }

    public void eliminarEstudiante(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM estudiantes WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        int rs = stm.executeUpdate();
        if (rs > 0) {
            System.out.println("\nRegistro eliminado de forma correcta");
        } else {
            System.out.println("\nFallo en la eliminacion");
        }
    }

    public void listarEstudiantes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        boolean hayEstudiantes = false;
        while (rs.next()) {
            hayEstudiantes = true;
            System.out.println("-------------------------------");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Apellido: " + rs.getString("apellido"));
            System.out.println("Correo: " + rs.getString("correo"));
            System.out.println("Edad: " + rs.getInt("edad"));
            System.out.println("Estado Civil: " + rs.getString("estado_civil"));
            System.out.println("-------------------------------");
        }

        if (!hayEstudiantes) {
            System.out.println("No hay estudiantes registrados.");
        }
    }

    public void buscarEstudiantePorCorreo(Connection conn, String correo) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, correo);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            System.out.println("-------------------------------");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Apellido: " + rs.getString("apellido"));
            System.out.println("Correo: " + rs.getString("correo"));
            System.out.println("Edad: " + rs.getInt("edad"));
            System.out.println("Estado Civil: " + rs.getString("estado_civil"));
            System.out.println("-------------------------------");
        } else {
            System.out.println("No se encontró un estudiante con el correo proporcionado.");
        }
    }
}
