package com.jonevu.abi.gameofchance.service.impl;

import com.jonevu.abi.gameofchance.service.AnalyzeIt;
import com.jonevu.abi.util.LineUtil;
import org.springframework.stereotype.Service;

import com.jonevu.abi.gameofchance.model.Entry;
import com.jonevu.abi.gameofchance.service.LottoPickGeneratorService;
import com.jonevu.abi.constants.LottoConstants;
import com.jonevu.abi.util.CommonUtil;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
@Service
public class LottoPickGeneratorServiceImpl implements LottoPickGeneratorService {

	private static final Logger logger = LoggerFactory.getLogger(LottoPickGeneratorServiceImpl.class);

	private static final int TOTAL_LOTTO_NUMBERS = 6;    // Illinois specific lotto number picks ...
	
	@Override
	public String pickMega(int numOfPicks) {
		logger.info("Processing mega tickets ...");
		StringBuilder sb = new StringBuilder();
        try {
            sb.append("MEGA__ Reg # _  _ PWR# _\n\n");
            sb.append(getQuikPicks(numOfPicks, LottoConstants.MEGA, 5, false));
            sb.append("\n************************ Total ticket Price: $"+(numOfPicks*2)+".00");
        }
        catch(Exception ex) {
            logger.error("Exception Error! "+ex.getMessage());
        }
		return sb.toString();
	}

	@Override
	public String pickPoewrBall(int numOfPicks) {
		logger.info("Processing power-ball tickets ...");
		StringBuilder sb = new StringBuilder();
        try {
        	sb.append("PWRBALL__ Reg # _ PWR# _\n\n");
            sb.append(getQuikPicks(numOfPicks, LottoConstants.PWR_BALL, 5, false));
            sb.append("\n************************ Total ticket Price: $"+(numOfPicks*2)+".00");
        }
        catch(Exception ex) {
            logger.error("Exception Error! "+ex.getMessage());
        }
		return sb.toString();
	}

	@Override
	public String pickRevLotto(Entry entry) {
		// validate requested numbers ..
		logger.info("Processing reversed lotto tickets ...");
		String message = validateRequestedNumbers(entry.getNumbers());
		if (message != null) {
			return message;
		}
		String orig = "Originally selected numbers: "
				+Arrays.toString(entry.getNumbers())+"\n.................\n\n";
		StringBuilder sb = processLottoNumbers(entry);
		return orig+CommonUtil.formatStringData(sb);
	}    

	@Override
	public String pickRegLotto(int numOfPicks) {
		logger.info("Processing Regular Lotto tickets ...");
		StringBuilder sb = new StringBuilder();
        try {
        	sb.append("__REGULAR-LOTTO__\n\n");
            sb.append(getQuikPicks(numOfPicks, LottoConstants.REG_LOTTO, 6, false));
            sb.append("\n************************ Total ticket Price: $"+(numOfPicks)+".00");
        }
        catch(Exception ex) {
            logger.error("Exception Error! "+ex.getMessage());
        }
		return sb.toString();
	}

    /**
     *
     * @param inputInfo -
     * @return -
     */
    @Override
    public String getMegaAnalysis(String inputInfo) {
        // ...
        logger.info("Analyzing MEGA machine probables ...");
        Map<String, String> inputMap = processInputInfo(inputInfo);
        String picks = getQuikPicks(Integer.valueOf(inputMap.get("p")),
                LottoConstants.MEGA, 5, true);
        return  analyzeGameOfChance(picks, inputMap.get("w"),
                LottoConstants.MEGGA,true, inputMap.get("d"));
    }

    /**
     *
     * @param inputInfo -
     * @return -
     */
    @Override
    public String getPowerballAnalysis(String inputInfo) {
        // ...
        logger.info("Analyzing powerball ball machine probables ...");
        Map<String, String> inputMap = processInputInfo(inputInfo);
        String picks = getQuikPicks(Integer.valueOf(inputMap.get("p")),
                LottoConstants.PWR_BALL, 5, true);
        return  analyzeGameOfChance(picks, inputMap.get("w"),
                LottoConstants.POWERBALL,true, inputMap.get("d"));
     }

    /**
     *
     * @param inputInfo -
     * @return -
     */
    @Override
    public String getLottoAnalysis(String inputInfo) {
        // ...
        logger.info("Analyzing LOTTO machine probables ...");
        Map<String, String> inputMap = processInputInfo(inputInfo);
        String picks = getQuikPicks(Integer.valueOf(inputMap.get("p")),
                LottoConstants.REG_LOTTO, 5, false);
        return  analyzeGameOfChance(picks, inputMap.get("w"),
                LottoConstants.LOTTO,false, inputMap.get("d"));
    }

    @Override
    public String getLottoMore(String inputInfo) {
        logger.info("Analyzing lotto MORE machine probables ...");
        Map<String, String> loadMap = loadInputData(inputInfo);
        StringBuilder sbHeader = new StringBuilder();
        if (loadMap.get("type").equalsIgnoreCase("m"))
            sbHeader.append(LineUtil.MEGA_LINE);
        else if (loadMap.get("type").equalsIgnoreCase("p"))
            sbHeader.append(LineUtil.PWRBALL_LINE);
        else
            sbHeader.append(LineUtil.LOTTO_LINE);
        sbHeader.toString();
        StringBuilder sbBody = new StringBuilder();
        String picks = loadMap.get("picks");

        sbBody.append(Arrays.toString(picks.split(","))).append("\n");


        return sbHeader.toString()+CommonUtil.formatStringData(sbBody);
    }

    /**
     * Expects pattern ex: min<comma-delim-nbrs>max:type
     * @param data
     * @return
     */
    private Map<String, String> loadInputData(String data) {
        Map<String, String> loadData = new HashMap<>();
        String min = data.substring(0, data.indexOf("<"));
        String picks = data.substring(data.indexOf("<")+1, data.indexOf(">"));
        String max = data.substring(data.indexOf(">")+1, data.indexOf(":"));
        String type = data.split(":")[1];

        loadData.put("min",min);
        loadData.put("picks", picks);
        loadData.put("max", max);
        loadData.put("type", type);
        logger.info("min: "+min+"  picks: "+picks+"  max: "+max+"  type: "+type);

        return loadData;
    }

    private String validatePick(String num, String type) {
        int nbr = Math.abs(Integer.parseInt(num));
        if (nbr==0)
            return num;
        if (type.equalsIgnoreCase("P") && nbr > LottoConstants.PWR_BALL_PICK5_MAX_NUM)
            return String.valueOf(LottoConstants.PWR_BALL_PICK5_MAX_NUM);
        else if(type.equalsIgnoreCase("m") && nbr > LottoConstants.MEGA_PICK5_MAX_NUM)
            return String.valueOf(LottoConstants.MEGA_PICK5_MAX_NUM);
        else if (type.equalsIgnoreCase("r") && nbr > LottoConstants.LOTTO_PICKS_MAX_NUM)
            return String.valueOf(LottoConstants.LOTTO_PICKS_MAX_NUM);
        return String.valueOf(nbr);
    }
    /**
     *
     * @param numbers -
	 * @return -
	 */
	private String validateRequestedNumbers(int[] numbers) {
		// selected numbers must be 6 ...
		if (numbers.length != LottoPickGeneratorServiceImpl.TOTAL_LOTTO_NUMBERS) {
			return "Selected numbers must total 6";
		}
		else {
			Set<Integer> numSet = Arrays.stream(numbers).boxed().collect(Collectors.toSet()); // convert int[] to Integer ...
			logger.info("numSet size="+numSet.size()+" .. collArr size="+numbers.length);
			// no duplicates in selected numbers ...
			if (numSet.size() != numbers.length) {
				return "No duplicates allowed in selected numbers!";
			}
			// selected numbers must be between 1 and 52 ...
			SortedSet<Integer> ss = new TreeSet<>(numSet);
			if (ss.first() <= 0 || ss.last() > 52) {
				return "Selected numbers must be between 1 and 52...";
			}
		}
		return null;
	}

	/**
	 * 
	 * @param entry -
	 * @return -
	 */
    private StringBuilder processLottoNumbers(Entry entry) {
    	
    	int size;
    	// recursive process ...
    	StringBuilder sb = new StringBuilder();
    	do {
    		List<Integer> listNums = getNumbers(entry.getNumbers());
    		size = listNums.size();
    		if (LottoPickGeneratorServiceImpl.TOTAL_LOTTO_NUMBERS == size) {
    			sb.append(listNums.toString()).append("\n");
    		}
    		if (sb.length()==0) {
    			sb.append(Arrays.toString(entry.getNumbers())).append("\n");
    		}
    		entry.setNumbers(listNums.stream().mapToInt(i->i).toArray());  // convert Integer to int[] ...
    	} while(size==LottoPickGeneratorServiceImpl.TOTAL_LOTTO_NUMBERS);
    	
		return sb;
	}

    /**
     * if a number is duplicate add the first and 
     * last number, If that number is still a duplicate,
     * then set number to LOTTO_PICKS_MA_NUM
     * @param numbers -
     * @return -
     */
    private List<Integer> getNumbers(int[] numbers) {
    	Set<Integer> setNum = new HashSet<>();
    	List<Integer> listNums = new ArrayList<>();
    	
    	for (int i=0; i<=numbers.length; i++) {
    		if (i<numbers.length-1) {
	    		int num = Math.abs(numbers[i]-numbers[i+1]);
	    		if (setNum.contains(num)) {
	    			break;
	    		}
	    		setNum.add(num);
	    		listNums.add(num);
	    		if (setNum.size() == LottoPickGeneratorServiceImpl.TOTAL_LOTTO_NUMBERS-1) {
	    			num = Math.abs(numbers[numbers.length-1]-numbers[0]);
	    			if (setNum.contains(num)) {
	    				num = Math.abs(numbers[numbers.length-1]+numbers[0]);
	    				if (setNum.contains(num)) {
	    					num = LottoConstants.LOTTO_PICKS_MAX_NUM;
	    				}
	    			}
		    		setNum.add(num);
		    		listNums.add(num);
	    		}
    		}
    	}
    	logger.info("*** listNums: "+listNums.toString());
    	return listNums;
    }

	/**
     * 
     * @param tickets -
     * @return -
     */
    private static String getQuikPicks(int tickets, String pickType, int columnSize, boolean isAnalysis) {
        // ...
        StringBuilder sb = new StringBuilder();
        // ...
        for (int j=0; j<tickets; j++) {
            Set<String> set = getPick(pickType, columnSize);
            for (String s : set) {
                sb.append(CommonUtil.charPad(s, 2, "0", "l")).append(" ");
            }
            if (!pickType.equalsIgnoreCase(LottoConstants.REG_LOTTO)) {
                if (!isAnalysis)
				    sb.append(".......");
				sb.append(CommonUtil.charPad(String.valueOf(getLottoNumber(pickType)), 2, "0", "l"));
			}
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 
     * @param columnSize -
     * @param pickType -
     * @return -
     */
    private static Set<String> getPick(String pickType, int columnSize) {
        // ...
    	int picknum = (pickType.equalsIgnoreCase(LottoConstants.MEGA) ?
    			LottoConstants.MEGA_PICK5_MAX_NUM : LottoConstants.PWR_BALL_PICK5_MAX_NUM);
    	if (pickType.equalsIgnoreCase(LottoConstants.REG_LOTTO)) {
    		picknum = LottoConstants.LOTTO_PICKS_MAX_NUM;
    	}
        // ...
        return setRandomSeed(picknum, columnSize);
    }
 
    /**
     * 
     * @param picknum -
     * @param columnSize -
     * @return -
     */
    private static Set<String> setRandomSeed(int picknum, int columnSize) {
    	Set<String> set = new HashSet<>();
        while (set.size() < columnSize) {
            int rad = Integer.parseInt(CommonUtil.getRandomNumber(2));
            rad = (rad % (picknum)) + 1;
            set.add(String.valueOf(rad));
        }
        return set;
    }
    
    /**
     * 
     * @param pickType -
     * @return -
     */
    private static int getLottoNumber(String pickType) {
        // ...
    	int pwrBallMaxNums = (pickType.equalsIgnoreCase(LottoConstants.MEGA) ?
    			LottoConstants.MEGA_PWR_BALL_MAX_NUM : LottoConstants.PWR_BALL_MAX_NUM);
        int rad = Integer.parseInt(CommonUtil.getRandomNumber(2));
        rad = (rad % (pwrBallMaxNums)) + 1;
        // ...
        return rad;
    }

    /**
     *
     * @param inputInfo - coming in as p=num;w=num1,num2,num3,num4,num5,num6 -- note that num6=powerball
     * @return -
     */
    private Map<String, String> processInputInfo(String inputInfo) {
        Map<String, String> inputMap = new HashMap<>();
        String[] inputArr = inputInfo.split(":");
        inputMap.put(inputArr[0].split("=")[0], inputArr[0].split("=")[1]);
        inputMap.put(inputArr[1].split("=")[0], inputArr[1].split("=")[1]);
        inputMap.put(inputArr[2].split("=")[0], inputArr[2].split("=")[1]);

        return inputMap;
    }

    /**
     *
     * @param aggregPickedNumbers -
     * @param winningNumbers -
     * @param gameType -
     * @param isNonLotto -
     * @param dateStr -
     * @return -
     */
    private String analyzeGameOfChance(String aggregPickedNumbers, String winningNumbers, String gameType,
                                       boolean isNonLotto, String dateStr) {
        String[] aggregPickedNumbersArr = aggregPickedNumbers.split("\n");
        String[]  winningNumbersArr =  winningNumbers.split(",");

        logger.info("aggregPickedNumbersArr length=" +
                ""+aggregPickedNumbersArr.length+" ..... winningNumbersArr length: "+winningNumbersArr.length);

        return getResults(aggregPickedNumbersArr, gameType, isNonLotto, dateStr, winningNumbers);
    }

    /**
     *
     * @param aggregateNumbers -
     * @param winningNumbers -
     * @param gameType -
     * @param isNonLotto -
     * @param dateStr -
     * @return -
     */
    private String getResults(String[] aggregateNumbers, String gameType, boolean isNonLotto,
                              String dateStr, String winningNumbers) {
        Map<Integer, AnalyzeIt> anlMap = new HashMap<>();
        AnalyzeIt analyzeIt;
        Set<String> pickSet;
        String[]  winningNumbersArr =  winningNumbers.split(",");
        for (int i=0; i<aggregateNumbers.length; i++) {
            pickSet = new HashSet<>();
            analyzeIt = new AnalyzeIt();
            for (String s : winningNumbersArr) {
                String[] aggregateIndexArr = aggregateNumbers[i].split(" ");
                for (String s1 : aggregateIndexArr) {
                    if (s.trim().equals(s1.trim())) {
                        analyzeIt.setWinningNumbers(winningNumbers);
                        analyzeIt.setYourPicks(aggregateNumbers[i]);
                        analyzeIt.setGameType(gameType);
                        analyzeIt.setNumberMatched(Boolean.TRUE);
                        pickSet.add(s);
                        analyzeIt.setWinningNumbersDate(dateStr);
                        if (isNonLotto) {
                            if (winningNumbersArr[winningNumbersArr.length - 1].trim().equals(aggregateIndexArr[aggregateIndexArr.length - 1].trim())) {
                                analyzeIt.setPowerBallNumber(winningNumbersArr[winningNumbersArr.length - 1]);
                            }
                        }
                    }
                }
                if (pickSet.size() > 0)
                    analyzeIt.setMatchedNumbers(pickSet.toString());
            }
            anlMap.put(i, analyzeIt);
        }
        return executeResults(anlMap, aggregateNumbers.length, gameType);
    }

    /**
     *
     * @param execMap -
     * @param numberSize -
     * @return -
     */
    private String executeResults(Map<Integer, AnalyzeIt> execMap, int numberSize, String gameType) {
        // ...
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        sb.append("\n\t\t\t\t\t\t\t\tGame of Chance - Computer Simulation - Type: ").append(gameType).append("\n\n\n");
        sb.append("Total Aggregate tickets bought: ").append(numberSize);
        if (gameType.equals(LottoConstants.LOTTO)) {
           sb.append(" .... Amount paid: $").append(numberSize).append(".00\n");
        }
        else {
           sb.append(" .... Amount paid: $").append(numberSize * 2).append(".00\n");
        }
        sb.append(LineUtil.AMOUNT);

        sb.append(LineUtil.NUMBER_STAT);
        Map<Integer, Integer> matchMap = executeNumbers(execMap);
        final Set<Integer> keys = matchMap.keySet();
        for(final Integer ch : keys){
            if (!gameType.equalsIgnoreCase(LottoConstants.LOTTO)) {
                String str = (ch == 7) ? "power number(s)" : String.valueOf(ch);
                sb.append("Number of tickets matching ").append(str).append(" on winning ticket: ").append(matchMap.get(ch)).append("\n\n");
            }
            else {
                if (ch !=7 ) {
                    sb.append("Number of tickets matching ")
                            .append(ch).append(" number(s) on winning ticket: ")
                            .append(matchMap.get(ch)).append("\n\n");
                }
            }
        }
        // calculate estimated payoff amount ...
        sb = computePayoffAmount(matchMap, gameType, sb);

        sb.append(LineUtil.BREAKDOWN);

        for(int i=0; i<execMap.size(); i++) {
            AnalyzeIt analyzeIt = execMap.get(i);
            if (analyzeIt != null) {
                if (analyzeIt.isNumberMatched()) {
                    found = true;
                    sb.append("Winning ticket: [").append(analyzeIt.getWinningNumbers()).append("]").
                            append("         computer generated ticket: [").append(analyzeIt.getYourPicks()).append("]");
                    sb.append(" ... Matched number(s) ==> ").append(analyzeIt.getMatchedNumbers());
                    if (analyzeIt.getPowerBallNumber() != null) {
                        sb.append(" \t\t.... including power-number ==> ").append(analyzeIt.getPowerBallNumber());
                    }
                    sb.append(LineUtil.MATCHED_NUMBER);
                }
            }
        }
        if (!found) {
            return sb.append("There are no matching number(s) in your ticket!!").toString();
        }
        return sb.toString();
    }

    /**
     *
     * @param execMap -
     * @return -
     */
    private Map<Integer, Integer> executeNumbers(Map<Integer, AnalyzeIt> execMap) {
        // ...
        int match_0=0;
        int match_1=1;
        int match_2=2;
        int match_3=3;
        int match_4=4;
        int match_5=5;
        int match_6=6;
        int match_PB=7;

        int matchPB=0;

        Map<Integer, Integer> matchMap = new HashMap<>();
        for (int i=0; i<execMap.size(); i++) {
            AnalyzeIt analyzeIt = execMap.get(i);
            if (analyzeIt.getMatchedNumbers() != null && analyzeIt.getMatchedNumbers().length() > 0) {
                if (analyzeIt.getPowerBallNumber() != null) {
                    matchPB++;
                }
                int num = CommonUtil.getSetSize(analyzeIt.getMatchedNumbers());
                if (num == 1) {
                    if (matchMap.containsKey(match_1)) {
                        matchMap.put(match_1, matchMap.get(match_1) + 1);
                    } else {
                        matchMap.put(match_1, 1);
                    }
                }
                if (num == 2) {
                    if (matchMap.containsKey(match_2)) {
                        matchMap.put(match_2, matchMap.get(match_2) + 1);
                    } else {
                        matchMap.put(match_2, 1);
                    }
                }
                if (num == 3) {
                    if (matchMap.containsKey(match_3)) {
                        matchMap.put(match_3, matchMap.get(match_3) + 1);
                    } else {
                        matchMap.put(match_3, 1);
                    }
                }
                if (num == 4) {
                    if (matchMap.containsKey(match_4)) {
                        matchMap.put(match_4, matchMap.get(match_4) + 1);
                    } else {
                        matchMap.put(match_4, 1);
                    }
                }
                if (num == 5) {
                    if (matchMap.containsKey(match_5)) {
                        matchMap.put(match_5, matchMap.get(match_5) + 1);
                    } else {
                        matchMap.put(match_5, 1);
                    }
                }
                if (num == 6) {
                    if (matchMap.containsKey(match_6)) {
                        matchMap.put(match_6, matchMap.get(match_6) + 1);
                    } else {
                        matchMap.put(match_6, 1);
                    }
                }
            }
            else {
                if (matchMap.containsKey(match_0)) {
                    matchMap.put(match_0, matchMap.get(match_0) + 1);
                } else {
                    matchMap.put(match_0, 1);
                }
            }
        }
        matchMap.put(match_PB, matchPB);
        return matchMap;
    }

    private Map<Integer, Integer> getMatchMap(int match) {
        Map<Integer, Integer> matchMap = new HashMap<>();
        if (matchMap.containsKey(match)) {
            matchMap.put(match, matchMap.get(match) + 1);
        } else {
            matchMap.put(match, 1);
        }
        return matchMap;
    }
    /**
     * This method attemps to calculate the winning projections
     * per game type based on numbers and other metrics:
     * Lotto:
     *        6 numbers -------> Jackpot
     *        5 numbers -------> $2000.00
     *        4 numbers -------> $50.00
     *        3 numbers -------> $5.00
     *        2 numbers -------> $1.00
     *-------------------------------------------------------------------------
     * Mega:
     *        6 numbers (including powwer-number) ---------> Jackpot
     *        5 numbers -----------------------------------> $1,000,000.00
     *        5 numbers (including powwer-number)----------> $10,000.00
     *        4 numbers -----------------------------------> $500.00
     *        4 numbers (including powwer-number)----------> $200.00
     *        3 numbers -----------------------------------> $10.00
     *        3 numbers (including powwer-number)----------> $10.00
     *        2 numbers (including powwer-number)----------> $4.00
     *        1 number  (powwer-number)--------------------> $2.00
     *-----------------------------------------------------------------------------
     * Power-ball:
     *        6 numbers (including powwer-number) ---------> Jackpot
     *        5 numbers -----------------------------------> $1,000,000.00
     *        5 numbers (including powwer-number)----------> $50,000.00
     *        4 numbers -----------------------------------> $100.00
     *        4 numbers (including powwer-number)----------> $100.00
     *        3 numbers -----------------------------------> $7.00
     *        3 numbers (including powwer-number)----------> $7.00
     *        2 numbers (including powwer-number)----------> $4.00
     *        1 number  (powwer-number)--------------------> $4.00
     *-------------------------------------------------------------------------------
     *
     * @param matchMap -
     * @param gameType -
     */
    private StringBuilder computePayoffAmount(Map<Integer, Integer> matchMap, String gameType, StringBuilder sb) {
        double totalLottoAmount = 0.00d;
        double totalMegaAmount = 0.00d;
        double totalPowerBallAmount = 0.00d;

        String jackpotStr = null;
        boolean isjackpot = false;

        final Set<Integer> keys = matchMap.keySet();
        for (final Integer ch : keys) {
            if (gameType.equalsIgnoreCase(LottoConstants.LOTTO)) {
                double numbers = Double.valueOf(matchMap.get(ch));
                if (ch == 5) {
                    totalLottoAmount = totalLottoAmount + Double.valueOf(numbers * 2000.00);
                } else if (ch == 4) {
                    totalLottoAmount = totalLottoAmount + Double.valueOf(numbers * 50.00);
                } else if (ch == 3) {
                    totalLottoAmount = totalLottoAmount + Double.valueOf(numbers * 10.00);
                } else if (ch == 2) {
                    totalLottoAmount = totalLottoAmount + Double.valueOf(numbers * 4.00);
                } else if (ch == 6){
                    isjackpot = true;
                }
                else if (ch == 7) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 2.00);
                }
            } else if (gameType.equalsIgnoreCase(LottoConstants.MEGGA)) {
                double numbers = Double.valueOf(matchMap.get(ch));
                if (ch == 5) {
                    totalMegaAmount = totalMegaAmount + Double.valueOf(numbers * 1000000.00);
                } else if (ch == 4) {
                    totalMegaAmount = totalMegaAmount + Double.valueOf(numbers * 500.00);
                } else if (ch == 3) {
                    totalMegaAmount = totalMegaAmount + Double.valueOf(numbers * 7.00);
                } else if (ch == 2) {
                    totalMegaAmount = totalMegaAmount + Double.valueOf(numbers * 4.00);
                } else if (ch == 6) {
                    isjackpot = true;
                }
                else if (ch == 7) {
                    totalMegaAmount = totalMegaAmount + Double.valueOf(numbers * 4.00);
                }
            } else if (gameType.equalsIgnoreCase(LottoConstants.POWERBALL)) {
                double numbers = Double.valueOf(matchMap.get(ch));
                if (ch == 5) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 1000000.00);
                } else if (ch == 4) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 100.00);
                } else if (ch == 3) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 7.00);
                } else if (ch == 2) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 4.00);
                } else if (ch == 6) {
                    isjackpot = true;
                }
                else if (ch == 7) {
                    totalPowerBallAmount = totalPowerBallAmount + Double.valueOf(numbers * 4.00);
                }
            }
        }
        if (gameType.equalsIgnoreCase(LottoConstants.LOTTO)) {
            if (isjackpot) {
                if (totalLottoAmount > 0.00d) {
                    jackpotStr = "J A C K P O T + $" + totalLottoAmount+"0 ***\n";
                } else {
                    jackpotStr = "J A C K P O T!!!!!!!!!!!!!!\n";
                }
                sb.append("*** Your estimated total payoff winning amount is:  " + jackpotStr);
            }
            else {
                sb.append("*** Your estimated total payoff winning amount is: $" + totalLottoAmount+"0 ***\n");
            }
        }
        else if (gameType.equalsIgnoreCase(LottoConstants.POWERBALL)) {
            if (isjackpot) {
                if (totalPowerBallAmount > 0.00d) {
                    jackpotStr = "J A C K P O T + $" + totalPowerBallAmount+"0 ***\n";
                } else {
                    jackpotStr = "J A C K P O T!!!!!!!!!!!!!!\n";
                }
                sb.append("*** Your estimated total payoff winning amount is:  " + jackpotStr);
            }
            else {
                sb.append("*** Your estimated total payoff winning amount is: $" + totalPowerBallAmount+"0 ***\n");
            }
        }
        else if (gameType.equalsIgnoreCase(LottoConstants.MEGGA)) {
            if (isjackpot) {
                if (totalMegaAmount > 0.00d) {
                    jackpotStr = "J A C K P O T + $" + totalMegaAmount+"0 ***\n";
                } else {
                    jackpotStr = "J A C K P O T!!!!!!!!!!!!!!\n";
                }
                sb.append("*** Your estimated total payoff winning amount is:  " + jackpotStr);
            }
            else {
                sb.append("*** Your estimated total payoff winning amount is: $" + totalMegaAmount+"0 ***\n");
            }
        }
        return sb;
    }


}
