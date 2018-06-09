package org.persistence.layer.wits.comparator;

import java.util.Comparator;

import org.persistence.layer.wits.comparator.task.data.GenericInfoTask;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsFirstTableTasks;

public class GenericInfoTaskComparer implements Comparator<GenericInfoTask> {
	protected Boolean ascending;
	protected OrdersFieldsFirstTableTasks ordersFieldsFirstTableTasks;

	public GenericInfoTaskComparer(OrdersFieldsFirstTableTasks ordersFieldsFirstTableTasks, 
			Boolean ascending)
	{
		this.ascending = ascending;
		this.ordersFieldsFirstTableTasks = ordersFieldsFirstTableTasks;
	}

	@Override
	public int compare(GenericInfoTask o1, GenericInfoTask o2) {
		int resultRet = -1;
		if (ordersFieldsFirstTableTasks!=null)
		{
			switch (ordersFieldsFirstTableTasks)
			{
			case NAME :
			{
				if (ascending)
				{
					resultRet = o1.getName().compareTo(o2.getName());
				}
				else
				{
					resultRet = o2.getName().compareTo(o1.getName());
				}
				break;
			}
			case WORKFLOWNAME :
			{
				if (ascending)
				{
					resultRet = o1.getWorkflowName().compareTo(o2.getWorkflowName());
				}
				else
				{
					resultRet = o2.getWorkflowName().compareTo(o1.getWorkflowName());
				}
				break;
			}
			case COUNTTASKS :
			{
				if (ascending)
				{
					resultRet = o1.getCountTasks().compareTo(o2.getCountTasks());
				}
				else
				{
					resultRet = o2.getCountTasks().compareTo(o1.getCountTasks());
				}
				break;
			}
			default :
			{
				if (ascending)
				{
					resultRet = o1.getName().compareTo(o2.getName());
				}
				else
				{
					resultRet = o2.getName().compareTo(o1.getName());
				}
				break;
			}
			}
		}
		return resultRet;
	}

}
