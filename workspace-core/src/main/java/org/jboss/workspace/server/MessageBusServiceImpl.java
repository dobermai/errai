package org.jboss.workspace.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.jboss.workspace.client.rpc.MessageBusService;
import org.jboss.workspace.server.bus.Message;
import org.jboss.workspace.server.bus.MessageBus;
import org.jboss.workspace.server.bus.SimpleMessageBusProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MessageBusServiceImpl extends RemoteServiceServlet implements MessageBusService {
    private MessageBus bus;

    @Override
    public void init() throws ServletException {
        // just use the simple bus for now.  more integration options to come...
        bus = new SimpleMessageBusProvider().getBus();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000 * 10);

                    System.out.println("transmitting test message...");
                    bus.store("org.jboss.workspace.WorkspaceLayout", "[[\"CommandType\", \"Hello\"], [\"Name\", \"Mr. Server\"]]");
                }
                catch (InterruptedException e) {

                }
            }
        };

        t.start();
    }

    public void store(String subject, String message) {
        bus.store(subject, message);
    }

    public String[] nextMessage() {
        System.out.println("Polling...");
        Message m = bus.nextMessage(getId());
        if (m != null) {
            System.out.println("pushing message [subject:" + m.getSubject() + ";msg=" + m.getMessage() + "]");
            return new String[]{m.getSubject(), m.getMessage()};
        }
        else {
            return null;
        }
    }

    public void remoteSubscribe(String subject) {

        bus.remoteSubscribe(getId(), subject);
    }

    private String getId() {
        HttpServletRequest request = getThreadLocalRequest();
        HttpSession session = request.getSession();

        if (session.getAttribute("WSSessionID") == null) {
            session.setAttribute("WSSessionID", session.getId());
        }

        return (String) session.getAttribute("WSSessionID");
    }

    public String[] getSubjects() {
        return bus.getSubjects().toArray(new String[bus.getSubjects().size()]);
    }
}