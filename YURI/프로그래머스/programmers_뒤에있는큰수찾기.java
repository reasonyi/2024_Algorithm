class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            int current = numbers[i];

            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stack.peek();
            }

            stack.push(current);
        }

        return answer;
    }
}
