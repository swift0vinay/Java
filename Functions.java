import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Functions {

	static int INF = (int) 1e9 + 7;

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Lower Bound & Upper Bound

	static int lowerBound(int[] ar, int val) {
		int l = -1, r = ar.length;
		while (l + 1 < r) {
			int mid = (l + r) >>> 1;
			if (ar[mid] >= val)
				r = mid;
			else
				l = mid;
		}
		return r;
	}

	static int upperBound(int[] ar, int val) {
		int l = -1, r = ar.length;
		while (l + 1 < r) {
			int mid = (l + r) >>> 1;
			if (ar[mid] <= val)
				l = mid;
			else
				r = mid;
		}
		return l + 1;
	}
	
////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// SEGMENT TREE

	static class SegmentTree {
		int st[];

		public SegmentTree(int len) {
			st = new int[len];
		}

		public String toString() {
			return Arrays.toString(st);
		}
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// GCD

	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return

		gcd(b, a % b);
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// NCR mod Fermat

	static long fact(int x) {
		long ans = 1;
		for (int i = 2; i <= x; i++)
			ans = mul(ans, i);
		return ans;
	}

	static int add(int a, int b) {
		if (a + b >= INF)
			return a + b - INF;
		return a + b;
	}

	static long mul(long a, long b) {
		return a * b % INF;
	}

	static long fastPow(long base, long exp) {
		if (exp == 0)
			return 1;
		long half = fastPow(base, exp / 2);
		if (exp % 2 == 0)
			return mul(half, half);
		return mul(half, mul(half, base));
	}

	static long modInv(long x) {
		return fastPow(x, INF - 2);
	}

	static long nCk(int n, int k) {
		return mul(fact(n), mul(modInv(fact(k)), modInv(fact(n - k))));
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Exponential Power Calculate

	static int power(int x, int y, int p) {
		int res = 1;
		x = x % p;
		if (x == 0)
			return 0;
		while (y > 0) {
			if ((y & 1) == 1)
				res = (res * x) % p;
			y = y >> 1;
			x = (x * x) % p;
		}
		return res;
	}

//	long power(long x,long y,long p){  
//	    long res=1;
//	    x=x%p;
//	    if (x==0) return 0;
//	    while (y>0){
//	        if ((y&1)==1)
//	            res=(res*x)%p; 
//	        y=y>>1;
//	        x=(x*x)%p;
//	    }
//	    return res;  
//	}
////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Prime Seive	

	static boolean primes[] = new boolean[(int) 1e5 + 1];

	static void seive() {
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i * i < primes.length; i++) {
			if (primes[i]) {
				for (int j = i * i; j < primes.length; j += i) {
					primes[j] = false;
				}
			}
		}
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Prime Factors

	static void primeFactors(int n) {
		while (n % 2 == 0) {
			System.out.print(2 + " ");
			n /= 2;
		}
		for (int i = 3; i <= Math.sqrt(n); i += 2) {
			while (n % i == 0) {
				System.out.print(i + " ");
				n /= i;
			}
		}
		if (n > 2)
			System.out.print(n);
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Prime Checker

	static boolean isPrime(long n) {
		if (n <= 1)
			return false;
		if (n <= 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		long sq = (long) Math.sqrt(n);
		for (int i = 5; i <= sq; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;

		return true;
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// MIN-MAX Functions

	static int _min(int[] ar) {
		intSort(ar, true);
		return ar[ar.length - 1];
	}

	static int _max(int[] ar) {
		intSort(ar, true);
		return ar[ar.length - 1];
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Int Sort

	static void intSort(int[] ar, boolean reverse) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i : ar)
			al.add(i);
		Collections.sort(al);
		if (reverse) {
			for (int i = 0; i < ar.length; i++)
				ar[i] = al.get(ar.length - i - 1);
		} else {
			for (int i = 0; i < ar.length; i++)
				ar[i] = al.get(i);
		}
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Long Sort

	static void longSort(long[] ar, boolean reverse) {
		ArrayList<Long> al = new ArrayList<Long>();
		for (long i : ar)
			al.add(i);
		Collections.sort(al);
		if (reverse) {
			for (int i = 0; i < ar.length; i++)
				ar[i] = al.get(ar.length - i - 1);
		} else {
			for (int i = 0; i < ar.length; i++)
				ar[i] = al.get(i);
		}
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Radix Sort

	public static int[] radixSort(int[] f) {
		return radixSort(f, f.length);
	}

	public static int[] radixSort(int[] f, int n) {
		int[] to = new int[n];
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (f[i] & 0xffff)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[f[i] & 0xffff]++] = f[i];
			int[] d = f;
			f = to;
			to = d;
		}
		{
			int[] b = new int[65537];
			for (int i = 0; i < n; i++)
				b[1 + (f[i] >>> 16)]++;
			for (int i = 1; i <= 65536; i++)
				b[i] += b[i - 1];
			for (int i = 0; i < n; i++)
				to[b[f[i] >>> 16]++] = f[i];
			int[] d = f;
			f = to;
			to = d;
		}
		return f;
	}

////////////////////////////////////////////////////////////

/////////////////////////////////////////////////// Mo's Query Template class
	static int blockSize = 0;

	static class Q implements Comparable<Q> {
		int index, left, right;

		Q(int index, int left, int right) {
			this.index = index;
			this.left = left;
			this.right = right;
		}

		@Override
		public int compareTo(Q o) {
			int block1 = left / blockSize;
			int block2 = o.left / blockSize;
			if (block1 == block2)
				return right - o.right;
			return block1 - block2;
		}

		public String toString() {
			return "[ " + index + " " + left + " " + right + " ]";
		}
	}

////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////
}
