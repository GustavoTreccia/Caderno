package fundamentosBasic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collections {
	
	public static void main(String[] args) {
		
		List<String> nomes = new ArrayList<>();
		Set<String> carros = new HashSet<>();
		Map<Long, String> funcionarios = new HashMap<>();
		
		nomes.add("Ana");
		nomes.add("Carlos");
		nomes.add("Johnes");
		
		carros.add("Volkswagen");
		carros.add("Chevrolet");
		carros.add("Chevrolet");
		carros.add("Nissan");
		
		funcionarios.put(100L, "Ana");
		funcionarios.put(200L, "Carlos");
		funcionarios.put(300L, "Johnes");
		
		System.out.println(nomes);
		System.out.println(carros);
		System.out.println(funcionarios);
		System.out.println(funcionarios.keySet());
	
	}
	
	/*public static String[] input = {"(", ")", "{", "}", "[", "]"};
	public static String[] inputErrado = {"(", "{", "}", "]"};
	public static String[] inputPalavrasAnagramas = {"eat","tea","tan","ate","nat","bat"};
	
	//dica .equals() e .isEmpty()
	
	public static void main(String[] args) {
		
		int opcao = 0;
		
		switch (opcao) {
        case 0:
        	//001 ache o par
        	checaPar(inputErrado);
            break;
        case 1:
        	//002 Hash anagramas
        	collectioners();
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
	
	public static void collectioners() {
		
	}
	
	public static boolean encontraPar(String[] input, String item) {
		for(String s : input) {
			if (s.equals(item)) {
				return false;
			}
		}
		return true;
	}*/
	
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
