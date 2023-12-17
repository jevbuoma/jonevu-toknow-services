/**
 * 
 */
package com.jonevu.abi.profile.conversion;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author jonev
 *
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {
	
	@Override
    public Timestamp unmarshal(Date date) {
        return new Timestamp(date.getTime());
    }
	
	@Override
    public Date marshal(Timestamp ts) {
        return new Date(ts.getTime());
    }
}
