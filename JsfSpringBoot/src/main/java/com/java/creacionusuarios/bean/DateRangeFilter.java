
package com.java.creacionusuarios.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ApplicationScoped
public class DateRangeFilter implements Serializable {

	private static final Logger LOG = LoggerFactory.getLogger(DateRangeFilter.class.getName());
	


	public boolean filterByDate(Object value, Object filter, Locale locale) {

		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.isEmpty()) {
			return true;
		}
		if (value == null) {
			return false;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date filterDate = (Date) value;
		Date dateFrom;
		Date dateTo;
		int i=filterText.length();
		String[] a=filterText.split(",");
		try {
			String fromPart =a[0].substring(1);
			String toPart =  a[1].substring(1);
			dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
			dateTo = toPart.isEmpty() ? null : df.parse(toPart);
		} catch (ParseException pe) {
			LOG.error( "unable to parse date: " + filterText, pe);
			return false;
		}

		return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));
	}


	
	
}