package org.services.layer.wits.services.parser.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.persistence.layer.wits.form.ContainingUnit;
import org.services.layer.wits.services.ContainingUnitService;
import org.services.layer.wits.services.parser.BarcodeParser;
import org.services.layer.wits.services.parser.ReadingObjectCoordinate;
import org.services.layer.wits.services.parser.ReadingObjectToMove;
import org.services.layer.wits.services.parser.ReadingBarcodeCommand;
import org.services.layer.wits.services.parser.ReadingBarcodeContex;
import org.services.layer.wits.services.parser.ReadingBarcodeParserConstants;
import org.services.layer.wits.services.parser.ReadingBarcodePhrase;
import org.services.layer.wits.services.parser.ReadingBarcodeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("barcodeParser")
@Scope("prototype")
public class BarcodeParserImpl implements BarcodeParser {
	protected List<ReadingBarcodeSession> listReadingBarcodeSession;

	protected ReadingBarcodeCommand readingBarcodeCommand;
	protected String returnMessage;
	protected StateMachine stateMachine;
	protected static Logger logger = LoggerFactory.getLogger(BarcodeParserImpl.class);
	@Autowired
	@Qualifier("containingUnitService")
	ContainingUnitService containingUnitService;

	@Override
	public String readSession(String inputRead , int line ) {
		Action nextAction = getNextAction( inputRead ,line);
		if (nextAction!=null)
		{
			EnumSet<Action> allowedActions = stateMachine.getAllowedAction();
			if (allowedActions.contains(nextAction))
			{
				nextAction = readLineInput(inputRead ,line , nextAction); 
				if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
					stateMachine.setNextState(nextAction);

			}
			else
			{
				returnMessage = ReadingBarcodeParserConstants.ACTION_NOT_ALLOWED;
			}
		}
		else
		{
			returnMessage = ReadingBarcodeParserConstants.LENGTH_LINE_NOT_COVERED;
		}
		return returnMessage;
	}

	private Action getNextAction(String inputRead , int line)
	{
		int lenInput = inputRead.length();
		Action retAction = null;
		switch (line)
		{
		case 1 : // first line
		{
			stateMachine = new StateMachine();
			if (listReadingBarcodeSession!=null && listReadingBarcodeSession.size()>0)
				listReadingBarcodeSession.clear();
			if (lenInput == ReadingBarcodeParserConstants.LEN_OBJECT_CONTAINER ) // Container Unit Immovable
			{
				retAction = Action.READCONTEXT;
				break;
			}
		}
		default :
		{
			switch (lenInput)
			{
			case ReadingBarcodeParserConstants.LEN_OBJECT_CONTAINER : // Container Unit 
			{
				ContainingUnit containingUnit = containingUnitService.getContainingUnit(inputRead.trim());
				if (stateMachine.getCurrentState().equals(State.START))
					retAction = Action.READCONTEXT;
				if (stateMachine.getCurrentState().equals(State.OBJECT))
				{
					if (containingUnit!=null)
					{
						if (containingUnit.getContainingUnitTypeSt().getIsImmobile())
							retAction = Action.READCONTEXT;
						else
							retAction = Action.READOBJECT;
					}
					else
						retAction = Action.READCONTEXT;
				}
				if (stateMachine.getCurrentState().equals(State.COORDINATE))
					retAction = Action.READOBJECT;
				break;
			}
			case ReadingBarcodeParserConstants.LEN_COMMAND_COORDINATE : // Commands OR Coordinate
			{
				if (inputRead.substring(0, 1).equals("A") &&
						inputRead.length()==ReadingBarcodeParserConstants.LEN_COMMAND_COORDINATE)
					retAction = Action.READCOMMAND;
				else
					retAction = Action.READCOORDINATE;
				break;
			}
			case ReadingBarcodeParserConstants.LEN_OBJECT_WAMAT_WITS2 : // WamatObject
			case ReadingBarcodeParserConstants.LEN_OBJECT_WAMAT : // WamatObject
			{
				retAction = Action.READOBJECT;
				break;
			}
			default :				
			{
				break;
			}
			}
		}
		}
		return retAction;	
	}

	private Action readLineInput(String inputRead , int line , Action nextAction )
	{
		Action retAction = nextAction;
		switch (retAction)
		{
		case READCONTEXT : 
		{
			if (!stateMachine.getCurrentState().equals(State.START))
				stateMachine.setCurrent(State.START);	
			ReadingBarcodeSession readingBarcodeSession = new ReadingBarcodeSessionImpl();
			ReadingBarcodeContex readingBarcodeContex = new ReadingBarcodeContexImpl();
			returnMessage = readingBarcodeContex.readContext(inputRead);
			if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
			{
				if (listReadingBarcodeSession==null)
					listReadingBarcodeSession = new ArrayList<ReadingBarcodeSession>();
				readingBarcodeSession.setReadingBarcodeContex(readingBarcodeContex);
				listReadingBarcodeSession.add(readingBarcodeSession);
			}
			break;
		}
		case READCOORDINATE : 
		{
			if (stateMachine.getCurrentState().equals(State.CONTEXT) ||
					stateMachine.getCurrentState().equals(State.OBJECT))
			{
				ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl();
				returnMessage = objectCoordinate.readCoordinate(inputRead);
				if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
				{
					/* Create new readingBarcodePhrase */
					ReadingBarcodePhrase readingBarcodePhrase = new ReadingBarcodePhraseImpl();
					readingBarcodePhrase.addCoordinate(objectCoordinate);
					listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().addReadingBarcodePhrase(readingBarcodePhrase);
				}

			}
			else
			{
				ReadingObjectCoordinate objectCoordinate = new ReadingObjectCoordinateImpl();
				returnMessage = objectCoordinate.readCoordinate(inputRead);
				if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
				{
					/* Update new readingBarcodePhrase */
					listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().updateReadingBarcodePhrase(objectCoordinate);	
				}

			}
			break;
		}
		case READOBJECT : 
		{
			if (stateMachine.getCurrentState().equals(State.CONTEXT) ||
					stateMachine.getCurrentState().equals(State.OBJECT))
			{
				ReadingObjectToMove objectToMove = new ReadingObjectToMoveImpl();
				returnMessage = objectToMove.readObject(inputRead);
				if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
				{
					ReadingBarcodePhrase readingBarcodePhrase = new ReadingBarcodePhraseImpl();
					readingBarcodePhrase.addObjectToMove(objectToMove);
					/*
					 * Inherit Coordinate from previous WamatObject into the list of the Phrases
					 */
					ReadingObjectCoordinate objectCoordinate = listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().inheritingObjectCoordinate();
					readingBarcodePhrase.addCoordinate(objectCoordinate);
					listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().addReadingBarcodePhrase(readingBarcodePhrase);
				}

			}
			else
			{
				ReadingObjectToMove objectToMove = new ReadingObjectToMoveImpl();
				returnMessage = objectToMove.readObject(inputRead);
				if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
				{
					int index = listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().getListReadingBarcodePhrase().size();
					/* last coordinate read */
					ReadingObjectCoordinate objectCoordinateLast = listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().getListReadingBarcodePhrase().get(index-1).getCoordinate();
					if (objectCoordinateLast.isEmptySomeCoordinate())
					{
						/*
						 * Inherit Coordinate from previous WamatObject into the list of the Phrases
						 */
						for (String item : objectCoordinateLast.getListEmptyCoordinate())
						{
							Integer valueCoordinate = listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().inheritingValueCoordinate(item);
							objectCoordinateLast.getCoordinateXYZ().put(item, valueCoordinate);
						}
						listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().getListReadingBarcodePhrase().get(index-1).updateCoordinate(objectCoordinateLast);
					}
					listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().updateReadingBarcodePhrase(objectToMove);	
				}

			}
			break;
		}
		case READCOMMAND : 
		{
			ReadingBarcodeCommand readingBarcodeCommand = new ReadingBarcodeCommandImpl();
			returnMessage = readingBarcodeCommand.readCommand(inputRead);
			if (returnMessage.equals(ReadingBarcodeParserConstants.SINTAX_CHECK_OK))
			{
				ReadingBarcodeCommandEnum readingBarcodeCommandEnum = readingBarcodeCommand.getReadingBarcodeCommandEnum();
				switch (readingBarcodeCommandEnum)
				{
				case RESET :
				{
					retAction = Action.START;
					break;
				}
				case CLEAR_LAST :
				{
					//listReadingBarcodeSession.get(listReadingBarcodeSession.size()-1).getReadingBarcodeContex().clearLastWamatObject();
					stateMachine.setCurrent(State.COORDINATE);
					retAction = Action.READCOORDINATE;
					break;
				}
				case TERMINATE :
				{
					retAction = Action.TERMINATE;
					break;
				}
				default :
				{
					break;
				}
				}
				EnumSet<Action> allowedActions = stateMachine.getAllowedAction();
				if (!allowedActions.contains(retAction))
				{
					returnMessage = ReadingBarcodeParserConstants.ACTION_NOT_ALLOWED;
				}
			}
			break;
		}
		default :
		{
			break;
		}
		}
		return retAction;
	}

	@Override
	public void writeSessionInfoOnLogger() {
		for (ReadingBarcodeSession item : listReadingBarcodeSession )
		{
			logger.info("Context " + item.getReadingBarcodeContex().getReadinBarcodeContext());
			for (ReadingBarcodePhrase readingBarcodePhrase : item.getReadingBarcodeContex().getListReadingBarcodePhrase())
			{
				if (readingBarcodePhrase.getCoordinate()!=null)
				{
					for ( String coordinate : readingBarcodePhrase.getCoordinate().getCoordinateXYZ().keySet())
					{
						logger.info("Coordinate " + coordinate + " : " + readingBarcodePhrase.getCoordinate().getCoordinateXYZ().get(coordinate));

					}
				}
				if (readingBarcodePhrase.getObjectToMove()!=null)
				{
					logger.info("WamatObject " + readingBarcodePhrase.getObjectToMove().getObjectToMove());

				}
			}
		}

	}
	@Override
	public List<ReadingBarcodeSession> getListReadingBarcodeSession() {
		return listReadingBarcodeSession;
	}


}
