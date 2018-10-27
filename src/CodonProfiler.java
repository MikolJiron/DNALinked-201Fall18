import java.util.*;

public class CodonProfiler {

	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 *
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in
	 * strand.
	 * This has been modified to be used with a HashMap, which improves time performance
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String, Integer> map = new HashMap<>();

		int[] ret = new int[codons.length];

		int strLength = strand.toString().length();

		Iterator<Character> iter = strand.iterator();

		for (int k = 0; k < strLength; k += 3) {
			//create the key
			String cod = "";

			char a = iter.next();
			char b = 'z';
			char c = 'z';
			if (iter.hasNext()) {
				b = iter.next();
			}
			if (iter.hasNext()) {
				c = iter.next();
			}
			cod = "" + a + b + c;


			//create value
			int value = 0;

			if (map.containsKey(cod)) {//updates the key/value pair
				value = map.get(cod);
				value++;
				map.put(cod, value);
			}

			else {//puts a brand new key/value pair into the map
				value++;
				map.put(cod, value);
			}

		}

		for (int k = 0; k < codons.length; k++) {
			if (map.containsKey(codons[k])) {
				ret[k] = map.get(codons[k]);
			} else
				ret[k] = 0;
		}
		return ret;
	}
}

