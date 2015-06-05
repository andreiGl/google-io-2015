import java.util.ArrayList;
import java.util.List;

public class MeetingSequence {
	static int START = 0;
	static int END = 1;
	
	public static List<Integer> getLongestSeq(double[][] meetings) {
		List<Integer> resultIdxs = new ArrayList<Integer>();
		List<Integer> processedIdxs = new ArrayList<Integer>();

		int earliestIdx = -1;
		do { 
			earliestIdx = findEarliestEnding(meetings, processedIdxs);
			if(earliestIdx==-1)
				break;
			removeOverlapping(meetings, processedIdxs, earliestIdx);
			resultIdxs.add(earliestIdx);
		} while(earliestIdx > -1);
		
		return resultIdxs;
	}
	
	private static int findEarliestEnding(double[][] meetings, List<Integer> processedIdxs) {
		int idx = -1;
		
		for(int i = 0; i < meetings.length; i++) {
			if(processedIdxs.contains(i))
				continue;
		
			if(idx < 0) {
				idx = i; 
				continue;
			}			
			
			if(meetings[i][END] < meetings[idx][END]) {
				idx = i;
			}	
		}
			
		processedIdxs.add(idx);
		return idx;
	}
	
	private static void removeOverlapping(double[][] meetings, List<Integer> processedIdxs, int idx) {
		for(int i = 0; i < meetings.length; i++) {
			if(processedIdxs.contains(i))
				continue;
		
			if(meetings[i][START] < meetings[idx][END]) {
				processedIdxs.add(i);
			}	
		}
	}
	
	public static void main(String[] args) {
		double[][] meetings = {
			{8,15},
			{8.5,9.5},
			{9,11},
			{11,12},
			{14,16}};
		
		List<Integer> longest = MeetingSequence.getLongestSeq(meetings);
		StringBuffer resStr = new StringBuffer("Longest sequence: ");
		for(int i = 0; i < longest.size(); i++) {
			resStr.append(longest.get(i)+1);
			if(i+1<longest.size())
				resStr.append(", ");
		}
		resStr.append(".");

		System.out.println(resStr);
	}
}
