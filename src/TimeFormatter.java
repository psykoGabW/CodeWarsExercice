import java.util.ArrayList;

public class TimeFormatter {

	private enum TimeSequences {

		/*
		 * Declaration of time sequence in descendant order !
		 * Will be useful during parsing time sequences for seconds amount division.
		 */
		YEAR("year", 31536000),
		DAY("day", 24 * 3600),
		HOUR("hour", 3600),
		MINUTE("minute", 60),
		SECOND("second", 1);

		private String label; // label to use for a TimeSequence
		private int secondQuantity; // Number of seconds to define the TimeSequence

		TimeSequences(String label, int secondQuantity) {
			this.label = label;
			this.secondQuantity = secondQuantity;
		}

		public String getLabel() {
			return this.label;
		}
		
		/**
		 * 
		 * @param tsQuantity Quantity of time sequence for which we want to get label
		 * @return label corresponding at the quantity of time sequences.
		 */
		public String getLabel(int tsQuantity) {
			String result = getLabel();

			if (tsQuantity>0)
			{
				result =  ((String) String.valueOf(tsQuantity)).concat(" "+result);
				if (tsQuantity>1) {
					result+="s";
				}
			}			
			
			return result;
		}

		/**
		 * 
		 * @param secondsAmount from which we're searching number of full time sequence
		 * @return number of full time sequence
		 */
		public int getNumberOfTimeSequence(int secondsAmount) {
			return (secondsAmount / secondQuantity);
		}

		/**
		 * 
		 * @param secondAmount from which we will subtract a given number of time sequence
		 * @param nbUnit : Quantity of time sequence to subtract 
		 * @return amount of seconds left after time sequence subtract
		 */
		public int getSecondsLeft(int secondAmount, int nbUnit) {
			return (secondAmount - (nbUnit * secondQuantity));
		}

	}

	public static String formatDuration(int seconds) {
		// your code goes here

		String result = "";

		if (seconds == 0) {
			// 0 seconds.... Now
			result = "now";
		} else {
			
			ArrayList<String> timeSeqDivision = new ArrayList<String>();
			for (TimeSequences e : TimeSequences.values()) {
				// for each time sequence (descendant order) 
				int timeSeqQuantity = e.getNumberOfTimeSequence(seconds); // get of quantity of time sequence in seconds Amount
				if (timeSeqQuantity > 0) {
					timeSeqDivision.add(e.getLabel(timeSeqQuantity)); // add of time sequence label (regarding time sequence quantity) in list
					seconds = e.getSecondsLeft(seconds, timeSeqQuantity); // subtract of time sequence quantity of seconds amount 
				}
			}

			int itTimeSeq = 0;

			// Construction of formated duration to return 
			while (itTimeSeq < timeSeqDivision.size()) {
				if (!result.isEmpty()) {
					// Previous time sequence in result -> add of a separator
					if (itTimeSeq == timeSeqDivision.size() - 1) {
						// Last time sequence -> separator =   " and "
						result += " and ";
					} else {
						// More time sequences left -> separator = ", "
						result += ", ";
					}
				}
				// concatenation of the current time sequence with previous result
				result += timeSeqDivision.get(itTimeSeq);
				itTimeSeq++;
			}

		}

		return result;
	}

}
