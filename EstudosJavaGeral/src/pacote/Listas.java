package pacote;

public class Listas {
	
	//001: Ache o par
	
	public static String[] input = {"(", ")", "{", "}", "[", "]"};
	public static String[] inputErrado = {"(", "{", "}", "]"};
	
	//dica .equals() e .isEmpty()
	
	public static void main(String[] args) {
		
		int opcao = 0;
		
		switch (opcao) {
        case 0:
        	checaPar(inputErrado);
            break;
        case 1:
            break;
        case 2:
            break;
        default:
            System.out.println("Opção inválida");
    }
		
    }
	
	public static void checaPar(String[] input) {
		Boolean semPar = false;
		
		for(int i = 0; i < input.length; i++) {
			
			switch (input[i]) {
			case "(":
				semPar = encontraPar(input, ")");
				break;
			case "{":
				semPar = encontraPar(input, "}");
				break;
			case "[":
				semPar = encontraPar(input, "]");
				break;
			case ")":
				semPar = encontraPar(input, "(");
				break;
			case "}":
				semPar = encontraPar(input, "{");
				break;
			case "]":
				semPar = encontraPar(input, "[");
				break;
			}
		}
		
		if (semPar) {
			System.out.println("Um item não tem seu par");
			return;
		}
		System.out.println("Todos os itens possuem par");
	}
	
	public static boolean encontraPar(String[] input, String item) {
		for(String s : input) {
			if (s.equals(item)) {
				return false;
			}
		}
		return true;
	}
	
}

/*
			
if (input[i].equals("(")) {
	semPar = encontraPar(input, ")");
}
if (input[i].equals("{")) {
	semPar = encontraPar(input, "}");
}
if (input[i].equals("[")) {
	semPar = encontraPar(input, "]");
}
if (input[i].equals(")")) {
	semPar = encontraPar(input, "(");
}
if (input[i].equals("}")) {
	semPar = encontraPar(input, "{");
}
if (input[i].equals("]")) {
	semPar = encontraPar(input, "[");
}
 * */
