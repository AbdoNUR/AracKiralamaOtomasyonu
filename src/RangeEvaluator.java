import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.IDateEvaluator;

public class RangeEvaluator implements IDateEvaluator{
private DateUtil dateutil = new DateUtil();
private  ArrayList<java.sql.Date> list = new ArrayList<>();
	public void add(Date date) {
		
		list.add( (java.sql.Date) date);
	}
	public void remove(Date date) {
		list.remove(date);
		
	}
	public void setDates(ArrayList<java.sql.Date> dates) {
		list.clear();
		list.addAll(dates);
		
	}
@Override
	public Color getInvalidBackroundColor() {
		// TODO Auto-generated method stub
		return Color.RED;
	}

	@Override
	public Color getInvalidForegroundColor() {
		// TODO Auto-generated method stub
		return Color.RED;
	}

	@Override
	public String getInvalidTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getSpecialBackroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getSpecialForegroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSpecialTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvalid(Date date) {
		// TODO Auto-generated method stub
		return list.contains(date);
	}

	@Override
	public boolean isSpecial(Date date) {
		// TODO Auto-generated method stub
		return false;
	}
	public  void setStartDate(Date startdate) {
		dateutil.setMinSelectableDate(startdate);
		
	}
	public Date getStartDate() {
		
		return  dateutil.getMinSelectableDate();
	}
	public void setEndDate(Date enddate) {
		
		dateutil.setMaxSelectableDate(enddate);
		
	}
	public Date getEndDate() {
		return dateutil.getMaxSelectableDate();
		
	}

}
