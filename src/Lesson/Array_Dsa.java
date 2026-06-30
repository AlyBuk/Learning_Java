package Lesson;

import java.util.Arrays;

public class Array_Dsa {
    public static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int sum = nums[right] + nums[right];
            if(sum == target){
                return new int[]{left,right};
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static int [] reverse(int[] nums){
        int right = nums.length -1;
        int left = 0;
        while(right >= left){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            right--;
            left++;
        }
        return nums;
    }
  public static int [] removeDuplicates(int [] nums){
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        int left = 0;

      for (int right = 1; right < nums.length; right++) {
          if(nums[right] != nums[left]){
              left++;
              nums[left] = nums[right];
          }
      }
      return Arrays.copyOf(nums, left + 1);
  }
    public static int[] moveDigits(int[] nums) {
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }

        return nums;
    }

    public static int[] moveOneDigit(int[] nums) {
        int left = 0;
        int right = 0;
        while(right < nums.length){
            if(nums[right] != 1){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
             left++;

            }
            right++;
        }
        return nums;
    }
    public static void main(String[] args) {
        int [] numList = {0,1,0,3,12};
        int[] nums = {1, 2, 4, 6, 10};
        System.out.println(Arrays.toString(twoSum(nums, 4)));
        System.out.println(Arrays.toString(reverse(nums)));
        System.out.println(Arrays.toString(moveDigits(numList)));
        System.out.println(Arrays.toString(moveOneDigit(numList)));

    }
}
