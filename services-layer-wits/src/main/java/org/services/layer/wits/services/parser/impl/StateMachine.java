package org.services.layer.wits.services.parser.impl;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateMachine {
	/*protected static Map<State, EnumSet<State>> stateMap;
	protected static Map<State, EnumSet<Action>> actionMap;
	protected static State currentState;*/
	protected Map<State, EnumSet<State>> stateMap;
	protected Map<State, EnumSet<Action>> actionMap;
	protected State currentState;
	static Logger logger = LoggerFactory.getLogger(StateMachine.class);
	
	public StateMachine()
	{
		stateMap = new HashMap<State, EnumSet<State>>();
		stateMap.put(State.START, EnumSet.of(State.CONTEXT));
		stateMap.put(State.CONTEXT, EnumSet.of(State.COORDINATE, State.OBJECT));
		stateMap.put(State.COORDINATE, EnumSet.of(State.COORDINATE,State.OBJECT));
		stateMap.put(State.OBJECT, EnumSet.of(State.START, State.CONTEXT,State.COORDINATE,State.OBJECT,State.END));
		// Configurate Start State 
		currentState = State.START;
		actionMap = new HashMap<State, EnumSet<Action>>();
		actionMap.put(State.START, 
			      EnumSet.of(Action.READCONTEXT));
		actionMap.put(State.CONTEXT, 
		      EnumSet.of(Action.READCOORDINATE, Action.READOBJECT));
		actionMap.put(State.COORDINATE, 
			      EnumSet.of(Action.READCOORDINATE, Action.READOBJECT));
		actionMap.put(State.OBJECT, 
			      EnumSet.of(Action.START, Action.READCONTEXT,Action.READCOORDINATE, Action.READOBJECT , Action.READCOMMAND,Action.TERMINATE));
		
	}

/*	static { 
		stateMap = new HashMap<State, EnumSet<State>>();
		stateMap.put(State.START, EnumSet.of(State.CONTEXT));
		stateMap.put(State.CONTEXT, EnumSet.of(State.COORDINATE, State.OBJECT));
		stateMap.put(State.COORDINATE, EnumSet.of(State.COORDINATE,State.OBJECT));
		stateMap.put(State.OBJECT, EnumSet.of(State.CONTEXT,State.COORDINATE,State.OBJECT,State.COMMAND,State.END));
		stateMap.put(State.COMMAND, EnumSet.of(State.CONTEXT,State.OBJECT));
		// Configurate Start State 
		currentState = State.START;
	}*/
	public void setCurrent(State desiredState) {
		logger.info("Stato di partenza: " + currentState + 
			       "; Stato di destinazione richiesto: " + desiredState);
		if (!isReachable(desiredState)) {
			throw new IllegalArgumentException();    
			}
		 setAsFinal(desiredState);
		}
	private boolean isReachable(State desiredState) {
		return stateMap.get(currentState).contains(desiredState);
		}
	private void setAsFinal(State desiredState) {
		currentState = desiredState;
		/*final ChangeEvent e = new ChangeEvent(currentState);
		for (final ChangeListener l : stateChangeListenerList) {
			l.stateChanged(e);
			}*/
	}
	/*static {
		actionMap = new HashMap<State, EnumSet<Action>>();
		actionMap.put(State.START, 
			      EnumSet.of(Action.READCONTEXT));
		actionMap.put(State.CONTEXT, 
		      EnumSet.of(Action.READCOORDINATE, Action.READOBJECT));
		actionMap.put(State.COORDINATE, 
			      EnumSet.of(Action.READCOORDINATE, Action.READOBJECT));
		actionMap.put(State.OBJECT, 
			      EnumSet.of(Action.READCONTEXT,Action.READCOORDINATE, Action.READOBJECT,Action.READCOMMAND));
		actionMap.put(State.COMMAND, 
			      EnumSet.of(Action.READCONTEXT,Action.READOBJECT));
		}*/
	/*public void stateChanged(ChangeEvent e) {
		if (e.getSource() instanceof BarcodeParserImpl.State) {
			changeButtonEnablement();
			}
		}*/
	protected void changeButtonEnablement() {
		/*switchOnButton.setEnabled(isAllowedAction(Azione.ACCENDIMOTORE));
		pressPedalButton.setEnabled(isAllowedAction(Azione.SCHIACCIAACCELERATORE));
		pressPedalHarderButton.setEnabled(isAllowedAction(Azione.SCHIACCIATROPPOACCELERATORE));
		switchOffButton.setEnabled(isAllowedAction(Azione.SPEGNIMOTORE));*/
		}
	public boolean isAllowedAction() {
		EnumSet<Action> allowed =
				actionMap .get(currentState); 
		  return allowed != null;
		}
	public EnumSet<Action> getAllowedAction() {
		EnumSet<Action> allowed =
				actionMap .get(currentState); 
		  return allowed;
		}
	public void setNextState(Action action)
	{
		switch (action) { 
		case START: 
			setCurrent(State.START);
			break;
		case READCONTEXT: 
			setCurrent(State.CONTEXT);
			break;
		case READCOORDINATE:
			setCurrent(State.COORDINATE);
			break;
		case READOBJECT:
			setCurrent(State.OBJECT);
			break;
		case READCOMMAND:
			break;
		case TERMINATE:
			setCurrent(State.END);
			break;
		default:
			logger.error("Azione sconosciuta: " + action);
			break;
		}    	
	}
	public State getCurrentState() {
		return currentState;
	}

}
