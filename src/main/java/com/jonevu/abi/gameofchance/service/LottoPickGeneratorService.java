/**
 * 
 */
package com.jonevu.abi.gameofchance.service;

import com.jonevu.abi.gameofchance.model.Entry;

/**
 * @author jonev
 *
 */
public interface LottoPickGeneratorService {
	// ...

	String pickMega(int numOfPicks);
	
	String pickPoewrBall(int numOfPicks);
	
	String pickRevLotto(Entry entry);
	
	String pickRegLotto(int numOfPicks);

	String getMegaAnalysis(String inputInfo);

	String getPowerballAnalysis(String inputInfo);

	String getLottoAnalysis(String inputInfo);

	String getLottoMore(String inputInfo);
}
