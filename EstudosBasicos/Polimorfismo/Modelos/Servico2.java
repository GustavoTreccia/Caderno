public class Servico2 implements ServicoDeBoleto {
    @Override
    public Float calcularDescontoBoleto() {
        return 400.0f;
    }

    @Override
    public Integer calcularPorcentagemBoleto() {
        return 10;
    }

    @Override
    public String obterDescricaoBoleto() {
        return "Boleto internacional";
    }
    
}
