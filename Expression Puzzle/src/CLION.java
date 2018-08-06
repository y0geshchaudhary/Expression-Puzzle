import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CLION {
	
	static int n=11111;
	public static final int INFINITY = 1000000;
	public static String[] map = new String[n+1];
	static ArrayList<Integer> numbers = new ArrayList<>();
	static int[] numSize= new int[n+1];
	

	public static void main(String[] args) {

		CLION dp = new CLION();
		
		int[] arr1= {2,9};
		int[] arr2= {1,4,7};
		int[] arr3= {4,7};
		int[] arr4= {1,0};
		int[] arr5= {2,6};
		int[] arr6= {3,5,0};
		int[] arr7= {0,1,2,3,4,5,6,7,8,9};
		
		/*System.out.println(dp.startCall(arr1,229 ));
		System.out.println(dp.startCall(arr1,11 ));
		System.out.println(dp.startCall(arr1, 729));
		System.out.println(dp.startCall(arr1,49 ));
		System.out.println(dp.startCall(arr2,21 ));
		System.out.println(dp.startCall(arr3, 6));
		System.out.println(dp.startCall(arr1, 22));
		System.out.println(dp.startCall(arr4,0 ));
		System.out.println(dp.startCall(arr4,10 ));
		System.out.println(dp.startCall(arr4,1 ));
		System.out.println(dp.startCall(arr5,50 ));
		System.out.println(dp.startCall(arr6,121 ));
		System.out.println(dp.startCall(arr7,7854 ));*/
		
		System.out.println(dp.startCall(arr1,n));
		System.out.println(map[n]);

	}

	int startCall(int arr[], int n) {
		
		map = new String[n+1];
		
		for(int i=0;i<n+1;i++){
			numSize[i]=INFINITY;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (n >= arr[i]) {
				String list = new String();
				list = "" + arr[i];
				map[arr[i]]= list;
				numbers.add(arr[i]);
				numSize[arr[i]]=1;
				
			}
		}
		
		concatUpdated(n);
		mulUpdated(n);
		addUpdated(n);
		return numSize[n];

	}
	
	void concatUpdated(int n){
		int minValue = CLION.INFINITY, temp;
		String str=null;
		for (int j = 10; j <= n; j++) {
				temp = CLION.INFINITY;
				minValue = CLION.INFINITY;
				for (int i = 0; i < numbers.size(); i++) {
					// if(conc[i]>-1 && conc[i]<CLION.INFINITY){
					if (j % 10 == numbers.get(i) || j / 10 == numbers.get(i)) {
						temp = numSize[j%10]+numSize[j/10];
						if (temp > -1 && temp < CLION.INFINITY && temp < minValue) {
							minValue = temp;
							str=map[j/10]+map[j%10];
						}

					}
			}
				if(minValue!=INFINITY){
					numbers.add(j);
					numSize[j]=minValue;
					map[j]=str;
				}
				
		}

	}
	
	void mulUpdated(int n){
		int minValue = CLION.INFINITY, temp ,a,b;
		String str=null;
		for (int j = 2; j <= n; j++) {
				temp = CLION.INFINITY;
				minValue = CLION.INFINITY;
				for (int i = 0; i < numbers.size(); i++) {
					if( numbers.get(i)>1 && numbers.get(i)<j && j%numbers.get(i)==0){
						a=numSize[j/numbers.get(i)];
						b=numSize[numbers.get(i)];
						temp=a+b+1;
						if (temp > -1 && temp < CLION.INFINITY && temp < minValue) {
							minValue = temp;
							str=map[j/numbers.get(i)]+"*"+map[numbers.get(i)];
						}
					}
				}
				if(minValue!=INFINITY && minValue<numSize[j]){
					numbers.add(j);
					numSize[j]=minValue;
					map[j]=str;
				}
			}				
	}

	void addUpdated(int n){
		int minValue = CLION.INFINITY, temp ,a,b;
		String str=null;
		for (int j = 2; j <= n; j++) {
				temp = CLION.INFINITY;
				minValue = CLION.INFINITY;
				for (int i = 0; i < numbers.size(); i++) {
					if(j-numbers.get(i)>0 && numSize[j-numbers.get(i)]<INFINITY && numSize[j-numbers.get(i)]>-1){
						a=numSize[j-numbers.get(i)];
						b=numSize[numbers.get(i)];
						temp=a+b+1;
						if (temp > -1 && temp < CLION.INFINITY && temp < minValue) {
							minValue = temp;
							str=map[j-numbers.get(i)]+"+"+map[numbers.get(i)];
						}
					}
				}
				if(minValue!=INFINITY && minValue<numSize[j]){
					numbers.add(j);
					numSize[j]=minValue;
					map[j]=str;
				}
			}				
	}
}
