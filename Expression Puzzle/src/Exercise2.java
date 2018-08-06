import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Exercise2 {

	public static final int INFINITY = 1000000;
	public static Map<Integer, String> map = new HashMap<Integer, String>();

	public static void main(String[] args) {

		Exercise2 dp = new Exercise2();
		
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
		
		System.out.println(dp.startCall(arr1,11111));
		System.out.println(map.get(11111));
		if(false){
			// int n=148; 
		
		int n = 11111;
		int[] conc = new int[n + 1];
		int[] mul = new int[n + 1];
		int[] add = new int[n + 1];

		//int arr[]={1,4,7};
		int arr[] = {2,9 };

		//conc[0] = Exercise2.INFINITY;
		for (int i = 0; i < arr.length; i++) {
			if(n>=arr[i]){
			conc[arr[i]] = 1;
			String list = new String();
			list=""+arr[i];
			map.put(arr[i], list);
			}
		}
		for (int i = 0; i <= n; i++) {
			if (conc[i] != 1 && i<10)
				conc[i] = Exercise2.INFINITY;
			else if(conc[i] != 1 && i>=10)
				conc[i] = -1;
		}

		// mul[0]=Exercise2.INFINITY;
		for (int i = 0; i <= n; i++) {
			mul[i] = -1;
		}

		for (int i = 0; i <= n; i++) {
			add[i] = -1;
		}
		//System.out.println(map.get(n)==null);
		System.out.println(dp.add(n,arr, arr.length, conc, mul,add));

		
		//System.out.println(map.get(n)==null);
		System.out.println("hello");
		}

	}

	int startCall(int arr[], int n) {

		int[] conc = new int[n + 1];
		int[] mul = new int[n + 1];
		int[] add = new int[n + 1];

		for (int i = 0; i < arr.length; i++) {
			if (n >= arr[i]) {
				conc[arr[i]] = 1;
				String list = new String();
				list = "" + arr[i];
				map.put(arr[i], list);
			}
		}
		for (int i = 0; i <= n; i++) {
			if (conc[i] != 1 && i < 10)
				conc[i] = Exercise2.INFINITY;
			else if (conc[i] != 1 && i >= 10)
				conc[i] = -1;
		}

		// mul[0]=Exercise2.INFINITY;
		for (int i = 0; i <= n; i++) {
			mul[i] = -1;
		}

		for (int i = 0; i <= n; i++) {
			add[i] = -1;
		}

		return add(n, arr, arr.length, conc, mul, add);

	}

	int concat(int n, int arr[], int arr_size, int conc[]) {

		if (conc[n] != -1 || n < 10) {
			return conc[n];
		} else {

			int minValue = Exercise2.INFINITY, temp;
			for (int j = 10; j <= n; j++) {
				if (conc[j] > -1 && conc[j] < Exercise2.INFINITY)
					continue;
				else {
					temp = Exercise2.INFINITY;
					minValue = Exercise2.INFINITY;
					for (int i = 0; i < arr_size; i++) {

						// if(conc[i]>-1 && conc[i]<Exercise2.INFINITY){
						if (j % 10 == arr[i] || j / 10 == arr[i]) {
							temp = concat(j / 10, arr, arr_size, conc) + concat(j % 10, arr, arr_size, conc);
							if (temp > -1 && temp < Exercise2.INFINITY && temp < minValue) {
								minValue = temp;
								String list;
								if (map.get(j) == null) {
									list = new String();
									list = "" + j / 10 + j % 10;
									map.put(j, list);
								} else {
									list = map.get(j);
									list = "" + j / 10 + j % 10;
									map.put(j, list);
								}

							}

							/*
							 * if (temp > -1 && temp < minValue) minValue =
							 * temp;
							 */
						} /*
							 * else { if (!(conc[n] < -1 && conc[n] <
							 * Exercise2.INFINITY)) conc[n] =
							 * Exercise2.INFINITY; }
							 */
					}

					conc[j] = minValue;
				}
			}

			// }
			// conc[n] = minValue;
		}
		return conc[n];
	}

	int mul(int n, int arr[], int arr_size, int conc[], int mul[]) {

		// int[] min = new int[mul.length];
		int concatValue = concat(n, arr, arr_size, conc);
		if (concatValue < Exercise2.INFINITY && concatValue > -1) {
			// mul[n]=concatValue;
			return concatValue;
		}

		else if (mul[n] != -1)
			return mul[n];

		else {
			int minValue = Exercise2.INFINITY, temp = Exercise2.INFINITY, a, b;
			for (int i = 2; i < n; i++) {
				temp = Exercise2.INFINITY;
				if (conc[i] > -1 && conc[i] < Exercise2.INFINITY) {
					if (n % i == 0) {
						a = n / i;
						b = i;
						temp = mul(a, arr, arr.length, conc, mul) + mul(b, arr, arr.length, conc, mul) + 1;
						if (temp > -1 && temp < Exercise2.INFINITY && temp < minValue) {
							minValue = temp;
							String list, c, d;
							if (map.get(n) == null) {
								list = new String();
								c = map.get(a) == null ? "" + a : map.get(a);
								d = map.get(b) == null ? "" + b : map.get(b);
								list = c + "*" + d;
								map.put(n, list);
							} else {
								list = map.get(n);
								c = map.get(a) == null ? "" + a : map.get(a);
								d = map.get(b) == null ? "" + b : map.get(b);
								list = c + "*" + d;
								map.put(n, list);
							}

						}
						/*
						 * if (temp > -1 && temp < Exercise2.INFINITY) {
						 * minValue = temp; }
						 */
					}
				}
			}
			mul[n] = minValue;

			// modified//

		}
		return mul[n];

	}

	int add(int n, int arr[], int arr_size, int conc[], int mul[], int add[]) {

		int mulValue = mul(n, arr, arr_size, conc, mul);
		if (mulValue < Exercise2.INFINITY && mulValue > -1) {
			// mul[n]=concatValue;
			return mulValue;
		}

		else if (add[n] != -1)
			return add[n];

		else {

			int minValue = Exercise2.INFINITY, temp;
			for (int i = 1; i < n; i++) {
				temp = Exercise2.INFINITY;
				if (conc[i] > -1 && conc[i] < Exercise2.INFINITY) {
					if (n - i > 0) {
						temp = add(n - i, arr, arr_size, conc, mul, add) + add(i, arr, arr_size, conc, mul, add) + 1;
						if (temp > -1 && temp < Exercise2.INFINITY && temp < minValue) {
							minValue = temp;
							String list, a, b;
							if (map.get(n) == null) {
								list = new String();
								a = map.get(n - i) == null ? "" + (n - i) : map.get(n - i);
								b = map.get(i) == null ? "" + i : map.get(i);
								list = a + "+" + b;
								map.put(n, list);
							} else {
								list = map.get(n);
								a = map.get(n - i) == null ? "" + (n - i) : map.get(n - i);
								b = map.get(i) == null ? "" + i : map.get(i);
								list = a + "+" + b;
								map.put(n, list);
							}

						}
						/*
						 * if (temp > -1 && temp < Exercise2.INFINITY) {
						 * minValue = temp; }
						 */
					}
				}
			}
			for (int i = 1; i < n; i++) {
				temp = Exercise2.INFINITY;
				if (mul[i] > -1 && mul[i] < Exercise2.INFINITY) {
					if (n - i > 0) {
						temp = add(n - i, arr, arr_size, conc, mul, add) + add(i, arr, arr_size, conc, mul, add) + 1;
						if (temp > -1 && temp < Exercise2.INFINITY && minValue > temp) {
							minValue = temp;
							String list, a, b;
							if (map.get(n) == null) {
								list = new String();
								a = map.get(n - i) == null ? "" + (n - i) : map.get(n - i);
								b = map.get(i) == null ? "" + i : map.get(i);
								list = a + "+" + b;
								map.put(n, list);
							} else {
								list = map.get(n);
								a = map.get(n - i) == null ? "" + (n - i) : map.get(n - i);
								b = map.get(i) == null ? "" + i : map.get(i);
								list = a + "+" + b;
								map.put(n, list);
							}

						}
						/*
						 * if (temp > -1 && temp < Exercise2.INFINITY ) {
						 * minValue = temp; }
						 */
					}
				}
			}
			add[n] = minValue;

		}
		return add[n];
	}
}
