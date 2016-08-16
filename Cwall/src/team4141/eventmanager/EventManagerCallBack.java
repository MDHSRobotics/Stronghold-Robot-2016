package team4141.eventmanager;

import org.eclipse.jetty.websocket.api.Session;

public interface EventManagerCallBack {
	void onConnect(Session session);
	void onClose(Session session, int closeCode, String closeReason);
	void onText(Session session, String message);
	void onBinary(Session session, byte[] buffer, int offset, int length);
	void onError(Session session,Throwable err);
	void setEventManager(EventManager eventManager);
	EventManager getEventManager();
}
