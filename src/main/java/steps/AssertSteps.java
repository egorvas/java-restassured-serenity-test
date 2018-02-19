package steps;


import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * Created by egorvas on 28.07.16.
 */

public class AssertSteps {

    @Step(callNestedMethods=false)
    public AssertSteps assertFieldCloseTo(String message, Double value, Double assertValue, Double error) {
        Assert.assertThat(message, value, Matchers.closeTo(assertValue,error));
        return this;
    }

    @Step(callNestedMethods=false)
    public AssertSteps assertThatFieldExists(String text, Boolean check) {
        Assert.assertTrue(text, check);
        return this;
    }

    @Step(callNestedMethods=false)
    public AssertSteps assertThatTokenExists(String text, Boolean check) {
        Assert.assertTrue(text, check);
        return this;
    }


}
