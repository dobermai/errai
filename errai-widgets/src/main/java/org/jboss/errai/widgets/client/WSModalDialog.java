package org.jboss.errai.widgets.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.jboss.errai.common.client.framework.AcceptsCallback;
import org.jboss.errai.widgets.client.effects.Effects;
import org.jboss.errai.widgets.client.listeners.ClickCallbackListener;


public class WSModalDialog implements AcceptsCallback {
    Label message = new Label("Warning!");
    AcceptsCallback callbackTo;

    DockPanel dockPanel;

    Button okButton;
    ClickCallbackListener okListener;

    Button cancelButton;
    ClickCallbackListener cancelListener;

    SimplePanel drapePanel;
    WSWindowPanel window;

    public WSModalDialog() {
        this("Alert!");
    }

    public WSModalDialog(String title) {
        window = new WSWindowPanel(title);

        dockPanel = new DockPanel();
        dockPanel.setWidth("400px");

        dockPanel.add(new Image(GWT.getModuleBaseURL() + "/images/ui/icons/redflag.png"), DockPanel.WEST);
        dockPanel.add(message, DockPanel.CENTER);

        message.getElement().getStyle().setProperty("padding", "0px");

        HorizontalPanel buttonPanel = new HorizontalPanel();

        okButton = new Button("OK");
        okListener = new ClickCallbackListener(this, AcceptsCallback.MESSAGE_OK);
        okButton.addClickHandler(okListener);

        buttonPanel.add(okButton);

        cancelButton = new Button("Cancel");
        cancelListener = new ClickCallbackListener(this, AcceptsCallback.MESSAGE_CANCEL);
        cancelButton.addClickHandler(cancelListener);

        dockPanel.add(cancelButton, DockPanel.SOUTH);

        buttonPanel.add(cancelButton);

        dockPanel.add(buttonPanel, DockPanel.SOUTH);
        dockPanel.setCellHorizontalAlignment(buttonPanel, DockPanel.ALIGN_RIGHT);
        dockPanel.setCellHeight(buttonPanel, "45px");

        window.add(dockPanel);
    }


    public void callback(Object message, Object data) {
        if (callbackTo != null) callbackTo.callback(message, data);
        window.hide();
    }

    public void ask(String message, final AcceptsCallback callbackTo) {
        this.callbackTo = callbackTo;
        this.message.setText(message);
        window.addClosingHandler(new Window.ClosingHandler() {
            public void onWindowClosing(Window.ClosingEvent event) {
                if (callbackTo != null) callbackTo.callback("WindowClosed", null);
                RootPanel.get().remove(drapePanel);
            }
        });
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

    public void setTitle(String title) {
        this.window.setTitle(title);
    }

    public static SimplePanel createDrape() {
        SimplePanel drapePanel = new SimplePanel();

        Style drapeStyle = drapePanel.getElement().getStyle();

        drapeStyle.setProperty("position", "absolute");
        drapeStyle.setProperty("top", "0px");
        drapeStyle.setProperty("left", "0px");

        drapePanel.setWidth("100%");
        drapePanel.setHeight("100%");

        drapePanel.setStyleName("WSWindowPanel-drape");

        Effects.setOpacity(drapePanel.getElement(), 20);

        return drapePanel;
    }

    public void showModal() {
        RootPanel.get().add(drapePanel = createDrape());
        window.center();
        window.show();
    }

}