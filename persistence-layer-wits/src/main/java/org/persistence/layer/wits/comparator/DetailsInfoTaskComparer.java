package org.persistence.layer.wits.comparator;

import java.util.Comparator;

import org.persistence.layer.wits.comparator.task.data.DetailInfoTask;
import org.persistence.layer.wits.enumusertypes.OrdersFieldsSecondTableTasks;

public class DetailsInfoTaskComparer implements Comparator<DetailInfoTask> {

	protected Boolean ascending;
	protected OrdersFieldsSecondTableTasks ordersFieldsSecondTableTasks;

	public DetailsInfoTaskComparer(OrdersFieldsSecondTableTasks ordersFieldsSecondTableTasks, 
			Boolean ascending)
	{
		this.ascending = ascending;
		this.ordersFieldsSecondTableTasks = ordersFieldsSecondTableTasks;
	}

	@Override
	public int compare(DetailInfoTask o1, DetailInfoTask o2) {
		int resultRet = -1;
		if (ordersFieldsSecondTableTasks!=null)
		{
			switch (ordersFieldsSecondTableTasks)
			{
			case CONTAININGUNITCODE :
			{
				if (o1.getContainingunitcode()!=null && o2.getContainingunitcode()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getContainingunitcode().compareTo(o2.getContainingunitcode());
					}
					else
					{
						resultRet = o2.getContainingunitcode().compareTo(o1.getContainingunitcode());
					}
				}
				break;
			}
			case DESTINATION :
			{
				if (o1.getDestination()!=null && o2.getDestination()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getDestination().compareTo(o2.getDestination());
					}
					else
					{
						resultRet = o2.getDestination().compareTo(o1.getDestination());
					}
				}
				break;
			}
			case TASKNAME :
			{
				if (ascending)
				{
					resultRet = o1.getTaskname().compareTo(o2.getTaskname());
				}
				else
				{
					resultRet = o2.getTaskname().compareTo(o1.getTaskname());
				}
				break;
			}
			case WAMATINPUT :
			{
				if (o1.getWamatinput()!=null && o2.getWamatinput()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getWamatinput().compareTo(o2.getWamatinput());
					}
					else
					{
						resultRet = o2.getWamatinput().compareTo(o1.getWamatinput());
					}
				}
				break;
			}
			case STARTDATE :
			{
				if (o1.getStartDate()!=null && o2.getStartDate()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getStartDate().compareTo(o2.getStartDate());
					}
					else
					{
						resultRet = o2.getStartDate().compareTo(o1.getStartDate());
					}
				}
				break;
			}
			case CREATIONDATE :
			{
				if (ascending)
				{
					resultRet = new Long(o1.getCreationdate().getTime()-o2.getCreationdate().getTime()).intValue();
				}
				else
				{
					resultRet = new Long(o2.getCreationdate().getTime()-o1.getCreationdate().getTime()).intValue();
				}
				break;
			}
			case NAMEPROCESS :
			{
				if (o1.getNameprocess()!=null && o2.getNameprocess()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getNameprocess().compareTo(o2.getNameprocess());
					}
					else
					{
						resultRet = o2.getNameprocess().compareTo(o1.getNameprocess());
					}
				}
				break;
			}
			case WAMATOBJECTCODE :
			{
				if (o1.getWamatObjectCode()!=null && o2.getWamatObjectCode()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getWamatObjectCode().compareTo(o2.getWamatObjectCode());
					}
					else
					{
						resultRet = o2.getWamatObjectCode().compareTo(o1.getWamatObjectCode());
					}
				}
				break;
			}
			case CONTAINERUNITTYPE :
			{
				if (o1.getCuType()!=null && o2.getCuType()!=null)
				{
					if (ascending)
					{
						resultRet = o1.getCuType().compareTo(o2.getCuType());
					}
					else
					{
						resultRet = o2.getCuType().compareTo(o1.getCuType());
					}
				}
				break;
			}
			case USERREQUESTING :
			{
				if (ascending)
				{
					resultRet = o1.getInitiator().compareTo(o2.getInitiator());
				}
				else
				{
					resultRet = o2.getInitiator().compareTo(o1.getInitiator());
				}
				break;
			}
			default :
			{
				if (ascending)
				{
					resultRet = o1.getIdTask().compareTo(o1.getIdTask());
				}
				else
				{
					resultRet = o2.getIdTask().compareTo(o1.getIdTask());
				}
				break;
			}
			}
		}
		return resultRet;
	}

}
