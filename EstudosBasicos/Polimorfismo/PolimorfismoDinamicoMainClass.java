import java.util.List;
import java.util.ArrayList;

public class PolimorfismoDinamicoMainClass {

    private List<ServicoDeBoleto> servicos = new ArrayList<>();
    private float valorASerPago = 1000.0f;
    private Integer porcentagemTotal = 0;
    private float descontoTotal = 0.0f;

    public static void main(String args[]) {
        PolimorfismoDinamicoMainClass mainClass = new PolimorfismoDinamicoMainClass();

        ServicoDeBoleto servicoNacional = new Servico1();
        ServicoDeBoleto servicoInternacional = new Servico2();

        mainClass.adicionarServico(servicoNacional);
        mainClass.adicionarServico(servicoInternacional);

        mainClass.calcularDescontoTotalBoletoNacional();
        mainClass.calcularPorcentagemTotalBoletoInternacional();
        mainClass.exibirDescricoes();
        mainClass.exibirValorASerPago();
    }

    public void adicionarServico(ServicoDeBoleto servico) {
        servicos.add(servico);
    }

    public float calcularDescontoTotalBoletoNacional() {
        for (ServicoDeBoleto servico : servicos) {
            if ("Boleto nacional".equals(servico.obterDescricaoBoleto())) {
                descontoTotal += servico.calcularDescontoBoleto();
            }
        }
        valorASerPago -= descontoTotal;
        return descontoTotal;
    }

    public float calcularPorcentagemTotalBoletoInternacional() {
        for (ServicoDeBoleto servico : servicos) {
            if ("Boleto internacional".equals(servico.obterDescricaoBoleto())) {
                porcentagemTotal += servico.calcularPorcentagemBoleto();
            }
        }
        return porcentagemTotal;
    }

    public void exibirDescricoes() {
        for (ServicoDeBoleto servico : servicos) {
            System.out.println(servico.obterDescricaoBoleto());
        }
    }

    public void exibirValorASerPago() {
        System.out.println("Valor a ser pago: " + valorASerPago);
        System.out.println("Porcentagem de boleto internacional: " + porcentagemTotal);
    }
}
