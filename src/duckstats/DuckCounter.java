package duckstats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 
 * This class is used for storing the number of ducks and ducklings per pond.
 * You may modify this class as long as it will not break the supplied code.
 * 
 * @author Wen 
 *
 */
public class DuckCounter implements Comparable<DuckCounter> {
	private LocalDate date;
	private int ducks;
	private int ducklings;

	public DuckCounter(String date, int ducks, int ducklings) throws DuckCountException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			this.date = LocalDate.parse(date, formatter);
			this.ducks = ducks;
			this.ducklings = this.ducklings;
		} catch (DateTimeParseException e) {
			throw new DuckCountException("Error in duck data!");
		}
	}

	public LocalDate getDate() {
		return date;
	}

	public int getDuckCount() {
		return ducks;
	}

	public int getDucklingCount() {
		return ducklings;
	}


	@Override
	public int compareTo(DuckCounter other) {
		if (this.ducks > other.getDuckCount()) {
			return -1;
		} else if (this.ducks < other.getDuckCount()) {
			return 1;
		}
		return 0;
	}
}
