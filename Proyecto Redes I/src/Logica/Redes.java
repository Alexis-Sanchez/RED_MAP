package Logica;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Ventana.VentanaPrincipal;

public class Redes implements Runnable 
{

	public static void main(String[] args) throws IOException {

		 //reconociendoHost();
		generarHilo();
		// generarInterface();
	}

	int puerto = 0;
	int fin;
	public static String p="";

	public Redes(int port, int fin) {

		puerto = port;
		this.fin = fin;
	}

	/**
	 * Metodo encargado de generar las interfaces de red disponibles.
	 * @return cadena
	 * @throws UnknownHostException
	 */
	public static String generarInterface() throws UnknownHostException 
	{
		String cadena = "";
		InetAddress in = InetAddress.getLocalHost();
		InetAddress[] all = InetAddress.getAllByName(in.getHostName());
		for (int i = 0; i < all.length; i++) {			
			cadena += "  Direccion = " + all[i] + "\n";
			
			//System.out.println("  Direccion = " + all[i]);
		}
		System.out.println(cadena);
		return cadena;
	}

	/**
	 * Metodo encargado de generar los host de la red.
	 * @return cadena
	 * @throws UnknownHostException
	 */
	public static String reconociendoHost() throws IOException 
	{

		String cadena = "";

		InetAddress inAdd = InetAddress.getLocalHost();
		System.out.println(inAdd);
		for (int i = 1; i <= 254; i++) {

			inAdd = InetAddress.getByName("192.168.1." + i);
			if (inAdd.isReachable(1000)) {
				
				cadena += "IP: " + inAdd.getHostAddress() + " HOST: "
						+ inAdd.getHostName() + "\n";			
			}
		}
		System.out.println(cadena);
		return cadena;

	}

	/**
	 * Metodo encargado de generar y mostrar los puertos abiertos de la red.
	 * @return cadena
	 * @throws UnknownHostException
	 */
	public static void listarPuertos(int puerto, int fin) 
	{
		Socket dame;
		String ip = "localhost";
		String cadena = "";

		for (int port = puerto; port <= fin; port++) 
		{
			try {
				dame = new Socket(ip, port);

				cadena += "Puerto " + port + " Abierto" + "\n";
				//System.out.println("Puerto " + port + " Abierto");
				p=cadena;
				System.out.print(p);
				dame.close();

			} catch (Exception ex) {
			}

		}
		// return cadena;

	}

	/**
	 * Metodo encargado de generar los hilos necesarios para 
	 * agilizar el proceso de busqueda.
	 */
		
	public static void generarHilo()
	{
		
		ExecutorService executor = Executors.newCachedThreadPool();

		int ini = 1, fin = 100;
		
		while (fin <= 65500) 
		{
			executor.execute(new Redes(ini, fin));
			fin += 100;
			ini += 100;
		}
		executor.execute(new Redes(65501, 65535));
		
		

	}

	@Override
	public void run() 
	{
		listarPuertos(puerto, fin);
	}

}
