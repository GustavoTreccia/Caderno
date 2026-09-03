public class ServicoDeBoleto {

    ServicoDeBoleto nacional = new Servico1();
    ServicoDeBoleto internacional = new Servico2();

    public float calcularNovoTotalComDesconto(float valorASerPago) {
        descontoTotal += calcularDescontoBoleto();
        valorASerPago -= descontoTotal;
        return descontoTotal;
    }

    public float calcularPorcentagemTotalBoleto() {
        porcentagemTotal += calcularPorcentagemBoleto();
        return porcentagemTotal;
    }

    public String exibirDescricoes() {
        return obterDescricaoBoleto();
    }

    public void exibirValorASerPago() {
        System.out.println("Valor a ser pago: " + valorASerPago);
        System.out.println("Porcentagem de boleto: " + porcentagemTotal);
        for (ServicoDeBoleto servico : servicos) {
            System.out.println("Boleto selecionado: " + servico.obterDescricaoBoleto());
        }
    }
    
}
