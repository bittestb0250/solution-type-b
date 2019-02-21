package problem04;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		//시작 지점
		int location = -1;
		String[] map = createMap();
		char input;
		int move;
		long start,end;
		List<User> list = new ArrayList<>();
		User user = new User();
		start = System.currentTimeMillis();
		while(true) {
			//입력
			reader.skip(2);
			input = (char)reader.read();
			
			user.countall++;
			// 스페이스바 or 엔터 일 때
			if(input == '\n' || input ==' ') {
				move = (int)(Math.random()*4+1);
				System.out.println(move);
				location += move;
				if(location > map.length) {
					break;
				}
				if(map[location].equals("#")) {
					user.replay++;
					map = createMap();
					location = -1;
				}
			}
			for(String s: map) 
				System.out.print(s);
			System.out.println();
			for(int j=0;j<location;j++)
				System.out.print(" ");
			System.out.println("^");
		
		}
		end = System.currentTimeMillis();
		long time = (end-start)/1000;
		user.playTime = time;
		list.add(user);
		list.sort(Comparator.comparing(User::getCountall).thenComparing(User::getReplay).thenComparing(User::getPlayTime));
		list.stream().forEach(u->{
			System.out.println(u.getCountall()+"/"+u.getReplay()+"/"+u.getTime());
		});
	}

	public static String[] createMap() {
		List<String> list = new ArrayList<>();
		String[] answer;
		String[] str = {"#","O"};
		int[] count = {0,0};
		
		while(!(count[0]>=10 && count[1]>=10)) {
			
			int select = (int)(Math.random()*2);
			count[select]++;
			
			list.add(str[select]);
		}
		answer = new String[list.size()];
		list.toArray(answer);
		return answer; 
	}
	
	static class User{
		int countall=0;
		int replay = 0;
		long playTime = 0;
		
		public int getCountall() {
			return countall;
		}
		public void setCountall(int countall) {
			this.countall = countall;
		}
		public int getReplay() {
			return replay;
		}
		public void setReplay(int replay) {
			this.replay = replay;
		}
		public Long getPlayTime() {
			return playTime;
		}
		public void setPlayTime(Long playTime) {
			this.playTime = playTime;
		}
		public String getTime() {
			long m = playTime/60;
			long s = playTime%60;
			return ""+m+" "+s;
		}
		
	}

}
