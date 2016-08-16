package team4141.eventmanager;


import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author RobotC
 *
 */
public class EventManager {
	private final EventManager self = this;
	private EventManagerCallBack callback;
	private long nextMessageID = 0;

	public EventManager(){
		this(null);		
	}
	public EventManager(EventManagerCallBack callback){
		sessions = new ArrayList<Session>();
		this.callback = callback;
		callback.setEventManager(this);
	}
	
	public EventManagerCallBack getCallback() {
		return callback;
	}
	public void start() throws Exception{
		int port = 5808;
		System.out.println("Initializing socket server at port "+port);
		Server server = new Server(port);
		WebSocketHandler wsHandler = new WebSocketHandler()
	    {
	        @Override
	        public void configure(WebSocketServletFactory factory)
	        {
	            factory.setCreator(new EventManagerWebSocketCreator(self));
	        }
	    };
		server.insertHandler(wsHandler);
		server.start();
//		server.join();  //blocking all until server is stopped
	}

	public void post(Notification notification){
		notification.setMessageId(nextMessageID++);
		if(notification.isConsole()){
			System.out.println(notification);
		}
		if(notification.isDisplay()||notification.isRecord()){
			try {
				for(Session session : sessions){
					if(session!=null && session.isOpen()){
						session.getRemote().sendString(notification.toJSON());
					}
				}
			} catch (IOException e) {
				System.err.println("unable to post: "+notification.toJSON());
			}
		}	
	}

	private ArrayList<Session> sessions;
	public boolean addSession(Session session) {
		return sessions.add(session);
		
	}
	public void removeSession(Session session) {
		if(session !=null && sessions!=null && sessions.contains(session)) sessions.remove(session);
	}	

}
