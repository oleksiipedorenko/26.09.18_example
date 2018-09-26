package ua.skillsup.example;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class MyService {

	private final WorkDaysService workDaysService;
	private final WeekEndService weekEndService;
	private final DayOfWeekService dayOfWeekService;

	public MyService(WorkDaysService workDaysService,
	                 WeekEndService weekEndService, DayOfWeekService dayOfWeekService) {
		this.workDaysService = workDaysService;
		this.weekEndService = weekEndService;
		this.dayOfWeekService = dayOfWeekService;
	}

	/**
	 * Verify current account balance
	 *
	 * Note: balances could be different depends on week day
	 *
	 * @param account an account to check
	 * @return
	 */
	BigDecimal calculateMoney(Account account) {
		switch (dayOfWeekService.currentDayOfWeek()) {
			case SATURDAY:
			case SUNDAY:
				return weekEndService.balance(account.getId());
			default:
				return workDaysService.balance(account.getId());

		}
	}
}