package org.services.layer.wits.logic;

import org.junit.Test;
import org.services.layer.wits.servicesimpl.Solution;

public class SolutionTest {
    @Test
	public void SolutionVerify()
    {
    	Solution p = new Solution();
    	int[] prova = {0,3,3,7,5,3,11,1};
    	p.adjacent_point_pairs_count(prova);		
    	
    }
}
