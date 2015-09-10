import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class TestTocatWebSocket {
	public static void main(String[] args) throws URISyntaxException {
		
		Scanner scanner1 = new Scanner(System.in);
		int roomId = scanner1.nextInt();
		Scanner scanner2 = new Scanner(System.in);
		String name = scanner2.nextLine();
		String url = "ws://localhost:8080/WebSocket/chat/"+roomId+"/"+name;
		WebSocketClient wc = new WebSocketClient(new URI(url), new Draft_17()) {
			@Override
			public void onOpen(ServerHandshake handshakedata) {
				System.out.println(handshakedata.getHttpStatusMessage());
			}

			@Override
			public void onMessage(String message) {
				System.out.println(message);
			}

			@Override
			public void onError(Exception ex) {

			}

			@Override
			public void onClose(int code, String reason, boolean remote) {

			}
		};

		wc.connect();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if(scanner.hasNext()){
				String message = scanner.nextLine();
				if (message.equals("q")) {
					wc.close();
					break;
				}
				wc.send(message);
			}
		}
	}
}
