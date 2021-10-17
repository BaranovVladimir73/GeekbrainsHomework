

public class SomeActionWithArrays {

     public static int[] findLastNumberFourAndCreateNewArray(int[] a) {

        int index = -1;
        int[] result;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 4){
                index = i;
            }
        }

        result = new int[a.length-index-1];

        if (index >= 0){
            int j = 0;

            for (int i = index+1; i < a.length; i++) {
                result[j] = a[i];
                j++;
            }

        } else throw new RuntimeException();
        return result;
    }


    public static boolean findOneAndFour(int[] a){

        boolean oneExist = false;
        boolean fourExist = false;

        for (int element:a) {
            if (element == 1) oneExist = true;
            if (element == 4) fourExist = true;
        }

        return (oneExist && fourExist);
    }
}
