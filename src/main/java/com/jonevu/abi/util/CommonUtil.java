/**
 * 
 */
package com.jonevu.abi.util;

import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.Random;

import javax.xml.bind.JAXB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author jonev
 *
 */
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);


	/**
	 * 
	 * @return
	 */
	public static Long generateRandomNumber() {
		return Math.abs(new Random().nextLong());
	}
	
	/**
	 * 
	 * @param digits
	 * @return
	 */
    public static String getRandomNumber(int digits) 
    {
        String fmtStr = "#."+charPad(null, digits, "0", "l");
        DecimalFormat df = new DecimalFormat(fmtStr);
        double randNum = Math.random();
        String retStr = df.format(randNum);
        if (retStr.equals("1.00")) {
           retStr = ".99";
        }
        int fmtNum = CommonUtil.formatNumber(retStr);
        return String.valueOf(fmtNum);
    }
    
	
    /**
     * 
     * @param num
     * @return
     */
    public static int formatNumber(String num) {		
        // remove decimal and leading zero...
        String str = null;		
        if (num.equals(".00"))
                num = ".0";
        // ...
        str = num.substring(1);	       
        int inum = Integer.parseInt(str);
        return inum;		
    }

    /**
     * 
     * @param inStr
     * @param size
     * @param ch
     * @param dir
     * @return
     */
    public static String charPad(String inStr, int size, String ch, String dir) {
        // ...
        String data = null;

        if (inStr == null) inStr = "";
        int strlen = inStr.length();
        // No need to pad if instr length in the same as size...
        if (strlen == size) {
            return inStr;
        }
        // Truncate if instr length is greater than size...
        else if (strlen > size) {
                return inStr.substring(0, size);
        }
        StringBuilder sb = new StringBuilder();
        int len = size - inStr.length();

        for (int i=0; i<len; i++) {
            sb.append(ch);
        }
        if (dir.equalsIgnoreCase("l")) {
            sb.append(inStr);
            data = sb.toString();
        }
        else if(dir.equalsIgnoreCase("r")) {
            data = inStr + sb.toString();
        }
        return data;
    }
    
    /**
     * 
     * @param msgType
     * @return
     * @throws JsonProcessingException 
     */
    public static Object convertToXmlJson(Object obj, String msgType) throws JsonProcessingException {
    	String str = null;
    	if (msgType.equalsIgnoreCase("xml")) {
	    	StringWriter sw = new StringWriter();
	        XmlMapper xmlMapper = new XmlMapper();
	        xmlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	        str = xmlMapper.writeValueAsString(obj);	    	
	    	JAXB.marshal(obj, sw);
	    	str = sw.toString();
    	}
    	else {
    		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    		str = ow.writeValueAsString(obj);    		
    	}
    	return str;
    }
        
    /**
     * 
     * @param sb -
     * @return -
     */
    public static String formatStringData(StringBuilder sb) {    	
    	// Note: Each incoming sb data is separated by '\n'
    	// and actual data set is formated as [1, 2, 3, 4, 5, 6, ...].
    	// We need to reformat data as: 01 02 03 04 05 06 with each set
    	// separated by '\n'
    	StringBuilder sbb = new StringBuilder("");
    	String[] dataSet = sb.toString().split("\n");
    	for (int i=0; i<dataSet.length; i++) {
    		dataSet[i] = dataSet[i].replaceAll("[\\[\\]]", "");
            sbb = parseStringData(dataSet[i], ",");
    	}
    	return sbb.toString();
    }

    public static StringBuilder parseStringData(String delimStr, String delim) {
        StringBuilder sb = new StringBuilder();
        String[] commaset = delimStr.split(delim);
        for (int j=0; j<commaset.length; j++) {
            sb.append(CommonUtil.charPad(commaset[j].trim(), 2, "0", "l")).append(" ");
            if (j == commaset.length-1)
                sb.append("\n");
        }
        return sb;
    }

    /**
     * setStrVal comes in as [1,2,3,4,5....]. Parse and return size ..
     * @param setStrVal -
     * @return -
     */
    public static Integer getSetSize(String setStrVal) {
        String dataSet = setStrVal.trim().replaceAll("[\\[\\]]", "");
        return dataSet.trim().split(",").length;
    }
}
