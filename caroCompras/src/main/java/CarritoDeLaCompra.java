import java.util.Collection;

public class CarritoDeLaCompra {
    private Collection<Integer> precios;

    public CarritoDeLaCompra(Collection<Integer> precios) {
        this.precios = precios;
    }

    public int calcularPrecioTotal() {
        int precioTotal = 0;
        for (Integer precio : precios) {
            precioTotal += precio;
        }
        return precioTotal;
    }

    public int contarNumeroProductos() {
        return precios.size();
    }

    public int calcularPrecioTotalLambda() {
        int precioTotal = this.precios.stream().mapToInt(precio -> precio.intValue()).sum();
        return precioTotal;
    }

    public int calcularPrecioTotalRefMethod() {
        return this.precios.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public long calcularDescuentoTotal(int cantidadConDescuento) {
        long descuentoTotal = 0;
        for (Integer precio : precios) {
            if (precio >= cantidadConDescuento) {
                descuentoTotal += (cantidadConDescuento * 5) / 100;
            }
        }
        return descuentoTotal;
    }

    public long calcularDescuentoTotalLambda(int cantidadConDescuento) {
        Long numeroDeDescuentos = this.precios
                .stream().filter(precio -> precio.intValue() >= cantidadConDescuento).count();
        return (cantidadConDescuento * 5 / 100) * numeroDeDescuentos;
    }

    public boolean detectarError() {
        boolean negativeFind = false;
        for (Integer precio : precios) {
            if (precio < 0) {
                negativeFind = true;
            }
        }
        return negativeFind;
    }

    public boolean detectarErrorAnyMatch() {
        return this.precios.stream().anyMatch(precio -> precio.intValue() < 0);
    }

    public boolean detectarErrorFindAny() {
        return this.precios.stream().filter(precio -> precio.intValue() < 0).findAny().isPresent();
    }

    public boolean detectarErrorFindFirst() {
        return this.precios.stream().filter(precio -> precio.intValue() < 0).findFirst().isPresent();
    }

    public boolean detectarErrorAnyMatchParallel() {
        return this.precios.parallelStream().anyMatch(precio -> precio.intValue() < 0);
    }

    public boolean detectarErrorFindAnyParallel() {
        return this.precios.parallelStream().filter(precio -> precio.intValue() < 0).findAny().isPresent();
    }

    public boolean detectarErrorFindFirstParallel() {
        return this.precios.parallelStream().filter(precio -> precio.intValue() < 0).findFirst().isPresent();
    }
}
