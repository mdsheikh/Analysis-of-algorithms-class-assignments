

//import java.util.Arrays;
import java.util.Random;
public class assignment_one {
	
	
	private int comparisons = 0 ;
	public int rightIndex = 0;
	public int leftIndex = 0;
	
	public void setComparisons(int com) {
		this.comparisons = com;
	}
	
	public int GetComparisons() {
		return this.comparisons;
	}
	
	public static int[] GenerateDataSet(int size, int start, int end) {

		int[] arr = new int[size];
		arr[0] = 1;
		for (int i = 0; i < arr.length-1; i++) {
			arr[i+1] = arr[i] + (int) (Math.random() * (end - start) + start);

		}
		return arr;
	}


	public int BinarySearch(int A[], int l, int r, int key) 
	{   
	    int m; 
	  
	    while( l <= r ) 
	    { 
	        m = l + (r-l)/2; 
	        
	        this.comparisons++;
	
	        if( A[m] == key ) {  
	            return m; 
	        }
	       
	        // second comparison	
	        this.comparisons++; 
	        
	        if( A[m] < key ) {  
	            l = m + 1; 
	            rightIndex = l;
	            
	        } else { 
	            r = m - 1;
	            leftIndex = r; 
	        }
	    } 
	  
	    return -1; 
	} 
	
	public int binarySearch(int arr[], int l, int r, int element) {
        
		if (r >= l) {
			int mid = l + (r - l) / 2;

			this.comparisons++;
			if (arr[mid] == element) {
				return mid;
			}

			this.comparisons++;
			
			if (arr[mid] > element) {
				rightIndex = mid;
				return binarySearch(arr, l, mid - 1, element);
				
			} else {
				leftIndex = mid;
				return binarySearch(arr, mid + 1, r, element);

			}

		}
		return -1;

	}

	public static void main(String[] args) {

		assignment_one m = new assignment_one();

		final int size = 65536;
		final int min = 0;
		final int max = 10;

		// 1. Generate Dataset
		int[] dataset = GenerateDataSet(size, min, max);

		// sort array
		// Arrays.sort(dataset);
		int n = dataset.length;

		// System.out.println("Search Key: " + dataset[3]);
		// int searchElement = dataset[3];
		
		
		int[] searchElement = { 30, 56, 56, 78, 80, 26, 56, 56, 45, 10 }; 
		
	    System.out.println("====================CLASSICAL BINARY SEARCH======================");
		for (int i = 0; i < searchElement.length; i++) {
			m.setComparisons(0);

			int result = m.binarySearch(dataset, 0, n - 1, searchElement[i]);
			
			if (result == -1) {
				System.out.print("Not found: " + " leftIndex: " + m.leftIndex + " RightIndex: " + m.rightIndex);
				System.out.println(" Comparison made " + m.GetComparisons());

			} else {
				System.out.print("Element found at index : " + result);
				System.out.println(" Comparisons made: " + m.GetComparisons());

			}
		}
		
		System.out.println("====================IMPROVED BINARY SEARCH======================");
		for (int i = 0; i < searchElement.length; i++) {
			m.setComparisons(0);

			int result = m.BinarySearch(dataset, 0, n - 1, searchElement[i]);
			
			if (result == -1) {
				System.out.print("Not found: " + " leftIndex: " + m.leftIndex + " RightIndex: " + m.rightIndex);
				System.out.println(" Comparison made " + m.GetComparisons());

			} else {
				System.out.print("Element found at index : " + result);
				System.out.println(" Comparisons made: " + m.GetComparisons());

			}
		}
		

	}

}
