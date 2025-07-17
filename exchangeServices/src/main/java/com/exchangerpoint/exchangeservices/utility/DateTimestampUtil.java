package com.exchangerpoint.exchangeservices.utility;

import java.sql.Timestamp;
import java.util.GregorianCalendar;

import com.exchangerpoint.exchangeservices.exception.DateTimestampException;

public class DateTimestampUtil {

	/**
	 * Gets current timestamp
	 * @return current timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new GregorianCalendar().getTime().getTime());
	}
	
	/**
	 * Gets timestamp before/after current timestamp, as supplied.
	 * 
	 * @param timeUnit unit of time. Possible values are GregorianCalendar.MILLISECOND, GregorianCalendar.SECOND,
	 * GregorianCalendar.MINUTE, GregorianCalendar.HOUR, GregorianCalendar.MONTH, GregorianCalendar.YEAR
	 * @param unitCount count of time before/after the current timestamp, as required.
	 * @return timestamp before/after current timestamp
	 * @throws DateTimestampException
	 */
	public static Timestamp getTimestampFromCurrentTime(int timeUnit, int unitCount) throws DateTimestampException {
		
		if (!(timeUnit == GregorianCalendar.MILLISECOND || timeUnit == GregorianCalendar.SECOND || timeUnit == GregorianCalendar.MINUTE
				|| timeUnit == GregorianCalendar.HOUR || timeUnit == GregorianCalendar.MONTH || timeUnit == GregorianCalendar.YEAR))
			throw new DateTimestampException("Invalid unit of time: " + timeUnit);
		
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.add(timeUnit, unitCount);
		return new Timestamp(gcal.getTime().getTime());
	}
}
