import java.util.*;

class SCAN{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> queue = new ArrayList<Integer>();
		int disk_head, disk_seek = 0;

		System.out.println("Enter the number of works");
		int n = sc.nextInt();
		int visited[] = new int[n+1];


		System.out.println("Enter the size of cylinder");
		int disk_space = sc.nextInt();

		System.out.println("Enter disk work queue");
		for (int i=0; i<n; i++) {
			int work = sc.nextInt();
			if (work<0 || work>disk_space) {
				System.out.println("Invalid");
			}else{
				queue.add(work);
			}
		}

		System.out.println("Enter the current disk head");
		disk_head = sc.nextInt();
		System.out.println("Enter the previous disk head");
		int prev_head = sc.nextInt();
		int head_pos = 0;
		sort(queue,n);
		for (int i=0; i<n; i++) {
			if (queue.get(i) < disk_head) {
				continue;
			}else{
				head_pos = i;
				break;
			}
		}
		queue.add(head_pos, disk_head);
		// System.out.println(queue);

		boolean direction_left;
		if (disk_head - prev_head < 0) {
			direction_left = true;
		}else{
			direction_left = false;
		}
		int initial_head_pos = head_pos;
		boolean changed = false;
		for (int i=0; i<n; i++) {
			if (direction_left) {
				//initial Direction is left
				if (head_pos != 0 && !changed) {
					disk_seek+= Math.abs(queue.get(head_pos) - queue.get(head_pos-1));
					head_pos--;
				}else{
					//change direction
					changed = true;
					if (head_pos == 0) {
						//add the boundary
						disk_seek+= Math.abs(queue.get(0) - 0);
						disk_seek+= Math.abs(queue.get(initial_head_pos) - 0);
						head_pos = initial_head_pos+1;
					}else{
						disk_seek+= Math.abs(queue.get(head_pos) - queue.get(head_pos+1));
						head_pos++;
					}
				}
			}else{
				//initial Direction is right
				if (head_pos != n && !changed) {
					disk_seek+= Math.abs(queue.get(head_pos) - queue.get(head_pos+1));
					head_pos++;
				}else{
					// change direction
					changed = true;
					if (head_pos == n) {
						//add the boundary
						disk_seek+= Math.abs(queue.get(head_pos) - (disk_space - 1));
						disk_seek+= Math.abs(queue.get(initial_head_pos-1) - (disk_space - 1));
						head_pos = initial_head_pos-1;
					}else{
						disk_seek+= Math.abs(queue.get(head_pos) - queue.get(head_pos-1));
						head_pos--;
					}
				}
			}
		}
		System.out.println(disk_seek + " Disk tot seek");
	}

    public static void sort(ArrayList<Integer> alp, double length){
        for(int i = 0 ; i<length; i++){
            for(int j=0; j<length; j++){
                if(alp.get(i) < alp.get(j)){
                    Collections.swap(alp, i, j);
                }
            }
        }
    }	
}