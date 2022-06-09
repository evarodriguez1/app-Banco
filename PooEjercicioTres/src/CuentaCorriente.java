public class CuentaCorriente {

    private int numeroDeCuenta;
    private double saldo;

    public CuentaCorriente(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
        saldo = 0;
    }

    //getters setters
    public int getNumeroDeCuenta() {
        return numeroDeCuenta;
    }
    public void setNumeroDeCuenta(int numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void ingresarDinero(double monto){
        saldo = saldo + monto;
    }
    public void retirarDinero(double monto){
        saldo = saldo - monto;
    }
}
