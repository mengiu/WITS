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

package org.front.end.wits.vaadin.identity;

import java.io.Serializable;

import org.activiti.engine.identity.User;
import org.persistence.layer.wits.dao.to.GenericRole;
import org.persistence.layer.wits.form.WitsUserSt;


/**
 * Interface describing the logged in user.
 * 
 * @author Frederik Heremans
 * @author Mennea Giuseppe
 */
public interface LoggedInUser extends Serializable {
  User   getUser();

  String getId();
  
  String getFirstName();
  
  String getLastName();
  
  String getFullName();
  
  String getPassword();
  
  GenericRole getGenericRole();
  
  boolean isResponsible();   // responsible

  boolean isSupervisor();

  boolean isOperator();

  boolean isGuest();
  
  boolean isAdmin();
  
  void initializeLoggedInUser();

  WitsUserSt getWitsUserSt();
  
  void setWitsUserSt(WitsUserSt witsUserSt);

  void setGenericRole(GenericRole genericRole);
}
