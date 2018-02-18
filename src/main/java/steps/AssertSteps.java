package steps;


import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * Created by egorvas on 28.07.16.
 */

public class AssertSteps {

    @Step
    public AssertSteps assertFieldCloseTo(String message, Double value, Double assertValue, Double error) {
        Assert.assertThat(message, value, Matchers.closeTo(assertValue,error));
        return this;
    }


}
