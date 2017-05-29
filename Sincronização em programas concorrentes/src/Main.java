import java.util.Scanner;

import BanheiroUnissex.BanheiroUnissex;
import BanheiroUnissex.PessoaThread;

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
		System.out.println("2. O Banheiro Unissex");
		System.out.println("---------");
		System.out.println("Digite o número da opção desejada");
	}

	static void verificarOpcaoEscolhida(String opcaoEscolhida) {

		switch (opcaoEscolhida) {
		case "1":
			System.out.println("Desculpa! Uma Montanha-Russa ainda não foi implementada.");
			break;
		case "2":
			System.out.println("Banheiro Unissex selecionado");
			abrirBanheiroUnissex();
			break;
		default:
			System.out.println("Opção inválida! Tente novamente.");
			break;
		}
	}

	static void abrirBanheiroUnissex() {
		final BanheiroUnissex banheiro = new BanheiroUnissex(10);

		System.out.println("Número máximo de pessoas permitido no banheiro : " + 
		banheiro.getSemaforo().availablePermits());

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
	}

}
