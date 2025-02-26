package pacoteManeiro;

/* Esta é a sessão de NullCheckers
	
	Uma coisa que é (ou deveria ser) analisada em Code Review é a possibilidade de NullPointers
	
	Afinal, o que são Null Checkers? 
	Em curtas palavras, Null Checkers são validações, ponto.
	Que você precisa ter em seu código a fim de não causar o lendário (e opressivo) NullPointeException.
	
	Imagine que você tem uma caixa(sua entidade), antes de utilizar o conteúdo da sua caixa, você precisa
	verificar se sua caixa está vazia!
	
	Então, no código a seguir, será demonstrado um exemplo de NullCheckers
	
	Por que usar Null Checkers? Se classes ou métodos dependem da resposta de outras classes ou métodos
	você deve ter alguma validação ou preenchimento adequado. Neste caso dos NullCheckers, você pode 
	causar um impacto direto no funcionamento da sua aplicação ou de ambientes que dependam do 
	funcionamento adequado do seu código.
	
 * */

public class NullCheckers {
	
	//defino minha classe Dummy
	public static ClasseDummy classe = new ClasseDummy();
	
	//criei um método para setar valores, lembrando que esse método precisa ser chamado para não causar nullpointer
	public static void setaValoresParaAClasse() {
		
		classe.setIdade(25);
		classe.setNome("Fulano");
		classe.setTaVivo(true); //eu acho
	}
	
	//criei um método que não vai chamar o setaValores logo de começo
	public static void vouDarNulo() {
		
		//perceba que aqui eu não estou fazendo validação nenhuma
		if(classe.getIdade() == 10) { 
			
			//logo, isso aqui nem vai aparecer na tela, mas sim, um NullPointerException
			System.out.println("Isso aqui vai dar nulo"); 
			
			//Exception in thread "main" java.lang.NullPointerException: 
			//Cannot invoke "java.lang.Integer.intValue()" because the return value of 
			//"pacoteManeiro.ClasseDummy.getIdade()" is null
		}
	}
	
	//criei um método que irá chamar o setaValores
	public static void naoVouDarNulo() {
		
		//dei set nos valores
		setaValoresParaAClasse();
		
		//é feita uma validação para ver se getIdade não está nulo e nem zero
		if(classe.getIdade() != null && classe.getIdade() != 0) {
			
			//inevitavelmente vai passar e printar em tela.
			System.out.println("Isso aqui vai funcionar");
		}
		
		
	}
	
	//criei mais um método que não irá chamar o setaValores, porém irá fazer uma validação se getIdade está nulo
	public static void vouPularValidacaoNula() {
		
		if(classe.getIdade() != null) {
			//não vai entrar no if e printar em tela.
			System.out.println("Isso aqui vai funcionar");
		}
		
		if(classe.getIdade() == null) {
			//vai entrar aqui e printar isto em tela
			System.out.println("getIdade está nulo e printou isto aqui em tela");
		}
	}

	public static void main(String[] args) {
		
		//Só descomentar e trocar de método, rodar e ver os resultados.
		
		vouDarNulo();
		//naoVouDarNulo();
		//vouPularValidacaoNula();

	}

}
