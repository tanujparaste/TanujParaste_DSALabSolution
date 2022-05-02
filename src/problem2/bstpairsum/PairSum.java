package problem2.bstpairsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PairSum {
	Node root;
	static int count;

	// custom inner class with generic to store the pair (x,y)
	static class Pair<DT1, DT2> {
		DT1 x;
		DT2 y;

		Pair(DT1 x, DT2 y) {
			this.x = x;
			this.y = y;
		}
	}

	class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

		void insert(int value) {
			if (value == data) {
				return;
			}

			if (value < data) {
				if (left == null) {
					left = new Node(value);
				} else {
					left.insert(value);
				}
			} else {
				if (right == null) {
					right = new Node(value);
				} else {
					right.insert(value);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		PairSum ps = new PairSum();
		// inserting hardcoded values
		ps.insert(40);
		ps.insert(20);
		ps.insert(10);
		ps.insert(30);
		ps.insert(60);
		ps.insert(3);
		ps.insert(8);
		ps.insert(50);
		ps.insert(70);
		ps.insert(80);
		ps.insert(1);
		ps.insert(5);
		ps.insert(6);
		ps.insert(4);
		ps.insert(17);
		ps.insert(7);
		ps.insert(2);
		ps.insert(9);

		List<Integer> list = getSortedElements(ps.root);

		printList(list);

		System.out.print("Enter the value required pair sum: ");
		int sum = in.nextInt();

		List<Pair<Integer, Integer>> pairs = findPairWithSum(list, sum);

		if (pairs.isEmpty()) {
			System.out.println("No pair found for the required sum of " + sum);
		} else {
			System.out.println("\nSum = " + sum);
			System.out.println("Pair list: ");

			for (Pair<Integer, Integer> pair : pairs) {
				System.out.println("(" + pair.x + "," + pair.y + ")");
			}

			System.out.println();
		}

		in.close();
	}

	private void insert(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			// explore the tree and insert value
			// at appropriate tree
			root.insert(value);
		}
	}

	private static List<Integer> getSortedElements(Node root) {
		List<Integer> result = new ArrayList<>();
		Stack<Node> stack = new Stack<>();
		Node current = root;
		boolean flag = false;
		while (!flag) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				if (stack.empty()) {
					flag = true;
				} else {
					current = stack.pop();
					result.add(current.data);
					current = current.right;
				}
			}
		}
		return result;
	}

	private static List<Pair<Integer, Integer>> findPairWithSum(List<Integer> list, int sum) {
		List<Pair<Integer, Integer>> pairs = new ArrayList<>();
		int leftIndex = 0;
		int length = list.size();
		int rightIndex = length - 1;
		while (leftIndex < rightIndex) {
			if (list.get(leftIndex) + list.get(rightIndex) < sum) {
				leftIndex++;
			} else if (list.get(leftIndex) + list.get(rightIndex) > sum) {
				rightIndex--;
			} else {
				pairs.add(new Pair<Integer, Integer>(list.get(leftIndex), list.get(rightIndex)));
				leftIndex++;
				rightIndex--;
			}
		}

		return pairs;
	}

	private static void printList(List<Integer> list) {
		for (int e : list) {
			System.out.print(e + " ");
		}
		System.out.println();
	}
}
