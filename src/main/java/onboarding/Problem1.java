package onboarding;

import java.util.List;

class Problem1 {
    /* 숫자를 문자열로 변환하는 메서드 */
    public static String integerToString(int num) {
        return Integer.toString(num);
    }

    /* 페이지 각 자리수 더하기 메서드 */
    public static int add(String individualNums) {
        int sum = 0;
        for (int i = 0; i < individualNums.length(); i++) {
            sum += (individualNums.charAt(i) - '0');
        }
        return sum;
    }

    /* 각 자리수 곱하기 메서드 */
    public static int multiply(String individualNums) {
        int result = 1;
        for (int i = 0; i < individualNums.length(); i++) {
            result *= (individualNums.charAt(i) - '0');
        }
        return result;
    }

    /* 두 수 비교 메서드 */
    public static int compareInt(int firstNum, int secondNum) {
        return firstNum - secondNum;
    }

    /* 왼쪽, 오른쪽 페이지 중 더 큰 페이지 반환하는 메서드 */
    public static int getHighestScore(List<Integer> arr) {
        int bigger = 0;
        int comparison;
        int tmpAdd;
        int tmpMult;
        for(Integer score : arr) {
            String seperatedNum = integerToString(score);
            tmpAdd = add(seperatedNum);
            tmpMult = multiply(seperatedNum);
            comparison = compareInt(tmpAdd, tmpMult);
            bigger = (comparison > 0) ? tmpAdd : tmpMult;
        }
        return bigger;
    }

    public static int solution(List<Integer> pobi, List<Integer> crong){
        int answer = Integer.MAX_VALUE;
        int pobiScore = getHighestScore(pobi);
        int crongScore = getHighestScore(crong);
        int comparison = compareInt(pobiScore, crongScore);
        if(comparison > 0) {
            answer = 1;
        } else if(comparison < 0) {
            answer = 2;
        } else {
            answer = 0;
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        solution(List.of(131, 132), List.of(211, 212));
    }
}