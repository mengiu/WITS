package org.services.layer.wits.services.model;

import java.io.Serializable;
import java.util.List;

import org.persistence.layer.wits.form.GenerationProcessSt;
import org.persistence.layer.wits.form.WamatObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondLevelControlStartPWMO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<WamatObject> listWamatObjectInputProcess;
	protected GenerationProcessSt generationProcessSt;
	protected String messageErrorRet;
	protected static Logger logger = LoggerFactory.getLogger(SecondLevelControlStartPWMO.class);
	protected StatusReturnAnswer statusReturnAnswer;
	protected boolean oneInstanceForEachWamat;
	protected boolean numberWamatInputNotCorrect;
	final int MAX_NUMBER_WAMAT_INPUT = 999;

	public SecondLevelControlStartPWMO()
	{
		statusReturnAnswer = StatusReturnAnswer.OK; // DEFAULT
		oneInstanceForEachWamat = false;
		numberWamatInputNotCorrect = false;
	}
	public List<WamatObject> getListWamatObjectInputProcess() {
		return listWamatObjectInputProcess;
	}
	public void setListWamatObjectInputProcess(
			List<WamatObject> listWamatObjectInputProcess) {
		this.listWamatObjectInputProcess = listWamatObjectInputProcess;
	}
	public GenerationProcessSt getGenerationProcessSt() {
		return generationProcessSt;
	}
	public void setGenerationProcessSt(GenerationProcessSt generationProcessSt) {
		this.generationProcessSt = generationProcessSt;
	}
	/*
	 * See page 7 of {link @Workflow- Processamento WaMat - 1.1.doc}
	 */
	public boolean isRangeObjectForProcessInstanceCorrect()
	{
		boolean bRet = false;
		int numberWamatInput = 0;
		if (listWamatObjectInputProcess!=null)
			numberWamatInput = listWamatObjectInputProcess.size();
		if (generationProcessSt.getMaxWamatPerInstance()!=null &&
				generationProcessSt.getMaxWamatPerInstance()>=0)
		{
			switch(generationProcessSt.getMaxWamatPerInstance())
			{
			case 0 :
			{
				oneInstanceForEachWamat = false;
				numberWamatInputNotCorrect = (numberWamatInput == 0);
				bRet = numberWamatInputNotCorrect;
				break;
			}
			case 1 :
			{
				if ( numberWamatInput <= MAX_NUMBER_WAMAT_INPUT)
				{
					bRet = oneInstanceForEachWamat = true;
				}
				else
				{
					bRet = numberWamatInputNotCorrect = false;
				}
				break;
			}
			default :
			{
				if ( numberWamatInput <= MAX_NUMBER_WAMAT_INPUT)
				{
					 oneInstanceForEachWamat = false;
					 bRet = (numberWamatInput <= generationProcessSt.getMaxWamatPerInstance());
				}
				else
				{
					bRet = numberWamatInputNotCorrect = false;
				}
				break;
			}
			}
		}
		return bRet;
	}
	public String getMessageErrorRet() {
		return messageErrorRet;
	}
	public void setMessageErrorRet(String messageErrorRet) {
		this.messageErrorRet = messageErrorRet;
	}
	public StatusReturnAnswer getStatusReturnAnswer() {
		return statusReturnAnswer;
	}
	public void setStatusReturnAnswer(StatusReturnAnswer statusReturnAnswer) {
		this.statusReturnAnswer = statusReturnAnswer;
	}
	public boolean isOneInstanceForEachWamat() {
		return oneInstanceForEachWamat;
	}
	public void setOneInstanceForEachWamat(boolean oneInstanceForEachWamat) {
		this.oneInstanceForEachWamat = oneInstanceForEachWamat;
	}
	public boolean isNumberWamatInputNotCorrect() {
		return numberWamatInputNotCorrect;
	}
	public void setNumberWamatInputNotCorrect(boolean numberWamatInputNotCorrect) {
		this.numberWamatInputNotCorrect = numberWamatInputNotCorrect;
	}
}
