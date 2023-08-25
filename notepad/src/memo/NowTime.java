package memo;

import java.util.Calendar;

public class NowTime {

	public String Times() {
		// TODO Auto-generated method stub
		Calendar time_day = Calendar.getInstance();

		String time_string = "";

		int dayOfWeek = time_day.get(Calendar.DAY_OF_WEEK);
		String week = "";

		switch(dayOfWeek)
		{
		case Calendar.SUNDAY:
			week = "     일요일";
			break;
		case Calendar.MONDAY:
			week = "     월요일";
			break;
		case Calendar.TUESDAY:
			week = "     화요일";
			break;
		case Calendar.WEDNESDAY:
			week = "     수요일";
			break;
		case Calendar.THURSDAY:
			week = "     목요일";
			break;
		case Calendar.FRIDAY:
			week = "     금요일";
			break; 
		case Calendar.SATURDAY:
			week = "     토요일";
			break;
		}time_string=week+" ";

		if(time_day.get(Calendar.AM_PM)==1)//오전 0 오후 1
		{time_string += "오후 ";
		}
		else
			time_string += "오전 ";
		if (time_day.get(Calendar.HOUR) == 0) //Calendar는 12시를 0시로 출력함. 
			time_string += "12: ";
		else
			time_string += time_day.get(Calendar.HOUR) + "시 ";
		if(time_day.get(Calendar.MINUTE) < 10)
			time_string += "0";
		time_string += time_day.get(Calendar.MINUTE) + "분 ";
		time_string += time_day.get(Calendar.YEAR) + "년" + (time_day.get(Calendar.MONTH)+1) + "월" //Calendar.Month는 0부터 시작
				+ time_day.get(Calendar.DAY_OF_MONTH) + "일";
		
		return time_string;
	}
}