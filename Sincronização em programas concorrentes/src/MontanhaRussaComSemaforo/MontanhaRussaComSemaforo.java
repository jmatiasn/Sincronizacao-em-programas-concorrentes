package MontanhaRussaComSemaforo;

import java.util.concurrent.Semaphore;

public class MontanhaRussaComSemaforo extends Thread {
	private int id;
	private Semaphore entrada;
	private Semaphore saida;
	private int capacidade = 10;
	private static int cont; 
	private static int out;
	private static int[] car;
	
	
	public MontanhaRussaComSemaforo(int id, Semaphore entrada, Semaphore saida){
		this.entrada = entrada;
		this.saida = saida;
		this.car = new int[capacidade];
		this.cont = 0;
		this.out = 10;
		this.id = id;
	}
	
	private void print(){

		System.out.println("########################################");
		System.out.println("##### Capacidade do carro: " + capacidade);
		System.out.println("##### Contador de passageiros: " + cont);
		System.out.println("##### Carro ############################");
		System.out.print("### [ ");
		for(int i = 0; i < car.length; i++){
			System.out.print(car[i]+ " - ");
		}
		System.out.println(" ] ####");
		System.out.println("########################################");
	}
	
	private void board(){
		//while(cont < 10){
			try{
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println("passageiro #"+this.id+" entrando no carro");
				car[cont] = this.id;
				this.cont++;
				this.out--;
			}catch(Exception e){
				e.printStackTrace();
			}
		//}
	}

	private void unboard() {
		try {
			for(int i =0; i < cont; i++){
				car[i] = 0;
			}
			cont  =0;
			out =10;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void load(){
		System.out.println("permitida a entrada de passageiros");
		board();
		print();
		
	}
	private void unload(){
		System.out.println("permitida a saida de passageiros");
		unboard();
	}
	
	public void run(){
		
		try{
			entrada.acquire();
			
			if(cont < 10 ){
				System.out.println("pessoas entrando no carro");
				this.load();
			}
			entrada.release();
			saida.acquire();
			if(out == 0){	
				this.unload();
			}
			saida.release();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}

	}
	

}