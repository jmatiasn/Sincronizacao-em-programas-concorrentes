package BanheiroComMonitor;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.rowset.spi.SyncResolver;

public class BanheiroComSemaforo {

	private int MAX_PESSOAS;
	private final Semaphore semaforo;
	private ArrayList<PessoaThreadComSemaforo> pessoasNoBanheiro;

	public BanheiroComSemaforo(int MAX_PESSOAS) {
		// TODO Auto-generated constructor stub
		this.MAX_PESSOAS = MAX_PESSOAS;
		this.pessoasNoBanheiro = new ArrayList<PessoaThreadComSemaforo>();
		this.semaforo = new Semaphore(MAX_PESSOAS, true);
	}

	public int getMAX_PESSOAS() {
		return MAX_PESSOAS;
	}

	public void setMAX_PESSOAS(int mAX_PESSOAS) {
		MAX_PESSOAS = mAX_PESSOAS;
	}

	public ArrayList<PessoaThreadComSemaforo> getPessoasNoBanheiro() {
		return pessoasNoBanheiro;
	}

	public void setPessoasNoBanheiro(ArrayList<PessoaThreadComSemaforo> pessoasNoBanheiro) {
		this.pessoasNoBanheiro = pessoasNoBanheiro;
	}

	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void entrar(PessoaThreadComSemaforo pessoa) throws InterruptedException {
		semaforo.acquire();
		// Se for de sexo diferente das pessoas que estão no banheiro
		// não entra
		if (pessoasNoBanheiro.size() > 0 && pessoasNoBanheiro.get(0).isMulher() != pessoa.isMulher()) {
			String sexoNaoPodeEntrar = "Homens";
			if (pessoa.isMulher()) {
				sexoNaoPodeEntrar = "Mulheres";
			}
			System.out.println("---------" + sexoNaoPodeEntrar + " não podem entrar---------");
			System.out.println(pessoa.getNome() + " : não posso entrar...");
			synchronized (semaforo) {
				try {
					semaforo.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		System.out.println(pessoa.getNome() + " : vou entrar...");
		System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + (MAX_PESSOAS - pessoasNoBanheiro.size()));

		pessoasNoBanheiro.add(pessoa);

		System.out.println(pessoa.getNome() + " : consegui entrar!");
		System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + (MAX_PESSOAS - pessoasNoBanheiro.size()));
		semaforo.release();
	}

	public void sair(PessoaThreadComSemaforo pessoa) throws InterruptedException {
		semaforo.acquire();
		if (pessoasNoBanheiro.contains(pessoa)) {
			pessoasNoBanheiro.remove(pessoa);

			System.out.println(pessoa.getNome() + " : vou sair...");
			System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + (MAX_PESSOAS - pessoasNoBanheiro.size()));
		}
		if (pessoasNoBanheiro.size() == 0) {
			System.out.println("---------" + "Banheiro liberado ---------");
			synchronized (semaforo) {
				try {
					semaforo.notifyAll();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		semaforo.release();
	}

}
