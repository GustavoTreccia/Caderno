package pacoteManeiro;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
	Esta é a sessão de TimeoutHandler
	
	Este tópico é um pouco mais complexo, sendo necessário que você esteja mais por dentro
	da parte WEB da coisa, como assuntos de async e await.
	
	Mas o que seriam os TimeoutHandlers?
	
	No exemplo do dia a dia, imagine que você foi a um restaurante, fez o pedido (requisição), e o pedido
	está levando eras para chegar, você fala com o garçom e nada, você então decide se levantar e ir embora.
	
	Isto é o que Timeout Handlers fazem, são monitores de quanto tempo uma ação está levando 
	e executar ações específicas para quando esse tempo ultrapassar o limite definido.
	
	Por que ter Timeout Handlers? Você garante três importantes fatores:
	
	Previsibilidade: Garante que sempre haverá uma resposta, mesmo que um erro;
	Recuperação: Garante tentativas automáticas pós timeout;
	Performance [IMPORTANTE]: Garante liberação de recursos que estavam presos em operações lentas
	
	Como um bom back-end, você precisa sempre ter um plano B, ainda mais pensando em  aplicações WEB 
	onde precisam ser dinâmicas e rápidas.
	
	Neste código a seguir utilizaremos o ExecutorService do Java para analisar duas situações:
	1- A operação é concluída no tempo limite
	2- A operação é cancelada por passar do tempo limite

 * */

//Defino uma classe genérica
public class TimeoutHandler<T> {
	
	//crio meu executor
	private final ExecutorService executor = Executors.newCachedThreadPool();
	
	//crio um método execute para executar uma ação
	//Future<T> em curtas palavras é uma interface que representa o resultado de uma ação assíncrona
	public Future<T> execute(Callable<T> task, long timeout, TimeUnit unit) {
        return executor.submit(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
	
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		
		//crio meu TimeoutHandler
		TimeoutHandler<String> handler = new TimeoutHandler<>();
		
		// Simulo uma operação demorada
		Callable<String> longTask = () -> {
            Thread.sleep(2000);
            return "Operação concluída";
        };
        
        //Preparo meu handler
        Future<String> future = handler.execute(longTask, 1, TimeUnit.SECONDS);
        
        try {
        	//Faço uma ação async para pegar o resultado da minha operação demorada
        	//Inevitavelmente vai dar time-out devido ao tempo definido
        	
        	String result = future.get(1, TimeUnit.SECONDS);
        	System.out.println(result);
        } catch (TimeoutException e) {
        	System.out.println("Operação excedeu o tempo limite");
        	future.cancel(true);
        }
        
        /*Como posso testar se a operação é concluída?
        
        Altere Thread.sleep(tempo) para um número abaixo do primeiro parâmetro de future.get
        E aumente o primeiro parâmetro de future.get(tempo, unidade)
        
        Exemplo:
        Thread.sleep(500);
      	&
      	String result = future.get(700, TimeUnit.SECONDS);
        
        Você irá receber a mensagem de operação concluída
        
		*/
	}

}
