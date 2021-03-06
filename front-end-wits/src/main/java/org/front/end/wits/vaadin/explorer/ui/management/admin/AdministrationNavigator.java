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
package org.front.end.wits.vaadin.explorer.ui.management.admin;

import org.front.end.wits.vaadin.WitsApp;
import org.front.end.wits.vaadin.navigation.ManagementNavigator;
import org.front.end.wits.vaadin.navigation.UriFragment;

/**
 * @author Tijs Rademakers
 */
public class AdministrationNavigator extends ManagementNavigator {

 public static final String MANAGEMENT_URI_PART = "admin_management";
  
  public String getTrigger() {
    return MANAGEMENT_URI_PART;
  }
  
  public void handleManagementNavigation(UriFragment uriFragment) {
    String managementId = uriFragment.getUriPart(1);
    
    if(managementId != null) {
      WitsApp.get().getViewManager().showAdministrationPage(managementId);
    } else {
      WitsApp.get().getViewManager().showAdministrationPage();
    }
  }


}
