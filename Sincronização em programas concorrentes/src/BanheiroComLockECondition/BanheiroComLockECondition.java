package BanheiroComLockECondition;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BanheiroComLockECondition {

	private int MAX_PESSOAS;
	private ArrayList<PessoaThread> pessoasNoBanheiro;
	
	final Lock lock = new ReentrantLock();
	final Condition naoPodeEntrar  = lock.newCondition();

	public BanheiroComLockECondition(int MAX_PESSOAS) {
		// TODO Auto-generated constructor stub
		this.MAX_PESSOAS = MAX_PESSOAS;
		this.pessoasNoBanheiro = new ArrayList<PessoaThread>();
	}

	public int getMAX_PESSOAS() {
		return MAX_PESSOAS;
	}

	public void setMAX_PESSOAS(int mAX_PESSOAS) {
		MAX_PESSOAS = mAX_PESSOAS;
	}

	public ArrayList<PessoaThread> getPessoasNoBanheiro() {
		return pessoasNoBanheiro;
	}

	public void setPessoasNoBanheiro(ArrayList<PessoaThread> pessoasNoBanheiro) {
		this.pessoasNoBanheiro = pessoasNoBanheiro;
	}

	public void entrar(PessoaThread pessoa) throws InterruptedException {
		lock.lock();
		try {
			//Se for de sexo diferente das pessoas que estão no banheiro
			//não entra
			if (pessoasNoBanheiro.size() > 0 && 
					pessoasNoBanheiro.get(0).isMulher() != pessoa.isMulher()) {
				String sexoNaoPodeEntrar = "Homens";
				if (pessoa.isMulher()) {
					sexoNaoPodeEntrar = "Mulheres";
				}
				System.out.println("---------" + sexoNaoPodeEntrar + " não podem entrar---------");
				System.out.println(pessoa.getNome() + " : não posso entrar...");
				naoPodeEntrar.await();
			}
			System.out.println(pessoa.getNome() + " : vou entrar...");
			System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + 
								(MAX_PESSOAS - pessoasNoBanheiro.size()));

			pessoasNoBanheiro.add(pessoa);

			System.out.println(pessoa.getNome() + " : consegui entrar!");
			System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + 
					(MAX_PESSOAS - pessoasNoBanheiro.size()));
		} finally {
			lock.unlock();
		}
	}

	public void sair(PessoaThread pessoa) {
		lock.lock();
		try {
			if (pessoasNoBanheiro.contains(pessoa)) {
				pessoasNoBanheiro.remove(pessoa);
				
				System.out.println(pessoa.getNome() + " : vou sair...");
				System.out.println(pessoa.getNome() + " : vagas disponíveis no banheiro: " + 
						(MAX_PESSOAS - pessoasNoBanheiro.size()));
			}
			if (pessoasNoBanheiro.size() == 0) {
				System.out.println("---------" + "Banheiro liberado ---------");
				naoPodeEntrar.signalAll();
			}
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}

}
