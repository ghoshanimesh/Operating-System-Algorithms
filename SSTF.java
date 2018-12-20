import java.util.*;

class SSTF{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int disk_head, disk_seek = 0;

		System.out.println("Enter the number of works");
		int n = sc.nextInt();

		System.out.println("Enter disk work queue");
		for (int i=0; i<n; i++) {
			queue.add(sc.nextInt());
		}

		System.out.println("Enter the current disk head");
		disk_head = sc.nextInt();
		int head_pos = 0;
		for (int i=0; i<n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j=0; j<queue.size(); j++) {
				//Check head with each element for minimum disk seek time
				if (Math.abs(queue.get(j) - disk_head) < min) {
					min = Math.abs(queue.get(j) - disk_head);
					head_pos = j;
				}
			}
			//Add the lowest disk seek time to total seek time and change the head
			disk_seek += Math.abs(queue.get(head_pos) - disk_head);
			disk_head = queue.get(head_pos);
			queue.remove(head_pos);
		}
		System.out.println(disk_seek + " Disk tot seek");
	}
}