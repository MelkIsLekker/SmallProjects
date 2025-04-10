package SortMethods;

public class SortingMethodTimer {

    public static void timeMethod(SortingMethod method, int[] list, boolean showFive) {
        
        if (showFive) {
            long beginTime = System.currentTimeMillis();
            method.callMethod(list);
            long endTime = System.currentTimeMillis();
            System.out.println(method.getMethodName() + " took " + (endTime - beginTime) + "ms to complete");
            System.out.println("Smallest Five:          Largest Five:");
            for (int i = 0; i < 5; i++) {
                System.out.println(list[i] + "             " + list[list.length - 1 - i]);
            }
        }
        else timeMethod(method, list);
    }

    public static void timeMethod(SortingMethod method, int[] list) {
        long beginTime = System.currentTimeMillis();
        method.callMethod(list);
        long endTime = System.currentTimeMillis();
        System.out.println(method.getMethodName() + " took " + (endTime - beginTime) + "ms to complete");
    }

}
                                