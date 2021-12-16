import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

public class DataVerifier
{
	private int startDay;
	private int startMonth;
	private int startYear;
	private int endDay;
	private int endMonth;
	private int endYear;
	private String[] endDate;
	private String[] startDate;

	private final static int MAX_DAY = 31;
	private final static int MAX_MONTH = 12;
	private final static int MAX_YEAR = 3000;
	private final static int MIN_YEAR = 1000;

	/**
	 * constructor of DataVerifier using two strings as parameters which are then
	 * split and set as endDate and startDate, the day in the 1st position, month
	 * in 2nd and year in 3rd
	 * 
	 * @param start
	 * @param end
	 */
	public DataVerifier(String start, String end)
	{
		startDate = start.split("/");
		endDate = end.split("/");
	}

	/**
	 * tries to set the other fields of Dataverifier by going through startDate
	 * and endDate and converting them into ints 
	 * if there are any errors in the
	 * process, such as there being nothing to convert, a number format exception
	 * is thrown with the appropriate message attached
	 * 
	 */
	public void formatCheck()
	{

		try
		{
			startDay = Integer.parseInt(startDate[0]);
			startMonth = Integer.parseInt(startDate[1]);
			startYear = Integer.parseInt(startDate[2]);
		} catch (NumberFormatException x)
		{
			throw new NumberFormatException(
			      "Start Date: only digits are allowed\n  D, M, and Y are digits in DD/MM/YYYY ");
		}
		try
		{
			endDay = Integer.parseInt(endDate[0]);
			endMonth = Integer.parseInt(endDate[1]);
			endYear = Integer.parseInt(endDate[2]);
		} catch (NumberFormatException x)
		{
			throw new NumberFormatException(
			      "End Date: only digits are allowed\n  D, M, and Y are digits in DD/MM/YYYY ");
		}
	}

	/**
	 * goes through the startDay, startMonth and startYear fields to check if
	 * they are appropriate 
	 * if there are any errors such as going out of bounds,
	 * a DateTimeException is thrown with a corresponding message.
	 */
	public void startCheck()
	{
		if (startMonth < 0)
		{
			throw new DateTimeException("Month cannot be negative");
		}

		if (startMonth > MAX_MONTH)
		{
			throw new DateTimeException("Month exceeds maximum possible: "
			      + MAX_MONTH);
		}

		if (startDay < 0)
		{
			throw new DateTimeException("Date cannot be negative");
		}
		if ((startMonth == 4) || (startMonth == 6) || (startMonth == 9)
		      || (startMonth == 11))
		{

			if (startDay > MAX_DAY - 1)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month: " + (MAX_DAY
				            - 1));
			}
		} else if (startMonth == 2)
		{
			if (startDay > MAX_DAY - 3)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month: " + (MAX_DAY
				            - 3));
			}

		} else
		{
			if (startDay > MAX_DAY)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month: " + MAX_DAY);

			}
		}
		if (startYear < MIN_YEAR)
		{
			throw new DateTimeException("Start year is below the minimum accepted:"
			      + MIN_YEAR);
		}
		if (startYear > MAX_YEAR)
		{
			throw new DateTimeException("Stat year is above the maximum accepted:"
			      + MAX_YEAR);
		}

	}

	/**
	 * goes through the endDay, endMonth and endYear fields to check if they are
	 * appropriate if there are any errors such as going out of bounds, a
	 * DateTimeException is thrown with a corresponding message.
	 */
	public void endCheck()
	{
		if (endMonth < 0)
		{
			throw new DateTimeException("Month cannot be negative");
		}

		if (endMonth > MAX_MONTH)
		{
			throw new DateTimeException("Month exceeds maximum possible: "
			      + MAX_MONTH);
		}

		if (endDay < 0)
		{
			throw new DateTimeException("Date cannot be negative");
		}
		if ((endMonth == 4) || (endMonth == 6) || (endMonth == 9)
		      || (endMonth == 11))
		{

			if (endDay > MAX_DAY - 1)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month:" + (MAX_DAY
				            - 1));
			}
		} else if (endMonth == 2)
		{
			if (endDay > MAX_DAY - 3)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month: " + (MAX_DAY
				            - 3));
			}

		} else
		{
			if (endDay > MAX_DAY)
			{
				throw new DateTimeException(
				      "Date exceeds maximum possible for that month: " + MAX_DAY);

			}
		}
		if (endYear < MIN_YEAR)
		{
			throw new CPSC1181Exception("End year is below the minimum accepted:"
			      + MIN_YEAR);
		}
		if (endYear > MAX_YEAR)
		{
			throw new CPSC1181Exception("End year is above the maximum accepted:"
			      + MAX_YEAR);
		}

	}

	/**
	 * calculates the difference between the startDate and endDate in terms of
	 * year,month, and day, as well as day alone
	 * 
	 * generates a popup window with the differences formatted in a message
	 */
	public void dateDifference()
	{
		LocalDate start = LocalDate.of(startYear, startMonth, startDay);
		LocalDate end = LocalDate.of(endYear, endMonth, endDay);
		long numberOfDays = ChronoUnit.DAYS.between(start, end);

		Period duration = Period.between(start, end);
		JOptionPane.showMessageDialog(null, "" + duration.getDays() + " days\n"
		      + duration.getMonths() + " months\n" + duration.getYears()
		      + " years\n" + numberOfDays + ": Total number of days");
	}
}
