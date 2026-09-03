public class Servico1 implements ServicoDeBoleto {
    @Override
    public Float calcularDescontoBoleto() {
        return 200.0f;
    }

    @Override
    public Integer calcularPorcentagemBoleto() {
        return 20;
    }

    @Override
    public String obterDescricaoBoleto() {
        return "Boleto nacional";
    }
    
}
