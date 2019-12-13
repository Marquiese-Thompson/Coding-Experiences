public class Precision {
    public static void accurate(int r1, int r2, String type)
    {
        int total = r1+r2;
        System.out.println("Number of "+type +" Reviews "+total);
        System.out.println("Correctly classified: "+r1);
        System.out.println("Misclassified: "+r2);
        double newTotal = total;
        double percent = (r1/newTotal*100.0);
        System.out.println("Correct classification rate: "+percent +" %");
        System.out.println();
    }
}
