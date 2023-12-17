package com.jonevu.abi.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



public class DateUtil {
    /**
     * Returns Current Date as Timestamp Object
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentDate() {
        Calendar cal = new GregorianCalendar();
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Returns Current Date without MilliSecond as Timestamp Object
     *
     * @param aPTimestamp - Timestamp that needs to be updated with Zero Milliseconds
     * @return Timestamp - Current Timestamp
     * @throws Exception - Error Message
     */
    public static Timestamp getTSWithZeroMilliSecond(Timestamp aPTimestamp) throws Exception {
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTime(aPTimestamp);
            cal.set(Calendar.MILLISECOND, 0);            // set millis in second
            return new Timestamp(cal.getTime().getTime());
        } catch (Exception ex) {
            throw new Exception("Error in DateUtil.getTSWithZeroMilliSecond :: " + ex.getMessage());
        }
    }

    /**
     * Returns Current Date without MilliSecond as Timestamp Object
     *
     * @return Timestamp - Current Timestamp
     */
    public static Timestamp getCurrentDateWithZeroMilliSecond() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MILLISECOND, 0);            // set millis in second
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Returns Current Date with Zero timestamp for Hour, Minute, Second and Millisecond
     *
     * @return Timestamp - Current Timestamp with Zero Hour, Minute, Second and Millisecond
     */
    public static Timestamp getCurrentDateWithoutTS() {
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millis in second
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Returns Given Date with Zero timestamp for Hour, Minute, Second and Millisecond
     *
     * @param aPTimestamp - Object
     * @return - Returns Timestamp with Zero Hour, Minute, Second and Millisecond
     */
    public static Timestamp getDateWithoutTS(Timestamp aPTimestamp) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(aPTimestamp);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millis in second
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * This Method takes Date as input String and Converts to Date Format Returns given data in a Specified data format
     *
     * @param aDateStr    - Date String
     * @param aDateFormat - Date Format
     * @return Date
     * @throws ParseException - Error Message
     */
    public static Timestamp parseDate(String aDateStr, String aDateFormat) throws ParseException {
        if (aDateStr != null && aDateFormat != null) {
            GregorianCalendar returnDate = new GregorianCalendar();
            DateFormat dateFormat = new SimpleDateFormat(aDateFormat);
            returnDate.setTime(dateFormat.parse(aDateStr));
            return new Timestamp(returnDate.getTime().getTime());
        } else {
            return null;
        }
    }

    /**
     * This Method takes Date Object and Converts to Specified String Format Returns given data in a Specified data format
     *
     * @param aDateStr    - Date String
     * @param aDateFormat - Date Format
     * @return Date
     * @throws ParseException - Error Message
     */
    public static String formatDate(Date aDateStr, String aDateFormat) throws ParseException {
        if (aDateStr != null && aDateFormat != null) {
            DateFormat dateFormat = new SimpleDateFormat(aDateFormat);
            return dateFormat.format(aDateStr);
        } else {
            return null;
        }
    }


    /**
     * This Method Converts Current Date to Timestamp Format
     *
     * @param aDateFormat - Date Format
     * @return Date
     * @throws ParseException - Error Message
     */
    public static Timestamp getDateAsTimeStamp(Date aDateFormat) throws ParseException {
        if (aDateFormat != null) {
            return new Timestamp(aDateFormat.getTime());
        } else {
            return null;
        }
    }


    /**
     * This Method Converts Current Date to Specified String Format Returns given data in a Specified data format
     *
     * @param aDateFormat - Date Format
     * @return Date
     * @throws ParseException - Error Message
     */
    public static String formatCurrentDate(String aDateFormat) throws ParseException {
        if (aDateFormat != null) {
            GregorianCalendar now = new GregorianCalendar();
            Timestamp today = new Timestamp(now.getTime().getTime());
            DateFormat dateFormat = new SimpleDateFormat(aDateFormat);
            return dateFormat.format(today);
        } else {
            return null;
        }
    }

    /**
     * Returns the Timestamp with/with out zero min, hour, sec, ms based on Flag and add number of days to a month
     *
     * @return Timestamp - Current Timestamp
     */
    public static Timestamp getTSWithEndofDay(Timestamp aPTimestamp, Integer daysToAdd_Month, Boolean endOfDay) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(aPTimestamp);
        cal.add(Calendar.DAY_OF_MONTH, daysToAdd_Month);
        if (endOfDay) {
            cal.set(Calendar.HOUR_OF_DAY, 23); // set hour to midnight
            cal.set(Calendar.MINUTE, 59); // set minute in hour
            cal.set(Calendar.SECOND, 59); // set second in minute
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
            cal.set(Calendar.MINUTE, 0); // set minute in hour
            cal.set(Calendar.SECOND, 0); // set second in minute

        }
        cal.set(Calendar.MILLISECOND, 0); // set millis in second
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     *
     * @param aPTimestamp
     * @return
     */
    public static Timestamp getTSWithStartofWeek(Timestamp aPTimestamp) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(aPTimestamp);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Timestamp(cal.getTime().getTime());
    }

    /**
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
        XMLGregorianCalendar xgc = null;
        if (date == null) {
            return null;
        } else {
            try {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(date.getTime());

                xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
                xgc.setTime(DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED);
            } catch (DatatypeConfigurationException ex) {
                System.out.println("Failed to get instance of DatatypeFactory");
            }
            return xgc;
        }
    }

    /**
     * This Method Converts Timestamp to Specified String Format Returns the specified string format
     *
     * @param apDateTime - Timestamp Format
     * @param apStrFormat - Specified String Format
     * @return String - converted string format
     * @throws ParseException - Error Message
     */
    public static String getTimestampAsString(Timestamp apDateTime, String apStrFormat) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat(apStrFormat);
        return sdf1.format(apDateTime);
    }

    /**
     * @param dateTime
     * @param aDateFormat
     * @return
     * @throws ParseException
     */
    public static String formatTime(Timestamp dateTime, String aDateFormat) throws ParseException {
        if (dateTime != null && aDateFormat != null) {

            SimpleDateFormat dateFormatFrom = new SimpleDateFormat(aDateFormat);
            String formattedDate = dateFormatFrom.format(dateTime);
            String subStrTime = formattedDate.substring(11, 19);
            return subStrTime;
        } else {
            return null;
        }

    }

    /**
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Timestamp addDaytoTS(Timestamp date) throws ParseException {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            Date ts = cal.getTime();
            return new Timestamp(ts.getTime());
        } else {
            return null;
        }
    }

    /**
     * Updated as general utility function - JAN 06, 2014 - jevbu - Start
     * @param date
     * @return
     * @throws ParseException
     */
    public static Timestamp addDaytoTS(Timestamp date, int aPDays) throws ParseException {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, aPDays);
            Date ts = cal.getTime();
            return new Timestamp(ts.getTime());
        } else {
            return null;
        }
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean containsData(String str) {
        if (str != null && str.trim().length() > 0)
            return true;
        return false;
    }

    /**
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static boolean isValidDate(String dateStr, String format) {
        // ...
        boolean validDate = false;

        if (containsData(dateStr)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                validDate = (sdf.parse(dateStr, new ParsePosition(0)) != null);
            } catch (Exception e1) {
                validDate = false;
            }
        }
        return validDate;
    }

    /**
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date convertStrToDate(String dateString, String format) {
        // ...
        Date stringToDate = null;
        if (containsData(dateString)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                stringToDate = sdf.parse(dateString, new ParsePosition(0));
            } catch (Exception ex) {
                stringToDate = null;
            }
        }
        return stringToDate;
    }

    /**
     * 
     * @return
     */
    public static Date getTodaysDate() {
    	return convertStrToDate(new java.util.Date().toString(), "yyyy-MM-dd");
    }
    
    /**
     * Added as general utility function - JAN 06, 2014 - jevbu - End
     */
    /**
     *
     * @param aPTimestamp
     * @return
     */
    public static Timestamp getPrevYearTimestamp(Timestamp aPTimestamp) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(aPTimestamp);
        cal.add(Calendar.YEAR, -1);

        return new Timestamp(cal.getTime().getTime());
    }

    //Updated for DW1.5 - 7/18/2013 - bmani - START
    public static Long getDifferenceInDays(Date startingDate, Date endingDate) {
        long endingDtMillis = endingDate.getTime();
        long startingDtMillis = startingDate.getTime();
        long diffDays = 0;
        if(endingDtMillis > startingDtMillis){
            long difference = endingDtMillis - startingDtMillis;
            diffDays = difference/(1000*60*60*24);
        }
        return new Long(diffDays);
    }


    /**
     * Copied from PolicyWS
     * Add X days to the current date and reply with Date (no Time)
     */
    public final static Timestamp addToDate(int addThis){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.DATE, addThis);
        Timestamp ts = DateUtil.getDateWithoutTS(new Timestamp(cal.getTime().getTime()));
        return ts;
    }
    /**
     * Modified for DW2.0 - R2 (APR REL) - Feb 26, 2014 - bsinj – START
     *
     */
    public static Calendar getDateWithZeroSecond(Timestamp apTime) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(apTime);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND,0);// set millis in second
        return cal;
    }

    public static Date getModifiedDate(String dateStr, char ch, int value, String dateFormat) {
        // ...
        Calendar cal = Calendar.getInstance();
        // convert dateStr to date type ... (Note: RAD date format is: MM/dd/yyyy)
        java.util.Date dateVal = convertStrToDate(dateStr, dateFormat);
        cal.setTime(dateVal);
        char ch_ = ch;
        switch(ch_) {
            case 'd':	// day
            case 'D':
                cal.add(Calendar.DATE, value);
                break;
            case 'm':	// month
            case 'M':
                cal.add(Calendar.MONTH, value);
                break;
            case 'y':	// year
            case 'Y':
                cal.add(Calendar.YEAR, value);
                break;
            default:
                return null;
        }

        return cal.getTime();
    }

    /**
     *
     * @param dt
     * @param format
     * @return
     */
    public static String convertDateToStr(java.util.Date dt, String format) {
        // ...
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dt);
    }
}

