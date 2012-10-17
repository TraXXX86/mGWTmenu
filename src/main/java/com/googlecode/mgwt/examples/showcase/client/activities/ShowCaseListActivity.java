/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.examples.showcase.client.activities;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.animation.AnimationPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;

/**
 * @author Daniel Kurka
 * 
 */
public class ShowCaseListActivity extends MGWTAbstractActivity {

  private final ClientFactory clientFactory;

  public ShowCaseListActivity(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    ShowCaseListView view = clientFactory.getHomeView();

    view.setTitle("mgwt");
    view.setRightButtonText("about");

    view.getFirstHeader().setText("Showcase");

    view.setTopics(createTopicsList());

    addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(
        new CellSelectedHandler() {

          @Override
          public void onCellSelected(CellSelectedEvent event) {
            int index = event.getIndex();
            if (index == 0) {
              clientFactory.getPlaceController().goTo(new AnimationPlace());
              return;
            }
            if (index == 1) {
              clientFactory.getPlaceController().goTo(new UIPlace());

              return;
            }

          }
        }));

    addHandlerRegistration(view.getAboutButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        clientFactory.getPlaceController().goTo(new AboutPlace());

      }
    }));

    panel.setWidget(view);
  }

  private List<Topic> createTopicsList() {
    ArrayList<Topic> list = new ArrayList<Topic>();
    list.add(new Topic("Animations", 5));
    list.add(new Topic("UI", 5));

    return list;
  }

}
