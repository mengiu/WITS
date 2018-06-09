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

package org.front.end.wits.vaadin.editor.ui;

import java.util.List;

import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.front.end.wits.vaadin.WitsApp;
import org.front.end.wits.vaadin.Messages;
import org.front.end.wits.vaadin.navigation.ProcessModelNavigator;
import org.front.end.wits.vaadin.navigation.UriFragment;
import org.front.end.wits.vaadin.ui.AbstractTablePage;
import org.front.end.wits.vaadin.ui.Images;
import org.front.end.wits.vaadin.ui.custom.ToolBar;
import org.front.end.wits.vaadin.ui.mainlayout.ExplorerLayout;
import org.front.end.wits.vaadin.ui.view.workflow.process.ProcessMenuBar;
import org.front.end.wits.vaadin.ui.view.workflow.process.listener.ImportModelClickListener;
import org.front.end.wits.vaadin.ui.view.workflow.process.listener.NewModelClickListener;
import org.front.end.wits.vaadin.ui.util.ThemeImageColumnGenerator;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;


/**
 * @author Tijs Rademakers
 */
public class EditorProcessDefinitionPage extends AbstractTablePage {

	private static final long serialVersionUID = 1L;

	// Services
	protected RepositoryService repositoryService = ProcessEngines.getDefaultProcessEngine().getRepositoryService();

	// UI
	protected String modelId;
	protected EditorProcessDefinitionDetailPanel detailPanel;

	public EditorProcessDefinitionPage() {
		WitsApp.get().setCurrentUriFragment(new UriFragment(ProcessModelNavigator.PROCESS_MODEL_URI_PART));
	}

	/**
	 * Used when the page is reached through an URL.
	 * The page will be built and the given process definition will be selected.
	 */
	public EditorProcessDefinitionPage(String modelId) {
		this();
		this.modelId = modelId;
	}

	@Override
	protected void initUi() {
		super.initUi();
		if (modelId == null) {
			firstTable.select(firstTable.firstItemId());
		} else {
			firstTable.select(modelId);
		}
	}

	@Override
	protected ToolBar createMenuBar() {
		ProcessMenuBar menuBar = new ProcessMenuBar();
		Button newModelButton = new Button(WitsApp.get().getI18nManager().getMessage(Messages.PROCESS_NEW));
		newModelButton.addListener(new NewModelClickListener());
		menuBar.addButton(newModelButton);
		Button importModelButton = new Button(WitsApp.get().getI18nManager().getMessage(Messages.PROCESS_IMPORT));
		importModelButton.addListener(new ImportModelClickListener());
		menuBar.addButton(importModelButton);
		return menuBar;
	}

	@Override
	protected Table createFirstList() {
		final Table processDefinitionTable = new Table();
		processDefinitionTable.addStyleName(ExplorerLayout.STYLE_PROCESS_DEFINITION_LIST);

		// Set non-editable, selectable and full-size
		processDefinitionTable.setEditable(false);
		processDefinitionTable.setImmediate(true);
		processDefinitionTable.setSelectable(true);
		processDefinitionTable.setNullSelectionAllowed(false);
		processDefinitionTable.setSortDisabled(true);
		processDefinitionTable.setSizeFull();

		// Listener to change right panel when clicked on a model
		processDefinitionTable.addListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				showProcessDefinitionDetail((String) event.getProperty().getValue());
			}
		});

		// Create columns
		processDefinitionTable.addGeneratedColumn("icon", new ThemeImageColumnGenerator(Images.PROCESS_22));
		processDefinitionTable.setColumnWidth("icon", 22);

		processDefinitionTable.addContainerProperty("name", String.class, null);
		processDefinitionTable.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);

		List<Model> modelList = repositoryService.createModelQuery().list();
		for (Model modelData : modelList) {
			Item item = processDefinitionTable.addItem(modelData.getId());
			item.getItemProperty("name").setValue(modelData.getName());
		}


		return processDefinitionTable;
	}

	protected void showProcessDefinitionDetail(String selectedModelId) {
		detailPanel = new EditorProcessDefinitionDetailPanel(selectedModelId, this);
		setDetailComponent(detailPanel);
		changeUrl("" + selectedModelId);
	}

	protected void changeUrl(String processDefinitionId) {
		UriFragment processDefinitionFragment = new UriFragment(ProcessModelNavigator.PROCESS_MODEL_URI_PART, processDefinitionId);
		WitsApp.get().setCurrentUriFragment(processDefinitionFragment);
	}

}
