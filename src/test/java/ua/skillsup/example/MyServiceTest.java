package ua.skillsup.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class MyServiceTest {

	@Mock
	private WorkDaysService workDaysService;
	@Mock
	private WeekEndService weekEndService;
	@Mock
	private DayOfWeekService dayOfWeekService;

	private MyService service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.service = new MyService(workDaysService, weekEndService, dayOfWeekService);
	}

	@Test
	public void testExample__work_days__monday() {
		//GIVEN
		final Account account = new Account(15L);
		final BigDecimal expected = BigDecimal.valueOf(100500.87);

		when(workDaysService.balance(eq(account.getId())))
				.thenReturn(expected);
		when(dayOfWeekService.currentDayOfWeek()).thenReturn(DayOfWeek.MONDAY);

		//WHEN
		BigDecimal actual = service.calculateMoney(account);

		//THEN
		assertThat(actual).isEqualTo(expected.toString());
	}

}