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

package org.front.end.wits.vaadin.explorer.ui.management.deployment;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.front.end.wits.vaadin.explorer.ui.management.deployment.DeploymentListQuery.DeploymentListitem;


/**
 * Class used in {@link DeploymentListQuery} for filtering.
 * 
 * @author Frederik Heremans
 */
public interface DeploymentFilter {

  /**
   * Return a query that filters deployments, paging info is applied later on
   * and should not be altered.
   */
  DeploymentQuery getQuery(RepositoryService repositoryService);
  
  /**
   * Return a query that filters deployments, used for counting total number.
   */
  DeploymentQuery getCountQuery(RepositoryService repositoryService);
  
  /**
   * @param a {@link Deployment} resulting from the query
   * @return item representing the deployment
   */
  DeploymentListitem createItem(Deployment deployment);
}
