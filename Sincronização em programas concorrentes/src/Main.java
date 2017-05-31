import java.util.Scanner;

import BanheiroComLockECondition.BanheiroComLockECondition;
import BanheiroComLockECondition.PessoaThread;
import BanheiroComMonitor.BanheiroComSemaforo;
import BanheiroComMonitor.PessoaThreadComSemaforo;

//Referências: 
//https://www.mkyong.com/java/java-thread-mutex-and-semaphore-example/
//https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html
//https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exibirMenuInicial();

		// Recebe dado do teclado
		Scanner scanner = new Scanner(System.in);
		String opcaoEscolhida = scanner.nextLine();

		verificarOpcaoEscolhida(opcaoEscolhida);
	}

	static void exibirMenuInicial() {
		System.out.println("Bem-vindo!");
		System.out.println("---Menu---");
		System.out.println("1. Uma Montanha-Russa");
		System.out.println("2. O Banheiro Unissex com Lock e Condition");
		System.out.println("3. O Banheiro Unissex com Semáforo");
		System.out.println("---------");
		System.out.println("Digite o número da opção desejada");
	}

	static void verificarOpcaoEscolhida(String opcaoEscolhida) {

		switch (opcaoEscolhida) {
		case "1":
			System.out.println("Desculpa! Uma Montanha-Russa ainda não foi implementada.");
			break;
		case "2":
			System.out.println("Banheiro Unissex com Lock e Condition selecionado");
			abrirBanheiroUnissex();
			break;
		case "3":
			System.out.println("Banheiro Unissex com Semáforo selecionado");
			abrirBanheiroUnissexSemaforo();
			break;
		default:
			System.out.println("Opção inválida! Tente novamente.");
			break;
		}
	}

	static void abrirBanheiroUnissex() {
		final int numVagas = 10;
		final BanheiroComLockECondition banheiro = new BanheiroComLockECondition(numVagas);

		System.out.println("Número máximo de pessoas permitido no banheiro : " + 
		banheiro.getMAX_PESSOAS());

		PessoaThread p1 = new PessoaThread("Ana", true, banheiro);
		p1.start();

		PessoaThread p2 = new PessoaThread("João", false, banheiro);
		p2.start();

		PessoaThread p3 = new PessoaThread("Fabiana", true, banheiro);
		p3.start();

		PessoaThread p4 = new PessoaThread("Fábio", false, banheiro);
		p4.start();

		PessoaThread p5 = new PessoaThread("Alice", true, banheiro);
		p5.start();

		PessoaThread p6 = new PessoaThread("Wesley", false, banheiro);
		p6.start();
		
		PessoaThread p7 = new PessoaThread("Marcos", false, banheiro);
		p7.start();
		
		PessoaThread p8 = new PessoaThread("Jorge", false, banheiro);
		p8.start();
		
		PessoaThread p9 = new PessoaThread("Larissa", true, banheiro);
		p9.start();
		
		PessoaThread p10 = new PessoaThread("Francleide", true, banheiro);
		p10.start();
		
		PessoaThread p11 = new PessoaThread("Aurélio", false, banheiro);
		p11.start();
	}
	
	static void abrirBanheiroUnissexSemaforo() {
		final BanheiroComSemaforo banheiro = new BanheiroComSemaforo(10);

		System.out.println("Número máximo de pessoas permitido no banheiro : " + 
		banheiro.getMAX_PESSOAS());

		PessoaThreadComSemaforo p1 = new PessoaThreadComSemaforo("Ana", true, banheiro);
		p1.start();

		PessoaThreadComSemaforo p2 = new PessoaThreadComSemaforo("João", false, banheiro);
		p2.start();

		PessoaThreadComSemaforo p3 = new PessoaThreadComSemaforo("Fabiana", true, banheiro);
		p3.start();

		PessoaThreadComSemaforo p4 = new PessoaThreadComSemaforo("Fábio", false, banheiro);
		p4.start();

		PessoaThreadComSemaforo p5 = new PessoaThreadComSemaforo("Alice", true, banheiro);
		p5.start();

		PessoaThreadComSemaforo p6 = new PessoaThreadComSemaforo("Wesley", false, banheiro);
		p6.start();
		
		PessoaThreadComSemaforo p7 = new PessoaThreadComSemaforo("Marcos", false, banheiro);
		p7.start();
		
		PessoaThreadComSemaforo p8 = new PessoaThreadComSemaforo("Jorge", false, banheiro);
		p8.start();
		
		PessoaThreadComSemaforo p9 = new PessoaThreadComSemaforo("Larissa", true, banheiro);
		p9.start();
		
		PessoaThreadComSemaforo p10 = new PessoaThreadComSemaforo("Francleide", true, banheiro);
		p10.start();
		
		PessoaThreadComSemaforo p11 = new PessoaThreadComSemaforo("Aurélio", false, banheiro);
		p11.start();
	}

}
