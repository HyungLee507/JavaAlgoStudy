import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> stations = new HashMap<>();
		Map<String, Integer> features = new HashMap<>();
		Map<Integer, Set<String>> featureToStations = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			String station = br.readLine();
			stations.put(station, 0);
		}
		
		int R = Integer.parseInt(br.readLine());
		
		int offset = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			if(cmd == 'U') {
				String station = st.nextToken();
				String feature = st.nextToken();
				st = new StringTokenizer(feature, ",");
				int updatedFeature = stations.get(station);
				
				while(st.hasMoreTokens()) {
					String f = st.nextToken();
					int idx;
					if(features.containsKey(f)) {
						idx = features.get(f);
					} else {
						idx = offset;
						features.put(f, offset++);
					}
					updatedFeature |= (1 << idx);
					featureToStations.putIfAbsent(1 << idx, new HashSet<>());
					featureToStations.get(1 << idx).add(station);
				}
				stations.put(station, updatedFeature);
			} else {
				String feature = st.nextToken();
				st = new StringTokenizer(feature, ",");
				int searchFeatures = 0;
				boolean foundInvalidFeature = false;
				
				while(st.hasMoreTokens()) {
					String f = st.nextToken();
					if(!features.containsKey(f)) {
						foundInvalidFeature = true;
						break;
					}
					int idx = features.get(f);
					searchFeatures |= (1 << idx);
				}
				
				if(foundInvalidFeature) {
					sb.append("0\n");
					continue;
				}
				
				int cnt = 0;
				Set<String> candidateStations = new HashSet<>();
				boolean firstFeature = true;
				
				for(int j = 0; j < offset; j++) {
					if((searchFeatures & (1 << j)) > 0) {
						if(!featureToStations.containsKey(1 << j)) {
							cnt = 0;
							break;
						}
						if(firstFeature) {
							candidateStations.addAll(featureToStations.get(1 << j));
							firstFeature = false;
						} else {
							candidateStations.retainAll(featureToStations.get(1 << j));
						}
					}
				}
				sb.append(candidateStations.size()).append("\n");
			}
		}
		System.out.println(sb);
	}
}
