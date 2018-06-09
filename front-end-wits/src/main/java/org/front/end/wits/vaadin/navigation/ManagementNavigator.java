/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.front.end.wits.vaadin.navigation;

import org.front.end.wits.vaadin.WitsApp;


/**
 * @author Joram Barrez
 */
public abstract class ManagementNavigator implements Navigator {

  public void handleNavigation(UriFragment uriFragment) {
    if (!WitsApp.get().getLoggedInUser().isAdmin()) {
      // If not an admin, just show DefaultPageWITS and act like nothing happened
      //WitsApp.get().getViewManager().showInboxPage();
      WitsApp.get().getViewManager().showDefaultPageWITS();
    } else {
      handleManagementNavigation(uriFragment);
    }
  }
  
  public abstract void handleManagementNavigation(UriFragment uriFragment);

}
