import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private String apellido;
    private int dni;
    private ArrayList<CuentaCorriente> cuentasCorrientes;

    public Cliente(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        cuentasCorrientes = new ArrayList<CuentaCorriente>();
    }

    public int getDni() {
        return dni;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public ArrayList<CuentaCorriente> getCuentasCorrientes() {
        return cuentasCorrientes;
    }

    public void mostrarCuentasCorrientes() {
        for (int i = 0; i < cuentasCorrientes.size(); i++) {
            System.out.println("Numero de cuenta: "+cuentasCorrientes.get(i).getNumeroDeCuenta());
            System.out.println("Saldo: "+cuentasCorrientes.get(i).getSaldo());
            System.out.println("**********************************");
        }
    }
    public void mostrarNumerosDeCuentas() {
        for (int i = 0; i < cuentasCorrientes.size(); i++) {
            System.out.println(i+") "+cuentasCorrientes.get(i).getNumeroDeCuenta());
        }
    }

    public boolean existeCuentaCorriente(int numeroDeCuenta) {
        boolean encontrado = false;
        int i = 0;
        while (encontrado != true && i < cuentasCorrientes.size()){
            if (cuentasCorrientes.get(i).getNumeroDeCuenta() == numeroDeCuenta){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
    public CuentaCorriente getCuentaSegunNumeroDeCuenta(int numeroDeCuenta) {
        boolean encontrado = false;
        int i = 0;
        CuentaCorriente cuentaCorriente = null;
        while (encontrado != true && i < cuentasCorrientes.size()) {
            if (cuentasCorrientes.get(i).getNumeroDeCuenta() == numeroDeCuenta) {
                cuentaCorriente = cuentasCorrientes.get(i);
                encontrado = true;
            }
            i++;
        }
        return cuentaCorriente;
    }
    public int getIndiceDeCuentaSegunNumeroDeCuenta(int numeroDeCuenta) {
        boolean encontrado = false;
        int i = 0;
        int posicion = -1;
        while (encontrado != true && i < cuentasCorrientes.size()) {
            if (cuentasCorrientes.get(i).getNumeroDeCuenta() == numeroDeCuenta) {
                encontrado = true;
                posicion = i;
            }
            i++;
        }
        return posicion;
    }
    public void eliminarCuenta(int numeroDeCuenta) {
        if (existeCuentaCorriente(numeroDeCuenta)){
            getCuentasCorrientes().remove(getIndiceDeCuentaSegunNumeroDeCuenta(numeroDeCuenta));
            System.out.println("La cuenta ha sido eliminada.");
        }else {
            System.out.println("La cuenta ingresada no es correcta.");
        }
    }
}
