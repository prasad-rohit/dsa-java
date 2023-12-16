/*Question
* Given a string s, return the longest palindromic substring in s.*/

// Approach

/* To return the longest substring we start with going through all the substrings which involves generating all the 
* subsequences and checking if they are palindrome or not on the go and then return the longest one. Since we have 
* to find the longest one we should start iterating from the longest one itself and then if we get any answer we return*/

class Solution {
    public boolean isPalindrome(String a, int start,int end){
        while(start <= end){
            if(a.charAt(start) != a.charAt(end)){
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public String longestPalindrome(String s) {
        String ans = "";

        for(int length = s.length();length>=0;length--){
            for(int start = 0;start <= s.length()-length;start++){
                if(isPalindrome(s,start,start+length-1)){
                    return s.substring(start,start+length);
                }
            }
        }
        return ans;
    }
}


/*
* Going through the concepts of palindrome in case when string from i+1 to j-1 index is palindrome then if string[i] == string[j]
* then string from index i to j will also be a palindrome. So, we will cover the cases starting from the strings from size 1 to
* n, by checking that if they are palindrome or not. In case of stirng with size 1 they will be a palindrome and in case of 
* string with size 2 if both the characters are same then they will also be a palindrome. 
* 
* Then using the above values we determine whether the strings of size more than that are palindrome or not. Such as a string of 
* size 3 is to be checked then we will only check the first and last characaters and as only one character is always a palindorme.
* If they match then we can say that string is palindrome.
* 
* Eventually we will keep on increasing the size or the difference of the strings and keep on checking if they are palindrome or not
* */

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] ans = new int[]{0,0};

        // We mark all the string with size 1 as palindrome
        for(int i=0;i<n;i++){
            dp[i][i] = true;
        }

        // Marking the string of size 2 as palindorme if first and last character matches
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                ans[0] = i;
                ans[1] = i+1;
            }
        }

        // Then checking strings from size >=2 by matching the first and last characters and then checking if internal string is palindrome or not
        for(int diff = 2;diff<n;diff++){
            for(int i =0 ;i<n-diff;i++){
                int j = i+diff;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]){
                    dp[i][j] = true;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }

        int i =ans[0];
        int j = ans[1];

        return s.substring(i,j+1);
    }
}


/* Instead of storing the middle string whether it is palindrome or not we can just start iterating from middle.  
* The longest pal subsequence can be of odd and even length both so we handle both cases. And for each index we expand on both sides
* 
* then we return length as length - 2 in expand function
* 
* And for each index in main function we check the max length and finally return it*/

class Solution {
    private int expand(int i,int j,String s){
        int left = i;
        int right = j;

        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return right-left-1;
    }

    public String longestPalindrome(String s) {
        int[] ans = new int[]{0,0};

        for(int i=0;i<s.length();i++){
            int oddl = expand(i,i,s);
            if(oddl > ans[1] - ans[0] +1){
                int dist = oddl/2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }


            int evenl = expand(i,i+1,s);
            if(evenl > ans[1] - ans[0] +1){
                int dist = (evenl/2) - 1;
                ans[0] = i - dist;
                ans[1] = i + dist + 1;
            }
        }

        int i = ans[0];
        int j = ans[1];

        return s.substring(i,j+1);
    }
}
