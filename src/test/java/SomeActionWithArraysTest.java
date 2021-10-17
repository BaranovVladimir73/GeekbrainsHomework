import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SomeActionWithArraysTest {

    @Test
    void testSuccessfulReturningNewArray_WhenValidArray(){

        Assertions.assertArrayEquals(new int[]{3, 7},
                SomeActionWithArrays.findLastNumberFourAndCreateNewArray(new int[]{1, 2, 4, 4, 2, -1, 4, 3, 7}));

    }

    @Test
    void shouldThrowRuntimeException_WhenArrayDoNotHaveAnyFourNumber(){

        Assertions.assertThrows(RuntimeException.class,
                () -> SomeActionWithArrays.findLastNumberFourAndCreateNewArray(new int[]{1, 2, 0, 0, 2, -1, 0, 3, 7}));

    }

    @Test
    void testSuccessfulReturningNewEmptyArray_WhenFourIsLastMemberOfArray(){

        Assertions.assertArrayEquals(new int[]{},
                SomeActionWithArrays.findLastNumberFourAndCreateNewArray(new int[]{1, 2, 4, 4, 2, -1, 4, 3, 4}));

    }

    @Test
    void testReturnTrue_WhenOneAndFourExistInArray(){

        Assertions.assertTrue(SomeActionWithArrays.findOneAndFour(new int[]{1, 2, 4, 4, 2, -1, 4, 3, 4}));

    }


    @Test
    void testReturnFalse_WhenOneExistInArrayAndFourNotExistInArray(){

        Assertions.assertFalse(SomeActionWithArrays.findOneAndFour(new int[]{1, 2, 0, 0, 2, -1, 0, 3, 0}));

    }

    @Test
    void testReturnFalse_WhenOneNotExistInArrayAndFourExistInArray(){

        Assertions.assertFalse(SomeActionWithArrays.findOneAndFour(new int[]{0, 2, 4, 4, 2, -1, 4, 3, 4}));

    }




}
