import java.util.*;

class Banker{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of resources");
		int resource = sc.nextInt();
		System.out.println("Enter number of process");
		int process = sc.nextInt();

		//Initial
		int max[][] = new int[process][resource];
		int allocated[][] = new int[process][resource];
		int need[][] = new int[process][resource];
		int resource_vector[] = new int[resource];
		int available[] = new int[resource];

		//Extras
		int work[] = new int[resource];
		boolean finish[] = new boolean[process];

		for (int i=0; i<resource; i++) {
			System.out.println("Enter the max resource " + i + " available");
			resource_vector[i] = sc.nextInt();
		}

		System.arraycopy(resource_vector, 0, available, 0, resource);

		//Max
		for (int i=0; i<process; i++) {
		 	for (int j=0; j<resource; j++) {
		 		System.out.println("Enter the max resource "+ j +"for process " + i);
		 		max[i][j] = sc.nextInt();
		 	}
		 } 

		//Allocated
		for (int i=0; i<process; i++) {
		 	for (int j=0; j<resource; j++) {
		 		System.out.println("Enter the allocated resource "+ j +"for process " + i);
		 		allocated[i][j] = sc.nextInt();
		 		if ((available[j] - allocated[i][j]) < 0) {
		 			System.out.println("Cannot fulfill requests since non availabilty of resource");
		 			System.exit(0);
		 		}
		 		available[j]-=allocated[i][j];
		 	}
		 } 

		System.out.println("Max matrix");
		for (int i=0; i<process; i++) {
		 	for (int j=0; j<resource; j++) {
				System.out.print(max[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("Allocated matrix");
		for (int i=0; i<process; i++) {
		 	for (int j=0; j<resource; j++) {
				System.out.print(allocated[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("Need matrix");
		for (int i=0; i<process; i++) {
		 	for (int j=0; j<resource; j++) {
		 		need[i][j] = max[i][j] - allocated[i][j];
				System.out.print(need[i][j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println("Available resource");
		for (int i=0; i<resource; i++) {
			System.out.print(available[i] + "\t");
		}

		//Step 1
		System.arraycopy(available, 0, work, 0, resource);
		for (int i=0; i<process; i++) {
			int count = 0;
			for (int j=0; j<resource; j++) {
				count+=allocated[i][j];
			}
			if (count == 0) {
				finish[i] = true;
			}else{
				finish[i] = false;
			}
		}

		//Step 2, 3, 4
		int i = 0;
		int request = 0;
		int countOfExec = 0;
		int countCur = 0;
		System.out.println("\nSafe state Sequence");
		while(true){
			for (int j=0; j<resource; j++) {
				if (need[i][j] <= work[j]) {
					request++;
				}
			}
			if (!finish[i] && request == resource) {
				finish[i] = true;
				for (int j=0; j<resource; j++) {
					work[j] = work[j] + allocated[i][j];
				}
				System.out.print(i + "\t");
			}
			request = 0;
			i++;
			if (i == process) {
				for (int j=0; j<process; j++) {
					if (finish[j]) {
						countCur++;
					}
				}
				if (countCur > countOfExec) {
					i = 0;
					//mandatory to break
					countOfExec = countCur;
					countCur = 0;
				}else if (countCur == countOfExec) {
					break;
				}
			}
		}

		for (int j=0; j<process; j++) {
			if (!finish[j]) {
				System.out.println("Unsafe system due to process " + i);			
			}
		}
	}	
}