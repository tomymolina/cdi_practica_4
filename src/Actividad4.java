import java.util.Vector;

/**
 * Clase con todos los métodos estáticos que se encarga de iniciar y gestionar el juego
 * @author marmol
 *
 */
public class Actividad4 {
	private static Vector<Ping> jugadores;
	private static Vector<Thread> threads;
	private static int indiceJugadorActual = 0;
	private static Pelota pelota;
	public static void pasarSiguienteJugador(){
		if(jugadores.size() == 0) return;
		indiceJugadorActual=(indiceJugadorActual+1)%jugadores.size();
		jugadores.get(indiceJugadorActual).recibirPelota(pelota);
	}
	public static void main(String[] args){
		pelota = new Pelota();
		int nJugadores = 2;
		jugadores = new Vector<Ping>();
		threads = new Vector<Thread>();
		
		//Crear objetos e hilos
		for(int i = 0; i < nJugadores; i++){
			jugadores.add(new Ping(i));
			threads.add(new Thread(jugadores.lastElement()));
		}
		
		//Empezar hilos
		for(int i = 0; i < nJugadores; i++){
			threads.get(i).start();
		}
		
		pasarSiguienteJugador();
		
		//Join
		for(int i = 0; i < nJugadores; i++){
			try {
				threads.get(i).join();
			} catch(InterruptedException e){}
		}
		
		System.out.println("Acabó");
	}
}
