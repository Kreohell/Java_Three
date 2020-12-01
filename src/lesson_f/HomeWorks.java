package lesson_f;


import java.util.Arrays;

public class HomeWorks {

    static int[] array = {1, 2, 1, 2, 2, 3, 4, 1, 7};
    static int[] arrayTwo = {1, 4, 1, 7, 4, 4, 4, 1};

    public int[] methodOne(int[] arr) {
        boolean buffer = false;
        int[] newArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                buffer = true;
                newArray = new int[arr.length - i - 1];
                for (int j = i + 1, k = 0; j < arr.length; j++, k++) {
                    newArray[k] = arr[j];
                }
            }
        }
        if (!buffer) {
            throw new RuntimeException("Массив должен содержать число 4.");
        }
        return newArray;
    }

    public boolean methodTwo(int[] arr){
        boolean buffOne = false;
        boolean buffTwo = false;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1) buffOne = true;
            if(arr[i] == 4) buffTwo = true;
            if(arr[i] != 1 && arr[i] != 4) throw new RuntimeException("Массив должен состоять из чисел: 1 и 4.");
        }
        return buffOne && buffTwo;
    }

    public static void main(String[] args) {
        HomeWorks homeWorks = new HomeWorks();
        System.out.println(Arrays.toString(homeWorks.methodOne(array)));
        System.out.println(homeWorks.methodTwo(arrayTwo));
    }

}
