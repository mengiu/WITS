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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedListener;


/**
 * FragmentChangedListener that is responsible to navigating the application to the right
 * page depending on the URI fragment.
 * 
 * @author Frederik Heremans
 */
public class NavigationFragmentChangeListener implements FragmentChangedListener {

  private static final long serialVersionUID = 1L;
  
  @Autowired
  protected NavigatorManager navigatorManager;

  public void fragmentChanged(FragmentChangedEvent source) {
    String fragment = source.getUriFragmentUtility().getFragment();
    
    if (StringUtils.isNotEmpty(fragment)) {
      UriFragment uriFragment = new UriFragment(fragment);
      
      // Find appropriate handler based on the first part of the URI
      Navigator navigationHandler = null;
      if (uriFragment.getUriParts() != null && uriFragment.getUriParts().size() > 0) {
        navigationHandler = navigatorManager.getNavigator(uriFragment.getUriParts().get(0));
      }
      
      if (navigationHandler == null) {
        navigationHandler = navigatorManager.getDefaultNavigator();
      }
      
      // Delegate navigation to handler
      navigationHandler.handleNavigation(uriFragment);
    
    } else if (WitsApp.get().getCurrentUriFragment() != null &&
        WitsApp.get().getCurrentUriFragment().getUriParts() != null &&
            WitsApp.get().getCurrentUriFragment().getUriParts().size() > 0) {
      
      Navigator navigationHandler = navigatorManager.getNavigator(WitsApp.get().getCurrentUriFragment().getUriParts().get(0));
      //if (navigationHandler instanceof ProcessModelNavigator) {
        //navigationHandler.handleNavigation(WitsApp.get().getCurrentUriFragment());
     // }
    }
    
  }
  
  
  public void setNavigatorManager(NavigatorManager navigatorManager) {
    this.navigatorManager = navigatorManager;
  }

}
