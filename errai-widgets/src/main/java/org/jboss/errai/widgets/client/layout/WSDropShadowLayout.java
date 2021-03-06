/*
 * Copyright 2010 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.widgets.client.layout;

import com.google.gwt.user.client.ui.*;

public class WSDropShadowLayout extends Composite {

  public WSDropShadowLayout(Widget wrappedWidget) {
    DockPanel dockPanel = new DockPanel();

    /**
     * Bad hack to avoid rendering shadow when IE is present.
     */
    if (getUserAgent().indexOf("msie") == -1) {
      HorizontalPanel top = new HorizontalPanel();
      top.setWidth("100%");

      dockPanel.add(top, DockPanel.NORTH);
      dockPanel.setCellWidth(top, "100%");

      SimplePanel topLeftCorner = new SimplePanel();
      topLeftCorner.setStyleName("WSDropShadow-topLeftCorner");
      top.add(topLeftCorner);

      SimplePanel topLeft = new SimplePanel();
      topLeft.setStyleName("WSDropShadow-topLeft");
      top.add(topLeft);

      SimplePanel topMiddle = new SimplePanel();
      topMiddle.setStyleName("WSDropShadow-topMiddle");
      top.add(topMiddle);
      top.setCellWidth(topMiddle, "100%");

      SimplePanel topRight = new SimplePanel();
      topRight.setStyleName("WSDropShadow-topRight");
      top.add(topRight);

      SimplePanel topRightCorner = new SimplePanel();
      topRightCorner.setStyleName("WSDropShadow-topRightCorner");
      top.add(topRightCorner);

      HorizontalPanel bottom = new HorizontalPanel();
      bottom.setWidth("100%");
      dockPanel.add(bottom, DockPanel.SOUTH);
      dockPanel.setCellWidth(bottom, "100%");

      SimplePanel bottomLeftCorner = new SimplePanel();
      bottomLeftCorner.setStyleName("WSDropShadow-bottomLeftCorner");
      bottom.add(bottomLeftCorner);

      SimplePanel bottomLeft = new SimplePanel();
      bottomLeft.setStyleName("WSDropShadow-bottomLeft");
      bottom.add(bottomLeft);

      SimplePanel bottomMiddle = new SimplePanel();
      bottomMiddle.setStyleName("WSDropShadow-bottomMiddle");
      bottom.add(bottomMiddle);
      bottom.setCellWidth(bottomMiddle, "100%");

      SimplePanel bottomRight = new SimplePanel();
      bottomRight.setStyleName("WSDropShadow-bottomRight");
      bottom.add(bottomRight);

      SimplePanel bottomRightCorner = new SimplePanel();
      bottomRightCorner.setStyleName("WSDropShadow-bottomRightCorner");
      bottom.add(bottomRightCorner);


      VerticalPanel left = new VerticalPanel();
      left.setHeight("100%");
      dockPanel.add(left, DockPanel.WEST);
      dockPanel.setCellHeight(left, "100%");

      SimplePanel leftTop = new SimplePanel();
      leftTop.setStyleName("WSDropShadow-leftTop");
      left.add(leftTop);

      SimplePanel leftMiddle = new SimplePanel();
      leftMiddle.setHeight("100%");
      leftMiddle.setStyleName("WSDropShadow-leftMiddle");
      left.add(leftMiddle);
      left.setCellHeight(leftMiddle, "100%");

      SimplePanel leftBottom = new SimplePanel();
      leftBottom.setStyleName("WSDropShadow-leftBottom");
      left.add(leftBottom);

      VerticalPanel right = new VerticalPanel();
      right.setHeight("100%");
      dockPanel.add(right, DockPanel.EAST);
      dockPanel.setCellHeight(right, "100%");

      SimplePanel rightTop = new SimplePanel();
      rightTop.setStyleName("WSDropShadow-rightTop");
      right.add(rightTop);

      SimplePanel rightMiddle = new SimplePanel();
      rightMiddle.setHeight("100%");
      rightMiddle.setStyleName("WSDropShadow-rightMiddle");
      right.add(rightMiddle);
      right.setCellHeight(rightMiddle, "100%");

      SimplePanel rightBottom = new SimplePanel();
      rightBottom.setStyleName("WSDropShadow-rightBottom");
      right.add(rightBottom);

    }
    dockPanel.add(wrappedWidget, DockPanel.CENTER);
    dockPanel.setCellWidth(wrappedWidget, "100%");
    dockPanel.setCellHeight(wrappedWidget, "100%");

    initWidget(dockPanel);
  }


  public static native String getUserAgent() /*-{
    return navigator.userAgent.toLowerCase();
  }-*/;
}
