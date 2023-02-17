package unused;

import java.io.BufferedReader;

import Client.ClientApp;
import common.Commands;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class clientListenerThread extends Thread{


	private boolean running;
	private ClientApp client;
	private String user;
	private String otherUser;
	private BufferedReader serverSideReader;

	public clientListenerThread(ClientApp clientApp, String user, String otherUser) {
		
		this.client = clientApp;
		this.user = user;
		this.otherUser = otherUser;
		this.running = true;
		this.serverSideReader = client.getServerSideReader();
	}



	public void run() {

		try {

			while (running) {

				if (serverSideReader.ready()) {

					String serverRequest = serverSideReader.readLine();

					System.out.println("Client heard " + serverRequest);

					if (serverRequest.equals(Commands.PROD)) {

						String sender = serverSideReader.readLine();
						String receiver = serverSideReader.readLine();

						Alert prodded = new Alert(AlertType.INFORMATION);
						prodded.setHeaderText("Congratulations " + receiver);
						prodded.setContentText(sender + " sent you a prod!");
						prodded.show();
					}
					else {

						/*
						 * I've noticed that in this context my buffer starts going out of sync and the serverrequest starts
						 * reading in the wrong things. I've written this code to try to clear it
						 * 
						 * Then run the prod command again
						 */
						while (serverSideReader.ready()){
							serverSideReader.reset();
							System.out.println("Clearing buffer");

						}

						client.prod(user, otherUser);
					}
				}
				
				Thread.sleep(2500);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Changes a condition such that the thread will stop running.
	 */
	public void cancelThread() {

		this.running = false;
	}
}
