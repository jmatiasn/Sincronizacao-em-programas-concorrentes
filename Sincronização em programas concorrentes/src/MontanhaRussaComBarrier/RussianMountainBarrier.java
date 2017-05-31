package MontanhaRussaComBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RussianMountainBarrier extends Thread{
	private CyclicBarrier barreira;
	private int id;
	
	
	public RussianMountainBarrier(int id, CyclicBarrier barrier){
		this.id = id;
		this.barreira = barrier;
	}
	
	//os passageiros devem embarcar no carro chamando este método
	private void board(){
		System.out.println("passageiro #" + this.id + " entrando");
	}

	private void unboard() {
		System.out.println("passageiro #"+this.id +" saindo");
	}
	
	private void load(){
		System.out.println("permitida a entrada de passageiros");
		board();
		
		
	}
	public void unload(){
		System.out.println("permitida a saida de passageiros");
		unboard();
	}
	
	private void print(){
		System.out.println("########################################");
		System.out.println("##### Capacidade do carro: " + 10);
		System.out.println("##### Contador de passageiros: " + barreira.getNumberWaiting());
		System.out.println("########################################");
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep((long) (Math.random() * 10000));
			load();
			barreira.await();
			print();
			unload();
		}catch(InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}