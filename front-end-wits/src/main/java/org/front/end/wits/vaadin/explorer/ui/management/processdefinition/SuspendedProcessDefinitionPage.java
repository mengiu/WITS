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
package org.front.end.wits.vaadin.explorer.ui.management.processdefinition;

import org.front.end.wits.vaadin.WitsApp;
import org.front.end.wits.vaadin.data.LazyLoadingContainer;
import org.front.end.wits.vaadin.data.LazyLoadingQuery;
import org.front.end.wits.vaadin.navigation.ActiveProcessDefinitionNavigator;
import org.front.end.wits.vaadin.navigation.SuspendedProcessDefinitionNavigator;
import org.front.end.wits.vaadin.navigation.UriFragment;
import org.front.end.wits.vaadin.ui.management.ManagementPage;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Table;

/**
 * @author Joram Barrez
 */
public class SuspendedProcessDefinitionPage extends ManagementPage {
  
  private static final long serialVersionUID = 1L;
  protected String processDefinitionId;
  protected Table processDefinitionTable;
  protected LazyLoadingQuery processDefinitionListQuery;
  protected LazyLoadingContainer processDefinitionListContainer;
  
  public SuspendedProcessDefinitionPage() {
    WitsApp.get().setCurrentUriFragment(
            new UriFragment(SuspendedProcessDefinitionNavigator.SUSPENDED_PROC_DEF_URI_PART));
  }
  
  public SuspendedProcessDefinitionPage(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  @Override
  protected void initUi() {
    super.initUi();
    
    if (processDefinitionId == null) {
      selectElementFirstTable(0);
    } else {
      selectElementFirstTable(processDefinitionListContainer.getIndexForObjectId(processDefinitionId));
    }
  }

  protected Table createFirstList() {
    processDefinitionTable = new Table();

    processDefinitionListQuery = new SuspendedProcessDefinitionListQuery();
    processDefinitionListContainer = new LazyLoadingContainer(processDefinitionListQuery);
    processDefinitionTable.setContainerDataSource(processDefinitionListContainer);
    
    // Column headers
    processDefinitionTable.addContainerProperty("name", String.class, null);
    processDefinitionTable.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
            
    // Listener to change right panel when clicked on a user
    processDefinitionTable.addListener(new Property.ValueChangeListener() {
      
      private static final long serialVersionUID = 1L;
      
      public void valueChange(ValueChangeEvent event) {
        Item item = processDefinitionTable.getItem(event.getProperty().getValue()); // the value of the property is the itemId of the table entry
        if (item != null) {
          String processDefinitionId = (String) item.getItemProperty("id").getValue();
          setDetailComponent(new SuspendedProcessDefinitionDetailPanel(processDefinitionId, SuspendedProcessDefinitionPage.this));
          
          // Update URL
          WitsApp.get().setCurrentUriFragment(
                  new UriFragment(ActiveProcessDefinitionNavigator.ACTIVE_PROC_DEF_URI_PART, processDefinitionId));
          
        } else {
          // Nothing selected
          setDetailComponent(null);
          WitsApp.get().setCurrentUriFragment(new UriFragment(ActiveProcessDefinitionNavigator.ACTIVE_PROC_DEF_URI_PART));
        }
      }
      
    });
    
    return processDefinitionTable;
  }
  
}
