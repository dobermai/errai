package org.jboss.errai.client.widgets;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.jboss.errai.client.framework.AcceptsCallback;
import org.jboss.errai.client.listeners.ClickCallbackListener;
import org.jboss.errai.client.rpc.CommandMessage;
import org.jboss.errai.client.rpc.MessageBusClient;
import org.jboss.errai.client.rpc.protocols.LayoutCommands;
import org.jboss.errai.client.rpc.protocols.LayoutParts;

import java.util.Map;
import java.util.Set;


public class WSTabSelectorDialog extends WSModalDialog {
    Label message = new Label("Select Window");
    AcceptsCallback callbackTo;

    HorizontalPanel hPanel;
    VerticalPanel vPanel;
    HorizontalPanel buttonPanel;

    Button okButton;
    ClickCallbackListener okListener;

    Button cancelButton;
    ClickCallbackListener cancelListener;

    final WSWindowPanel window = new WSWindowPanel("Select Window");

    String id;

    public WSTabSelectorDialog(Set<String> components) {
        hPanel = new HorizontalPanel();
        hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        vPanel = new VerticalPanel();
        hPanel.add(vPanel);
        vPanel.add(message);

        for (String s : components) {

            final Map<String, Object> instanceProperties = MessageBusClient.decodeMap(s);

            Button b = new Button("<span><img src='" + instanceProperties.get(LayoutParts.IconURI.name())
                    + "' align='left'/>" + instanceProperties.get(LayoutParts.Name.name()) + "</span>"
                    , new ClickHandler() {

                        public void onClick(ClickEvent event) {
                            MessageBusClient.store((String) instanceProperties.get(LayoutParts.Subject.name()),
                                    CommandMessage.create(LayoutCommands.ActivateTool));


                            window.hide();
                        }
                    });

            b.getElement().getStyle().setProperty("background", "transparent");
            b.getElement().getStyle().setProperty("textAlign", "left");
            b.setWidth("100%");

            vPanel.add(b);
        }



        HorizontalPanel innerContainer = new HorizontalPanel();
        vPanel.add(innerContainer);
        innerContainer.setWidth("100%");
        innerContainer.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

        buttonPanel = new

                HorizontalPanel();

        okButton = new

                Button("OK");

        okListener = new

                ClickCallbackListener(this, AcceptsCallback.MESSAGE_OK);

        okButton.addClickHandler(okListener);
        buttonPanel.add(okButton);

        cancelButton = new

                Button("Cancel");

        cancelListener = new

                ClickCallbackListener(this, AcceptsCallback.MESSAGE_CANCEL);

        cancelButton.addClickHandler(cancelListener);
        buttonPanel.add(cancelButton);

        innerContainer.add(buttonPanel);

        Style s = message.getElement().getStyle();
        s.setProperty("padding", "8px");
        s.setProperty("verticalAlign", "top");

        window.add(hPanel);


    }


    public void callback(Object message, Object data) {
        callbackTo.callback(message, data
        );
        window.hide();
    }

    public void ask(String message, AcceptsCallback callbackTo) {
        this.callbackTo = callbackTo;
        this.message.setText(message);
    }

    public Button getOkButton() {
        return okButton;
    }

    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }

    public void showModal() {
        window.show();
        window.center();
    }

}