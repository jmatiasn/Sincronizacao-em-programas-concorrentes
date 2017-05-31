package BanheiroComSemaforo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PessoaThreadComSemaforo extends Thread {
	
	private String nome = "";
	//Homem = false
	//Mulher = true
	private boolean ehMulher = false;
	private int tempoMedioNoBanheiro = 0; //em segundos
	private BanheiroComSemaforo banheiro;
	
	public PessoaThreadComSemaforo(String nome, boolean ehMulher, BanheiroComSemaforo banheiro) {
		// TODO Auto-generated constructor stub
		this.nome = nome;
		this.ehMulher = ehMulher;
		this.banheiro = banheiro;
		
		//Gera número aleatório entre 1 e 10
		Random rand = new Random();
		int  novoTempo = rand.nextInt(10) + 1;
		this.tempoMedioNoBanheiro = novoTempo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isMulher() {
		return ehMulher;
	}

	public void setEhMulher(boolean ehMulher) {
		this.ehMulher = ehMulher;
	}

	public int getTempoMedioNoBanheiro() {
		return tempoMedioNoBanheiro;
	}

	public void setTempoMedioNoBanheiro(int tempoMedioNoBanheiro) {
		this.tempoMedioNoBanheiro = tempoMedioNoBanheiro;
	}

	public BanheiroComSemaforo getBanheiro() {
		return banheiro;
	}

	public void setBanheiro(BanheiroComSemaforo banheiro) {
		this.banheiro = banheiro;
	}
	
	public void run() {

		try {
			banheiro.entrar(this);

			try {
				Thread.sleep(tempoMedioNoBanheiro * 1000);
			} finally {
				banheiro.sair(this);

			}
		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}


}
