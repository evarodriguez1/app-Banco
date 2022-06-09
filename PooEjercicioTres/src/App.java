import java.util.ArrayList;
import java.util.Scanner;
public class App {

    static ArrayList<Cliente> baseDatosClientes = new ArrayList<>();

    public static void main (String[]args){
        int funcionElegida;
        do {
            mensajeMenuFunciones();
            System.out.println("Selecciona la funcion: ");
            funcionElegida = ingresarNumeroInt();
            realizarFuncionElegida(funcionElegida);
        }while(funcionElegida != 0);
    }

    public static void realizarFuncionElegida(int funcionElegida) {
        int dni;
        int numeroDeCuenta = 0;
        int saldo = 0;
        switch (funcionElegida) {
            case 0:
                System.out.println("Gracias. Vuelva pronto.");
                break;
            case 1:
                System.out.println("Ingrese el nombre: ");
                String nombre = ingresarPalabra();
                System.out.println("Ingrese el apellido: ");
                String apellido = ingresarPalabra();
                System.out.println("Ingrese el DNI: ");
                dni = ingresarNumeroInt();
                anyadirCliente(dni,crearCliente(nombre,apellido,dni));
                break;
            case 2:
                System.out.println("Ingrese el DNI del cliente que quiere dar de baja:");
                dni = ingresarNumeroInt();
                darDeBajaCliente(dni);
                break;
            case 3:
                System.out.println("Ingrese el DNI del cliente al que quiere agregar una cuenta:");
                dni = ingresarNumeroInt();
                anyadirCuentaDeCliente(numeroDeCuenta,saldo,dni);
                break;
            case 4:
                System.out.println("Ingrese el DNI del cliente que quiere ver sus cuentas:");
                dni = ingresarNumeroInt();
                mostrarCuentasDelCliente(dni);
                break;
            case 5:
                System.out.println("Ingrese el DNI del cliente que quiere eliminar una cuenta:");
                dni = ingresarNumeroInt();
                eliminarCuentaDeCliente(dni);
                break;
            case 6:
                System.out.println("Ingrese el DNI del cliente al que quiere agregar dinero a una cuenta:");
                dni = ingresarNumeroInt();
                ingresarDineroCuenta(dni);
                break;
            case 7:
                System.out.println("Ingrese el DNI del cliente al que quiere retirar dinero de una cuenta:");
                dni = ingresarNumeroInt();
                retirarDineroCuenta(dni);
                break;
            default:
                System.out.println("La opción no es válida.");
        }
    }

    public static Cliente crearCliente(String nombre, String apellido, int dni){
        Cliente cliente = new Cliente(nombre,apellido,dni);
        return cliente;
    }
    public static void anyadirCliente(int dni,Cliente cliente ){
        if (!clienteExiste(dni)) {
            baseDatosClientes.add(cliente);
            System.out.println("El cliente se ha agregado correctamente a la base de datos.");
        }
        else {
            mensajeErrorNoPuedeAgregarClienteMismoDNI();
        }
    }
    public static void darDeBajaCliente(int dni){
        if (clienteExiste(dni)) {
            baseDatosClientes.remove(getIndiceDeCliente(dni));
            System.out.println("El cliente ha sido eliminado de la base de datos.");
        }else {
            mensajeNoEncontrado();
        }
    }
    public static CuentaCorriente crearCuentaDeCliente(int numeroDeCuenta, double saldo){
        CuentaCorriente cuentaCorriente = new CuentaCorriente(numeroDeCuenta);
        cuentaCorriente.setNumeroDeCuenta((int) (Math.random() * 15262));
        cuentaCorriente.setSaldo(saldo);
        return cuentaCorriente;
    }
    public static void anyadirCuentaDeCliente(int numeroDeCuenta,double saldo, int dni){
        if (clienteExiste(dni)) { //validacion de cuentas iguales
            CuentaCorriente cuentaCorriente = crearCuentaDeCliente(numeroDeCuenta,saldo);
            baseDatosClientes.get(getIndiceDeCliente(dni)).getCuentasCorrientes().add(cuentaCorriente);
            System.out.println("Se añadió correctamente la cuenta corriente numero: "+cuentaCorriente.getNumeroDeCuenta()+
                    " del cliente "+baseDatosClientes.get(getIndiceDeCliente(dni)).getNombre()+ " "
                    +baseDatosClientes.get(getIndiceDeCliente(dni)).getApellido());
        }  else {
            mensajeNoEncontrado();
        }
    }
    public static void mostrarCuentasDelCliente(int dni){
        if (clienteExiste(dni)) {
            Cliente cliente = baseDatosClientes.get(getIndiceDeCliente(dni));
            System.out.println("Cuentas del cliente: "+cliente.getNombre()+" "+cliente.getApellido());
            cliente.mostrarCuentasCorrientes();
        } else {
            mensajeNoEncontrado();
        }

    }
    public static void eliminarCuentaDeCliente(int dni) {
        if (clienteExiste(dni)){
            int numeroDeCuenta;
            Cliente cliente = baseDatosClientes.get(getIndiceDeCliente(dni));
            System.out.println("Ingrese el numero de cuenta que quiere eliminar: ");
            cliente.mostrarNumerosDeCuentas();
            numeroDeCuenta = ingresarNumeroInt();
            cliente.eliminarCuenta(numeroDeCuenta);
        }else {
            mensajeNoEncontrado();
        }
    }
    public static void ingresarDineroCuenta(int dni){
        if (clienteExiste(dni)) {
            int numeroDeCuenta;
            double montoAIngresar;
            Cliente cliente = baseDatosClientes.get(getIndiceDeCliente(dni));
            ArrayList<CuentaCorriente> cuentasCorrientes = cliente.getCuentasCorrientes();

            System.out.println("Ingrese el numero de cuenta al que quiere ingresar el dinero: ");
            cliente.mostrarNumerosDeCuentas();
            numeroDeCuenta = ingresarNumeroInt();

            if (cliente.existeCuentaCorriente(numeroDeCuenta)){
                System.out.println("Ingrese el monto que desea ingresar: ");
                montoAIngresar = ingresarNumeroDouble();
                int indiceCuentaCorriente = cliente.getIndiceDeCuentaSegunNumeroDeCuenta(numeroDeCuenta);
                cuentasCorrientes.get(indiceCuentaCorriente).ingresarDinero(montoAIngresar);
                System.out.println("Se ha ingresado el monto correctamente.");
            }else {
                System.out.println("La cuenta ingresada no es correcta.");
            }
        } else {
         mensajeNoEncontrado();
        }
    }
    public static void retirarDineroCuenta(int dni) {
        int numeroDeCuenta;
        double montoARetirar;

        if (clienteExiste(dni)) {

            Cliente cliente = baseDatosClientes.get(getIndiceDeCliente(dni));
            ArrayList<CuentaCorriente> cuentasCorrientes = cliente.getCuentasCorrientes();

            System.out.println("Ingrese el numero de cuenta del que quiere retirar  dinero: ");
            cliente.mostrarNumerosDeCuentas();
            numeroDeCuenta = ingresarNumeroInt();

            if (cliente.existeCuentaCorriente(numeroDeCuenta)){
                CuentaCorriente ctaCorriente = cliente.getCuentaSegunNumeroDeCuenta(numeroDeCuenta);
                System.out.println("Ingrese el monto que desea retirar: ");
                montoARetirar = ingresarNumeroDouble();
                if (ctaCorriente.getSaldo() > montoARetirar) {
                    int indiceCuentaCorriente = cliente.getIndiceDeCuentaSegunNumeroDeCuenta(numeroDeCuenta);
                    cuentasCorrientes.get(indiceCuentaCorriente).retirarDinero(montoARetirar);
                    System.out.println("Extraccion del dinero realizada correctamente.");
                }else{
                    System.out.println("No tienes dinero suficiente en la cuenta.");
                }
            }else {
                System.out.println("La cuenta ingresada no es correcta.");
            }
        }else {
            mensajeNoEncontrado();
        }
    }

    private static boolean clienteExiste(int dni) {
        boolean encontrado = false;
        int i = 0;
        while (encontrado != true && i < baseDatosClientes.size()){
            if (baseDatosClientes.get(i).getDni() == dni){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
    public static int getIndiceDeCliente(int dni) {
        int posicion = -1;
        int i = 0;
        boolean encontrado = false;
        while (encontrado != true && i < baseDatosClientes.size()){
            if (baseDatosClientes.get(i).getDni() == dni){
                posicion=i;
                encontrado = true;
            }
            i++;
        }
        return posicion;
    }

    public static void mensajeMenuFunciones(){
        System.out.println("Bienvenido, elija una opcion: ");
        System.out.println("1-Crear Cliente.");
        System.out.println("2-Dar de baja cliente.");
        System.out.println("3-Crear cuenta de un cliente.");
        System.out.println("4- Mostrar las cuentas de un cliente.");
        System.out.println("5-Eliminar una cuenta de un cliente. ");
        System.out.println("6-Ingresar euros en una cuenta de un cliente.");
        System.out.println("7-Retirar euros en una cuenta de un cliente.");
        System.out.println("0-Salir de la aplicación.");
    }
    public static void mensajeNoEncontrado(){
        System.out.println("No valido. El cliente no se encuentra en la base de datos.");
    }
    public static void mensajeErrorNoPuedeAgregarClienteMismoDNI(){
        System.out.println("No valido. El DNI ya se encuentra en la base de datos.");
    }
    public static double ingresarNumeroDouble() {
        Scanner input = new Scanner (System.in);
        double numero = input.nextDouble();
        return  numero;
    }
    public static int ingresarNumeroInt() {
        Scanner input = new Scanner (System.in);
        int numero = input.nextInt();
        return  numero;
    }
    public static String ingresarPalabra() {
        Scanner input = new Scanner (System.in);
        String palabra = input.nextLine();
        return  palabra;
    }
}
