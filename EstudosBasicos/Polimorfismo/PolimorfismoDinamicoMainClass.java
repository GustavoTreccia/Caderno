import java.util.List;
import java.util.ArrayList;

public class PolimorfismoDinamicoMainClass {

    public static float valorASerPago = 1000.0f;
    public static Integer porcentagemTotal = 0;
    public static float descontoTotal = 0.0f;

    public static void main(String args[]) {

        ServicoDeBoleto servico = new ServicoDeBoleto();

        System.out.println("Descrição do boleto: " + servico.exibirDescricoes());
        System.out.println("Valor a ser pago: " + valorASerPago);
        System.out.println("Porcentagem de boleto: " + porcentagemTotal);
        System.out.println("Desconto total: " + descontoTotal);

        // servico.exibirDescricoes(); // Already printed above
        servico.calcularNovoTotalComDesconto(valorASerPago);
        servico.calcularPorcentagemTotalBoleto();
        servico.exibirValorASerPago();
        
    }
    
}
