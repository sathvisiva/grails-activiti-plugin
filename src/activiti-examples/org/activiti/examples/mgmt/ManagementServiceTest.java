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
package org.activiti.examples.mgmt;

import java.util.Map;

import org.activiti.engine.ManagementService;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.activiti.engine.management.TableMetaData;

/**
 * Test case for the various operations of the {@link ManagementService}
 * 
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public class ManagementServiceTest {
  @Rule public ActivitiRule activitiRule = new ActivitiRule();

  @Test 
  public void testTableCount() {
    ManagementService managementService = activitiRule.getManagementService();
    Map<String, Long> tableCount = managementService.getTableCount();

    assertEquals(new Long(2), tableCount.get("ACT_GE_PROPERTY"));
    assertEquals(new Long(0), tableCount.get("ACT_GE_BYTEARRAY"));
    assertEquals(new Long(0), tableCount.get("ACT_RE_DEPLOYMENT"));
    assertEquals(new Long(0), tableCount.get("ACT_RU_EXECUTION"));
    assertEquals(new Long(0), tableCount.get("ACT_ID_GROUP"));
    assertEquals(new Long(0), tableCount.get("ACT_ID_MEMBERSHIP"));
    assertEquals(new Long(0), tableCount.get("ACT_ID_USER"));
    assertEquals(new Long(0), tableCount.get("ACT_RE_PROC_DEF"));
    assertEquals(new Long(0), tableCount.get("ACT_RU_TASK"));
    assertEquals(new Long(0), tableCount.get("ACT_RU_IDENTITY_LINK"));
    ;
  }

  @Test 
  public void testGetTableMetaData() {
    ManagementService managementService = activitiRule.getManagementService();
    TableMetaData tableMetaData = managementService.getTableMetaData("ACT_RU_TASK");
    assertEquals(tableMetaData.getColumnNames().size(), tableMetaData.getColumnTypes().size());
    assertEquals(14, tableMetaData.getColumnNames().size());

    int assigneeIndex = tableMetaData.getColumnNames().indexOf("ASSIGNEE_");
    int createTimeIndex = tableMetaData.getColumnNames().indexOf("CREATE_TIME_");

    assertTrue(assigneeIndex >= 0);
    assertTrue(createTimeIndex >= 0);

    assertEquals("VARCHAR", tableMetaData.getColumnTypes().get(assigneeIndex));
    assertEquals("TIMESTAMP", tableMetaData.getColumnTypes().get(createTimeIndex));
  }

}
