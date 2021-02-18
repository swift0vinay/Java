  
  // COLLECTIONS SORT
  
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
  
  // RADIX SORT 
  
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
	
 // RUFFLE SORT
 
 static void ruffleSort(int[] a) {
		//ruffle
		int n=a.length;
		Random r=new Random();
		for (int i=0; i<a.length; i++) {
			int oi=r.nextInt(n), temp=a[i];
			a[i]=a[oi];
			a[oi]=temp;
		}
		
		//then sort
		Arrays.sort(a);
 }
