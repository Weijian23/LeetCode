import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCode {
    public int numberOfSteps (int num) {
        int steps = Integer.MAX_VALUE;
        while (num != 0) {
            if(num % 2 == 0) {
                num = num / 2;
                steps++;
            }
            else if(num % 2 == 1) {
                num = num - 1;
                steps++;
            }
        }
        return steps;
    }

    public int countNegatives(int[][] grid) {
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            int[] array = grid[i];
            for(int k = array.length - 1; k >= 0; k--) {
                int number = array[k];
                if(number >= 0) {
                    break;
                }
                else {
                    result++;
                }
            }
        }
        return  result;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<String, Integer> occurence = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            String number = "" + arr[i];
            if(occurence.containsKey(number)) {
                int value = occurence.get(number);
                value++;
                occurence.put(number, value);
            }
            else {
                occurence.put(number, 1);
            }
        }

        Set<Integer> mySet = new HashSet<>();
        for (String key: occurence.keySet()) {
            int value = occurence.get(key);
            if(!mySet.add(value)) {
                return false;
            }
        }
        return  true;
    }

//    public int[] kWeakestRows(int[][] mat, int k) {
//        Map<String, Integer> myMap = new HashMap<>();
//        for(int i = 0; i < mat.length; i++) {
//            int[] row = mat[i];
//            int numberOfOne = 0;
//            for(int j = 0; j < row.length; j++) {
//                int number = row[j];
//                if(number == 1) {
//                    numberOfOne++;
//                }
//                if(number == 0) {
//                    break;
//                }
//            }
//            int numberOfZero = row.length - numberOfOne;
//            myMap.put("" + i, numberOfOne);
//        }
//        Map<String, Integer> sortedMap = new HashMap<>();
//
//        System.out.println("unsorted: " + myMap);
//
//        myMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue())
//                .forEachOrdered(x -> sortedMap.put("" + x.getKey(), x.getValue()));
//
//        System.out.println("Sorted: " + sortedMap);
//
//        int[] weak = new int[mat.length];
//        int index = 0;
//        for (String key: sortedMap.keySet()) {
//            weak[index] = Integer.parseInt(key);
//            index++;
//        }
//
//        int[] result = new int[k];
//        for(int l = 0; l < k; l++) {
//            result[l] = weak[l];
//        }
//
//        return  result;
//    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        List<int[]> myList = new ArrayList<>();
        for(int i = 0; i < mat.length; i++) {
            int[] row = mat[i];
            int numberOfOne = 0;
            for(int j = 0; j < row.length; j++) {
                int number = row[j];
                if(number == 1) {
                    numberOfOne++;
                }
                if(number == 0) {
                    break;
                }
            }
            int numberOfZero = row.length - numberOfOne;
            int[] array = new int[2];
            array[0] = i;
            array[1] = numberOfOne;
            myList.add(array);
        }

        Collections.sort(myList, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                }
                return  o1[0] - o2[0];
            }
        });

        for(int i = 0; i < k; i++) {
            result[i] = myList.get(i)[0];
        }
        return  result;
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> myMap = new HashMap<>();
        for(int i = 0; i < cpdomains.length; i++) {
            String inputString = cpdomains[i];
//            String[] array = inputString.split(" ");
//            int visit = Integer.parseInt(array[0]);
//            String domain = array[1];
            int index = inputString.indexOf(" ");
            int visit = Integer.parseInt(inputString.substring(0, index));
            String domain = inputString.substring(index+1);
            String[] domainElement = domain.split("\\.");
            String key = "";
            for(int j = domainElement.length - 1; j >= 0 ; j--) {
                if(j == domainElement.length - 1) {
                    key = domainElement[j];
                }
                else {
                    key = domainElement[j] + "." + key;
                }

                if(myMap.containsKey(key)) {
                    int value = myMap.get(key);
                    value = value + visit;
                    myMap.put(key, value);
                }
                else {
                    myMap.put(key, visit);
                }
            }
        }
        List<String> result = new ArrayList<>();

        for (String key: myMap.keySet()) {
            int value = myMap.get(key);
            String s = value + " " + key;
            result.add(s);
        }

        return result;
    }

    public int countCharacters(String[] words, String chars) {
        LinkedList<Character> charList = new LinkedList<>();
        for(int k = 0; k < chars.length(); k++) {
            charList.add(chars.charAt(k));
        }

        int result = 0;
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean flag = true;
            LinkedList<Character> tem = new LinkedList<>();
            tem.addAll(charList);
            for(int j = 0; j < word.length(); j++) {
                char word_c = word.charAt(j);
                if(tem.contains(word_c)) {
                    int index = tem.indexOf(word_c);
                    tem.remove(index);
                }
                else {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result = result + word.length();
            }
        }
        return  result;
    }

    public List<String> commonChars(String[] A) {
        List<Map<Character, Integer>> l = new ArrayList<>();
        for(int i = 0; i < A.length; i++) {
            String word = A[i];
            char[] charArray = word.toCharArray();
            Map<Character, Integer> myMap = new HashMap<>();
            for(int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                if(myMap.containsKey(c)) {
                    int value = myMap.get(c) + 1;
                    myMap.put(c, value);
                }
                else {
                    myMap.put(c, 1);
                }
            }
            l.add(myMap);
        }

        List<String> result = new ArrayList<>();
        List<String> tested = new ArrayList<>();
        for(int i = 0; i < l.size(); i++) {
            Map<Character, Integer> myMap = l.get(i);
            List<Map<Character, Integer>> copy = new ArrayList<>(l);
            copy.remove(i);
            for (Character key:myMap.keySet()) {
                int minApperence = myMap.get(key);
                boolean flag = true;
                for(int j = 0; j < copy.size(); j++) {
                    Map<Character, Integer> targetMap = copy.get(j);
                    if (!targetMap.containsKey(key)) {
                        flag = false;
                        continue;
                    }
                    else {
                        int value = targetMap.get(key);
                        if(value < minApperence) {
                            minApperence = value;
                        }
                    }
                }
                if(flag) {
                    if(!tested.contains("" + key)) {
                        tested.add("" + key);
                        for(int k = 1; k <= minApperence; k++) {
                            result.add("" + key);
                        }
                    }
                }
            }
        }
        return result;
    }

    public int[] sortByBits(int[] arr) {
        List<Integer> l = Arrays.stream(arr).boxed().collect(Collectors.toList());
        l.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = Integer.toBinaryString(o1);
                long count1 = s1.chars().filter(ch -> ch =='1').count();
                String s2 = Integer.toBinaryString(o2);
                long count2 = s2.chars().filter(ch -> ch =='1').count();

                if(count1 > count2) {
                    return  1;
                }
                if(count1 == count2) {
                    if(o1 >= o2) {
                        return -1;
                    }
                    else {
                        return  1;
                    }
                }
                else {
                    return  -1;
                }
            }
        });

        System.out.println(l);
        return l.stream().mapToInt(i -> i).toArray();
    }

    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (char c: S.toCharArray()) {
            if(!stack.empty() && c == stack.peek()) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while ((!stack.isEmpty())) {
            sb.append(stack.pop());
        }
        return  sb.reverse().toString();
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int currentMin = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int current = arr[i];
            int next = arr[i + 1];
            if(next - current < currentMin) {
                currentMin = next - current;
                List<Integer> newPair = new ArrayList<>();
                newPair.add(current);
                newPair.add(next);
                result.clear();
                result.add(newPair);
            }
            else if(next - current == currentMin) {
                List<Integer> newPair = new ArrayList<>();
                newPair.add(current);
                newPair.add(next);
                result.add(newPair);
            }
        }
        return  result;
    }

    public int numRookCaptures(char[][] board) {
        int result = 0;
        int x_R = -1;
        int y_R = -1;
        for(int y = 0; y < board.length; y++) {
            char[] row = board[y];
            for(int x = 0; x < row.length; x++) {
                char c = row[x];
                if(c == 'R') {
                    x_R = x;
                    y_R = y;
                    break;
                }
            }
        }

        for(int i = x_R-1; i > 0 ; i--) {
            char c = board[y_R][i];
            if(c == 'p') {
                result++;
                break;
            }
            else if(c != '.') {
                break;
            }
        }

        for(int i = x_R+1; i < 8 ; i++) {
            char c = board[y_R][i];
            if(c == 'p') {
                result++;
                break;
            }
            else if(c != '.') {
                break;
            }
        }

        for(int i = y_R-1; i > 0 ; i--) {
            char c = board[i][x_R];
            if(c == 'p') {
                result++;
                break;
            }
            else if(c != '.') {
                break;
            }
        }

        for(int i = y_R+1; i < 8 ; i++) {
            char c = board[i][x_R];
            if(c == 'p') {
                result++;
                break;
            }
            else if(c != '.') {
                break;
            }
        }
        return  result;
    }

    public void reverseString(char[] s) {
        for(int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

    public int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        char[] charArray = S.toCharArray();
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            char letter = charArray[i];
            if(letter == C) {
                index.add(i);
            }
        }
        for(int i = 0; i < charArray.length; i++) {
            char letter = charArray[i];
            int minDestance = Integer.MAX_VALUE;
            for (Integer number: index) {
                int distance = Math.abs(number - i);
                if(distance < minDestance) {
                    minDestance = distance;
                }
            }
            result[i] = minDestance;
        }
        return  result;
    }



    public static void main(final String[] args) throws Exception {
//        List<String> l = new ArrayList<>();
//        l.add("1");
//        l.add("2");
//        l.add("3");
//        l.add("4");
//
//        List<String> l2 = new ArrayList<>(l);


    }
}
