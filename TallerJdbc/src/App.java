import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    static String url = "jdbc:mysql://localhost:3306/universidad?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static String user = "root";
    static String password = "Oso17";

    public static void main(String[] args) throws Exception {
        try(Connection conn= DriverManager.getConnection(url, user, password)){
            System.out.println("Conexion exitosa");
            EstudianteService estudianteService= new EstudianteService();
            Scanner sc = new Scanner(System.in);
            int opcion;
            
            do {
                
                System.out.println("\n--- MENU ---");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar Todos");
                System.out.println("5. Consultar por Correo");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        estudianteService.insertarEstudiantes(conn);
                        break;
                    case 2:
                        System.out.println("\nDigite el id del estudiante a actualizar: ");
                        int id=sc.nextInt();
                        sc.nextLine();
                        estudianteService.actualizarEstudiante(conn, id);
                        break;
                    case 3:
                        System.out.println("Digite el id del estudiante a eliminar: ");
                        int idDelete=sc.nextInt();
                        sc.nextLine();
                        estudianteService.eliminarEstudiante(conn, idDelete);
                        break;
                    case 4:
                        estudianteService.listarEstudiantes(conn);
                        break;
                    case 5:
                        System.out.println("Digite el correo del estudiante a buscar: ");
                        String correo=sc.nextLine();
                        estudianteService.buscarEstudiantePorCorreo(conn, correo);
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (opcion != 6);
            sc.close();
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
